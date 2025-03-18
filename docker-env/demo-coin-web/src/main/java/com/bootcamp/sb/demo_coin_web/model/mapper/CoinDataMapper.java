package com.bootcamp.sb.demo_coin_web.model.mapper;

import org.springframework.stereotype.Component;
import com.bootcamp.sb.demo_coin_web.model.CoinData;
import com.bootcamp.sb.demo_coin_web.model.dto.CoinDataDto;

@Component
public class CoinDataMapper {
  public CoinData map(CoinDataDto dto) {
    return CoinData.builder() //
        .id(dto.getId()) //
        .image(dto.getImage()) //
        .name(dto.getName()) //
        .symbol(dto.getSymbol())  //
        .currentPrice(dto.getCurrentPrice()) //
        .priceChangePercentage24h(dto.getPriceChangePercentage24h()) //
        .marketCap(dto.getMarketCap())  //
        .lastUpdated(dto.getLastUpdated())  //
        .build();
  }
}
