package com.bootcamp.sb.bc_coin.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bootcamp.sb.bc_coin.dto.CryptoWebDTO;
import com.bootcamp.sb.bc_coin.model.CoinMarket;

@Component
public class DTOMapper {
  @Autowired
  private ModelMapper modelMapper;

  public CryptoWebDTO map(CoinMarket coinMarket) {
    return this.modelMapper.map(coinMarket, CryptoWebDTO.class);
  }
}