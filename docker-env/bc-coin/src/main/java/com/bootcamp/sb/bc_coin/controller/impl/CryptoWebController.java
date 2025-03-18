package com.bootcamp.sb.bc_coin.controller.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sb.bc_coin.controller.CryptoWebOperation;
import com.bootcamp.sb.bc_coin.dto.CryptoWebDTO;
import com.bootcamp.sb.bc_coin.dto.mapper.DTOMapper;
import com.bootcamp.sb.bc_coin.service.CoinGeckoService;
import com.fasterxml.jackson.core.JsonProcessingException;

// http://localhost:8080/crypto/api/v1/coin/market
@RestController
@RequestMapping("/crypto/api/v1")
public class CryptoWebController implements CryptoWebOperation {
  @Autowired
  private CoinGeckoService coinGeckoService;

  @Autowired
  private DTOMapper dtoMapper;

  @Override
  public List<CryptoWebDTO> getCoinMarket() {
    return coinGeckoService.getCoinMarket() //
        .stream() //
        .map(e -> dtoMapper.map(e)) //
        .collect(Collectors.toList());
  }

  @Override
  public List<CryptoWebDTO> getCoinMarketWithCache() throws JsonProcessingException {
    return coinGeckoService.getCoinMarketWithCache() //
        .stream() //
        .map(e -> dtoMapper.map(e)) //
        .collect(Collectors.toList());
  }
}