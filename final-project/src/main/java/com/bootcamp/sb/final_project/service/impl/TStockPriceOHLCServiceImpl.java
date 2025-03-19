package com.bootcamp.sb.final_project.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.sb.final_project.dto.CachedTStockPriceOHLCDTO;
import com.bootcamp.sb.final_project.dto.TStockPriceDTO;
import com.bootcamp.sb.final_project.dto.TStockPriceOHLCDTO;
import com.bootcamp.sb.final_project.entity.StockPriceOHLCEntity;
import com.bootcamp.sb.final_project.lib.RedisManager;
import com.bootcamp.sb.final_project.model.TStockRecordType;
import com.bootcamp.sb.final_project.repository.TStockPriceOHLCRepository;
import com.bootcamp.sb.final_project.service.TStockPriceOHLCService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class TStockPriceOHLCServiceImpl implements TStockPriceOHLCService {
  @Autowired
  private TStockPriceOHLCRepository tStockPriceOHLCRepository;

  @Autowired
  private RedisManager redisManager;

  @Override
  public StockPriceOHLCEntity create(
      StockPriceOHLCEntity stockPriceOHLCEntity) {
    Optional<StockPriceOHLCEntity> data = this.tStockPriceOHLCRepository
        .findBySymbolAndTimestamp(stockPriceOHLCEntity.getSymbol(),
            stockPriceOHLCEntity.getTimestamp());
    if (data.isPresent()) {
      return data.get();
    } else {
      return this.tStockPriceOHLCRepository.save(stockPriceOHLCEntity);
    }
  }

  @Override
  public Optional<Long> getStartDate(String symbol) {
    Optional<StockPriceOHLCEntity> data =
        this.tStockPriceOHLCRepository.findTopBySymbolOrderByTimestamp(symbol);
    if (data.isPresent()) {
      return Optional.of(data.get().getTimestamp());
    } else {
      return Optional.empty();
    }
  }

  @Override
  public Optional<Long> getEndDate(String symbol) {
    Optional<StockPriceOHLCEntity> data = this.tStockPriceOHLCRepository
        .findTopBySymbolOrderByTimestampDesc(symbol);
    if (data.isPresent()) {
      return Optional.of(data.get().getTimestamp());
    } else {
      return Optional.empty();
    }
  }

  @Override
  public List<TStockPriceOHLCDTO> getOHLCData(String symbol,
      TStockRecordType type, int window) throws JsonProcessingException {
    CachedTStockPriceOHLCDTO cachedTStockPriceOHLCDTO = this.redisManager.get(
        type.getValue() + "-OHLC-" + window + "-" + symbol,
        CachedTStockPriceOHLCDTO.class);

    if (cachedTStockPriceOHLCDTO != null) {
      Optional<StockPriceOHLCEntity> dbMaxRegularMarketTime =
          this.tStockPriceOHLCRepository
              .findTopBySymbolOrderByTimestampDesc(symbol);

      if (dbMaxRegularMarketTime.isEmpty()) {
        return Collections.emptyList();
      }

      if (dbMaxRegularMarketTime.get()
          .getTimestamp() <= cachedTStockPriceOHLCDTO.getTimestamp()) {
        return Arrays.asList(cachedTStockPriceOHLCDTO.getData());
      }
    }

    Long startDate = getStartDate(type, 0);
    Long fetchStartDate = getStartDate(type, window * 5);
    List<StockPriceOHLCEntity> ohlcEntities = this.tStockPriceOHLCRepository
        .findAllBySymbolAndTimestampGreaterThanEqualOrderByTimestamp(symbol,
            fetchStartDate);
    List<TStockPriceOHLCDTO> ohlcDtos = switch (type) {
      case FIVE_MINUTES -> Collections.emptyList();
      case DAILY -> convertDaily(ohlcEntities);
      case WEEKLY -> convertWeekly(ohlcEntities);
      case MONTHLY -> convertMonthly(ohlcEntities);
    };

    int windowData = 0;
    int startIndex = 0;
    for (int i = ohlcDtos.size() - 1; i >= 0; i--) {
      if (ohlcDtos.get(i).getTimestamp() < startDate) {
        windowData++;
        if (windowData == window) {
          startIndex = i;
          break;
        }
      }
    }
    System.out.println("************** " + ohlcDtos.size() + ", " + startIndex);

    ohlcDtos = ohlcDtos.subList(startIndex, ohlcDtos.size());

    cachedTStockPriceOHLCDTO =
        CachedTStockPriceOHLCDTO.builder()
            .timestamp(ohlcDtos.stream().map(e -> e.getTimestamp())
                .max(Long::compareTo)
                .orElseThrow(() -> new RuntimeException(
                    "Max regular market time cannot be found")))
            .data(ohlcDtos.toArray(TStockPriceOHLCDTO[]::new)).build();

    this.redisManager.set(type.getValue() + "-OHLC-" + window + "-" + symbol,
        cachedTStockPriceOHLCDTO, Duration.ofHours(12));

    return ohlcDtos;
  }

  @Override
  public List<TStockPriceDTO> getMAClosePriceData(String symbol,
      TStockRecordType type, int window) throws JsonProcessingException {
    List<TStockPriceOHLCDTO> ohlcs = getOHLCData(symbol, type, window);
    if (ohlcs.isEmpty() || window <= 0) {
      return Collections.emptyList();
    }

    List<TStockPriceDTO> movingAveragePrices = new ArrayList<>();
    BigDecimal total = BigDecimal.valueOf(0.0);
    for (int i = 0; i < window; i++) {
      total = total.add(BigDecimal.valueOf(ohlcs.get(i).getClose()));
    }

    Double avgPrice =
        total.divide(BigDecimal.valueOf(window), 6, RoundingMode.HALF_UP)
            .doubleValue();
    movingAveragePrices.add(
        new TStockPriceDTO(ohlcs.get(window - 1).getTimestamp(), avgPrice));

    for (int i = 1; i < ohlcs.size() - window + 1; i++) {
      total = total.subtract(BigDecimal.valueOf(ohlcs.get(i - 1).getClose()))
          .add(BigDecimal.valueOf(ohlcs.get(i + window - 1).getClose()));

      avgPrice =
          total.divide(BigDecimal.valueOf(window), 6, RoundingMode.HALF_UP)
              .doubleValue();

      movingAveragePrices.add(new TStockPriceDTO(
          ohlcs.get(i + window - 1).getTimestamp(), avgPrice));
    }
    System.out.println("**************** " + window + ", " + ohlcs.size() + ", "
        + movingAveragePrices.size());

    return movingAveragePrices;
  }

  @Override
  public LocalDate convert(Long timestamp) {
    return Instant.ofEpochSecond(timestamp).atZone(ZoneOffset.UTC)
        .toLocalDate();
  }

  private Long getStartDate(TStockRecordType type, int window) {
    LocalDate today = LocalDate.now();
    LocalDate startDate = switch (type) {
      case FIVE_MINUTES -> today;
      case DAILY -> today.minusMonths(1).minusDays(window);
      case WEEKLY -> today.minusYears(1).minusDays(7 * window);
      case MONTHLY -> today.minusYears(5).minusMonths(window);
    };
    System.out.println("*************" + startDate + ", " + window);
    return startDate.atStartOfDay().atZone(ZoneId.of("Asia/Hong_Kong"))
        .toEpochSecond();
  }

  private List<TStockPriceOHLCDTO> convertDaily(
      List<StockPriceOHLCEntity> entities) {
    return entities.stream()
        .map(e -> TStockPriceOHLCDTO.builder().timestamp(e.getTimestamp())
            .open(e.getOpen()).close(e.getClose()).low(e.getLow())
            .high(e.getHigh()).build())
        .toList();
  }

  private List<TStockPriceOHLCDTO> convertWeekly(
      List<StockPriceOHLCEntity> entities) {
    // Map to group by week start timestamp
    Map<Long, List<StockPriceOHLCEntity>> weeklyGroups = new TreeMap<>();

    // Group data by week
    for (StockPriceOHLCEntity entity : entities) {
      LocalDateTime dateTime =
          LocalDateTime.ofInstant(Instant.ofEpochSecond(entity.getTimestamp()),
              ZoneId.of("Asia/Hong_Kong"));
      // Get the Monday of the week (ISO week definition)
      LocalDateTime weekStart = dateTime
          .with(IsoFields.WEEK_OF_WEEK_BASED_YEAR,
              dateTime.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR))
          .with(DayOfWeek.MONDAY).withHour(0).withMinute(0).withSecond(0)
          .withNano(0);
      Long weekStartTimestamp =
          weekStart.atZone(ZoneId.of("Asia/Hong_Kong")).toEpochSecond();

      weeklyGroups.computeIfAbsent(weekStartTimestamp, k -> new ArrayList<>())
          .add(entity);
    }

    // Aggregate into weekly OHLC
    List<TStockPriceOHLCDTO> weeklyData = new ArrayList<>();
    for (Map.Entry<Long, List<StockPriceOHLCEntity>> entry : weeklyGroups
        .entrySet()) {
      List<StockPriceOHLCEntity> weekData = entry.getValue();

      Long timestamp = entry.getKey(); // Week start timestamp
      Double open = weekData.get(0).getOpen(); // First day's open
      double high =
          weekData.stream().mapToDouble(o -> o.getHigh()).max().getAsDouble(); // Max high
      double low =
          weekData.stream().mapToDouble(o -> o.getLow()).min().getAsDouble(); // Min low
      double close = weekData.get(weekData.size() - 1).getClose(); // Last day's close

      weeklyData.add(TStockPriceOHLCDTO.builder().timestamp(timestamp).low(low)
          .high(high).open(open).close(close).build());
    }

    return weeklyData;
  }

  private List<TStockPriceOHLCDTO> convertMonthly(
      List<StockPriceOHLCEntity> entities) {
    // Map to group by month start timestamp
    Map<Long, List<StockPriceOHLCEntity>> monthlyGroups = new TreeMap<>();

    // Group data by month
    for (StockPriceOHLCEntity entity : entities) {
      LocalDateTime dateTime =
          LocalDateTime.ofInstant(Instant.ofEpochSecond(entity.getTimestamp()),
              ZoneId.of("Asia/Hong_Kong"));

      LocalDateTime monthStart = dateTime.withDayOfMonth(1).withHour(0)
          .withMinute(0).withSecond(0).withNano(0);
      Long monthStartTimestamp =
          monthStart.atZone(ZoneId.of("Asia/Hong_Kong")).toEpochSecond();

      monthlyGroups.computeIfAbsent(monthStartTimestamp, k -> new ArrayList<>())
          .add(entity);
    }

    // Aggregate into weekly OHLC
    List<TStockPriceOHLCDTO> monthlyData = new ArrayList<>();
    for (Map.Entry<Long, List<StockPriceOHLCEntity>> entry : monthlyGroups
        .entrySet()) {
      List<StockPriceOHLCEntity> monthData = entry.getValue();

      Long timestamp = entry.getKey(); // Week start timestamp
      Double open = monthData.get(0).getOpen(); // First day's open
      double high =
          monthData.stream().mapToDouble(o -> o.getHigh()).max().getAsDouble(); // Max high
      double low =
          monthData.stream().mapToDouble(o -> o.getLow()).min().getAsDouble(); // Min low
      double close = monthData.get(monthData.size() - 1).getClose(); // Last day's close

      monthlyData.add(TStockPriceOHLCDTO.builder().timestamp(timestamp).low(low)
          .high(high).open(open).close(close).build());
    }

    return monthlyData;
  }

}
