package com.bootcamp.sb.final_project.service.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.sb.final_project.entity.StockPriceEntity;
import com.bootcamp.sb.final_project.entity.StockPriceOHLCEntity;
import com.bootcamp.sb.final_project.entity.mapper.EntityMapper;
import com.bootcamp.sb.final_project.lib.YahooFinanceManager;
import com.bootcamp.sb.final_project.model.TStockRecordType;
import com.bootcamp.sb.final_project.model.dto.YahooFinanceDto;
import com.bootcamp.sb.final_project.model.dto.YahooFinanceOHLCDto;
import com.bootcamp.sb.final_project.service.TStockPriceOHLCService;
import com.bootcamp.sb.final_project.service.TStockPriceService;
import com.bootcamp.sb.final_project.service.TStockService;
import com.bootcamp.sb.final_project.service.YahooFinanceService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class YahooFinanceServiceImpl implements YahooFinanceService {
  @Autowired
  private TStockService tStockService;

  @Autowired
  private TStockPriceService tStockPriceService;

  @Autowired
  private TStockPriceOHLCService tStockPriceOHLCService;

  @Autowired
  private YahooFinanceManager yahooFinanceManager;

  @Autowired
  private EntityMapper entityMapper;

  @Override
  public void fetchStockPrice(TStockRecordType type)
      throws JsonProcessingException {
    List<String> symbols = this.tStockService.findAllSymbols();

    YahooFinanceDto dto = this.yahooFinanceManager.getQuote(symbols);

    List<StockPriceEntity> prices = this.entityMapper.map(dto, type);

    for (StockPriceEntity price : prices) {
      this.tStockPriceService.create(price);
    }
  }

  @Override
  public void fetchStockPriceHistory() throws JsonProcessingException {
    List<String> symbols = this.tStockService.findAllSymbols();

    for (String symbol : symbols) {
      LocalDate today = LocalDate.now();
      Long startDate = today.minusYears(10).atStartOfDay()
          .atZone(ZoneId.of("Asia/Hong_Kong")).toEpochSecond();
      Long endDate = today.atStartOfDay().atZone(ZoneId.of("Asia/Hong_Kong"))
          .toEpochSecond();

      Optional<Long> dbStartDate =
          this.tStockPriceOHLCService.getStartDate(symbol);
      if (dbStartDate.isPresent() && dbStartDate.get() <= startDate) {
        Optional<Long> dbEndDate =
            this.tStockPriceOHLCService.getEndDate(symbol);
        if (dbEndDate.isPresent()) {
          LocalDate temp =
              LocalDateTime.ofInstant(Instant.ofEpochSecond(dbEndDate.get()),
                  ZoneId.of("Asia/Hong_Kong")).toLocalDate();
          startDate = temp.plusDays(1).atStartOfDay()
              .atZone(ZoneId.of("Asia/Hong_Kong")).toEpochSecond();
        }
      }

      if (startDate >= endDate) {
        continue;
      }

      YahooFinanceOHLCDto dto =
          this.yahooFinanceManager.getOHLC(symbol, startDate, endDate, "1d");
      Long[] timestamps = dto.getChart().getResult()[0].getTimestamp();
      Double[] opens =
          dto.getChart().getResult()[0].getIndicators().getQuote()[0].getOpen();
      Double[] closes =
          dto.getChart().getResult()[0].getIndicators().getQuote()[0]
              .getClose();
      Double[] lows =
          dto.getChart().getResult()[0].getIndicators().getQuote()[0].getLow();
      Double[] highs =
          dto.getChart().getResult()[0].getIndicators().getQuote()[0].getHigh();
      Long[] volumes =
          dto.getChart().getResult()[0].getIndicators().getQuote()[0]
              .getVolume();
      for (int i = 0; i < timestamps.length; i++) {
        tStockPriceOHLCService
            .create(StockPriceOHLCEntity.builder().symbol(symbol)
                .timestamp(timestamps[i]).open(opens[i]).close(closes[i])
                .low(lows[i]).high(highs[i]).volume(volumes[i]).build());
      }
    }
  }
}
