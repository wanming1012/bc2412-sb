package com.bootcamp.sb.final_project.entity.mapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.bootcamp.sb.final_project.dto.TStockPriceDTO;
import com.bootcamp.sb.final_project.dto.TStockPriceOHLCDTO;
import com.bootcamp.sb.final_project.entity.StockPriceEntity;
import com.bootcamp.sb.final_project.entity.StockPriceOHLCEntity;
import com.bootcamp.sb.final_project.model.TStockRecordType;
import com.bootcamp.sb.final_project.model.dto.YahooFinanceDto;

@Component
public class EntityMapper {
  public List<StockPriceEntity> map(YahooFinanceDto dto,
      TStockRecordType type) {
    List<StockPriceEntity> stockPrices = new ArrayList<>();
    for (YahooFinanceDto.ResultWrapper.StockQuoteDto result : dto
        .getQuoteResponse().getResult()) {
      stockPrices.add(map(result, type));
    }
    return stockPrices;
  }

  private StockPriceEntity map(YahooFinanceDto.ResultWrapper.StockQuoteDto dto,
      TStockRecordType type) {
    return StockPriceEntity.builder().symbol(dto.getSymbol())
        .regularMarketTime(dto.getRegularMarketTime())
        .regularMarketPrice(dto.getRegularMarketPrice())
        .regularMarketChangePercent(dto.getRegularMarketChangePercent())
        .bid(dto.getBid()).bidSize(dto.getBidSize()).ask(dto.getAsk())
        .askSize(dto.getAskSize()).apiDateTime(LocalDateTime.now())
        .marketTime(convert(dto.getRegularMarketTime())).type(type.getValue())
        .build();
  }

  public LocalDateTime convert(Long unixTimeInSeconds) {
    Instant instant = Instant.ofEpochSecond(unixTimeInSeconds);
    return LocalDateTime.ofInstant(instant, ZoneId.of("Asia/Hong_Kong"));
  }

  public TStockPriceDTO map(StockPriceEntity entity) {
    // return TStockPriceDTO.builder().symbol(entity.getSymbol())
    // .regularMarketTime(entity.getRegularMarketTime())
    // .regularMarketPrice(entity.getRegularMarketPrice())
    // .regularMarketChangePercent(entity.getRegularMarketChangePercent())
    // .bid(entity.getBid()).ask(entity.getAsk()).bidSize(entity.getBidSize())
    // .askSize(entity.getAskSize()).marketTime(entity.getMarketTime())
    // .build();
    return TStockPriceDTO.builder()
        .regularMarketTime(entity.getRegularMarketTime())
        .regularMarketPrice(entity.getRegularMarketPrice()).build();
  }

  public TStockPriceOHLCDTO map(StockPriceOHLCEntity entity) {
    return TStockPriceOHLCDTO.builder().timestamp(entity.getTimestamp())
        .open(entity.getOpen()).close(entity.getClose()).high(entity.getHigh())
        .low(entity.getLow()).build();
  }
}
