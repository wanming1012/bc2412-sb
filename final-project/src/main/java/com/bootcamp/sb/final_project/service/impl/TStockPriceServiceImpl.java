package com.bootcamp.sb.final_project.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.sb.final_project.dto.CachedTStockPriceDTO;
import com.bootcamp.sb.final_project.dto.TStockPriceDTO;
import com.bootcamp.sb.final_project.entity.StockPriceEntity;
import com.bootcamp.sb.final_project.entity.mapper.EntityMapper;
import com.bootcamp.sb.final_project.lib.RedisManager;
import com.bootcamp.sb.final_project.model.TStockRecordType;
import com.bootcamp.sb.final_project.repository.TStockPriceRepository;
import com.bootcamp.sb.final_project.service.TStockPriceService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class TStockPriceServiceImpl implements TStockPriceService {
  private static final String REDIS_FINAL = "SYSDATE-";
  private static final long REDIS_EXPIRE_HOURS = 8;

  @Autowired
  private TStockPriceRepository tStockPriceRepository;

  @Autowired
  private RedisManager redisManager;

  @Autowired
  private EntityMapper entityMapper;

  @Override
  public StockPriceEntity create(StockPriceEntity stockPriceEntity) {
    Optional<StockPriceEntity> data =
        this.tStockPriceRepository.findBySymbolAndTypeAndRegularMarketTime(
            stockPriceEntity.getSymbol(), stockPriceEntity.getType(), stockPriceEntity.getRegularMarketTime());
    if (data.isPresent()) {
      return data.get();
    }
    else {
      return this.tStockPriceRepository.save(stockPriceEntity);
    }
  }

  @Override
  public Optional<LocalDateTime> getSystemTime(String symbol)
      throws JsonProcessingException {
    LocalDate cachedSystemTime =
        this.redisManager.get(REDIS_FINAL + symbol, LocalDate.class);
    if (cachedSystemTime != null) {
      return Optional.of(LocalDateTime.of(cachedSystemTime, LocalTime.MIN));
    }

    Optional<StockPriceEntity> stockPriceEntity = this.tStockPriceRepository
        .findTopBySymbolOrderByRegularMarketTimeDesc(symbol);

    if (stockPriceEntity.isEmpty()) {
      return Optional.empty();
    }

    cachedSystemTime = this.entityMapper
        .convert(stockPriceEntity.get().getRegularMarketTime()).toLocalDate();
    this.redisManager.set(REDIS_FINAL + symbol, cachedSystemTime,
        Duration.ofHours(REDIS_EXPIRE_HOURS));
    return Optional.of(LocalDateTime.of(cachedSystemTime, LocalTime.MIN));
  }

  @Override
  public List<TStockPriceDTO> getPriceData(String symbol, TStockRecordType type)
      throws JsonProcessingException {
    Optional<LocalDateTime> systemTime = getSystemTime(symbol);
    if (systemTime.isEmpty()) {
      return Collections.emptyList();
    }

    CachedTStockPriceDTO cachedTStockPriceDTO = this.redisManager
        .get(type.getValue() + "-" + symbol, CachedTStockPriceDTO.class);

    if (cachedTStockPriceDTO != null) {
      Optional<StockPriceEntity> dbMaxRegularMarketTime =
          this.tStockPriceRepository
              .findTopBySymbolAndTypeAndMarketTimeGreaterThanEqualOrderByRegularMarketTimeDesc(
                  symbol, type.getValue(), systemTime.get());

      if (dbMaxRegularMarketTime.isEmpty()) {
        return Collections.emptyList();
      }

      if (dbMaxRegularMarketTime.get()
          .getRegularMarketTime() <= cachedTStockPriceDTO
              .getRegularMarketTime()) {
        return Arrays.asList(cachedTStockPriceDTO.getData());
      }
    }

    List<StockPriceEntity> newPrices = this.tStockPriceRepository
        .findAllBySymbolAndTypeAndMarketTimeGreaterThanEqualOrderByMarketTime(
            symbol, type.getValue(), systemTime.get());

    if (newPrices.isEmpty()) {
      return Collections.emptyList();
    }

    cachedTStockPriceDTO = CachedTStockPriceDTO.builder()
        .regularMarketTime(newPrices.stream().map(e -> e.getRegularMarketTime())
            .max(Long::compareTo)
            .orElseThrow(() -> new RuntimeException(
                "Max regular market time cannot be found")))
        .data(newPrices.stream().map(e -> this.entityMapper.map(e))
            .toArray(TStockPriceDTO[]::new))
        .build();

    this.redisManager.set(type.getValue() + "-" + symbol, cachedTStockPriceDTO,
        Duration.ofHours(12));

    return Arrays.asList(cachedTStockPriceDTO.getData());
  }

  // @Override
  // public boolean isDataExist(String symbol, TStockRecordType type,
  //     Long regularMarketTime) {
  //   Optional<StockPriceEntity> data =
  //       this.tStockPriceRepository.findBySymbolAndTypeAndRegularMarketTime(
  //           symbol, type.getValue(), regularMarketTime);
  //   return data.isPresent();
  // }

  @Override
  public void setSystemTime(String symbol, LocalDate date)
      throws JsonProcessingException {
    this.redisManager.set(REDIS_FINAL + symbol, date,
        Duration.ofHours(REDIS_EXPIRE_HOURS));
  }

  @Override
  public List<TStockPriceDTO> getMAPriceData(String symbol,
      TStockRecordType type, int window)
      throws JsonProcessingException {
    List<TStockPriceDTO> prices = getPriceData(symbol, type);
    if (prices.isEmpty() || window <= 0) {
      return Collections.emptyList();
    }

    List<TStockPriceDTO> movingAveragePrices = new ArrayList<>();
    for (int i = 0; i < prices.size(); i++) {
      int startIndex = i - window + 1;
      if (startIndex < 0) {
        startIndex = 0;
      }

      int dataSize = 0;
      BigDecimal total = BigDecimal.valueOf(0.0);
      for (int j = startIndex; j <= i; j++) {
        total = total.add(BigDecimal.valueOf(prices.get(j).getRegularMarketPrice()));
        dataSize++;
      }

      Double avgPrice = total.divide(BigDecimal.valueOf(dataSize), 6, RoundingMode.HALF_UP).doubleValue();
      movingAveragePrices.add(new TStockPriceDTO(prices.get(i).getRegularMarketTime(), avgPrice));
    }

    return movingAveragePrices;
  }
}
