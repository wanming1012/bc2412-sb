package com.bootcamp.sb.bc_coin.model.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bootcamp.sb.bc_coin.model.CoinMarket;
import com.bootcamp.sb.bc_coin.model.dto.CoinGeckoMarketDto;

@Component
public class CoreMapper {
  @Autowired
  private ModelMapper modelMapper;

  public CoinMarket map(CoinGeckoMarketDto dto) {
    return this.modelMapper.map(dto, CoinMarket.class);
  }
}