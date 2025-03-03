package com.bootcamp.sb.bc_coin.controller;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import com.bootcamp.sb.bc_coin.dto.CryptoWebDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface CryptoWebOperation {
  @CrossOrigin
  @GetMapping("/coin/market")
  List<CryptoWebDTO> getCoinMarket();

  @CrossOrigin
  @GetMapping("/cache/coin/market")
  List<CryptoWebDTO> getCoinMarketWithCache() throws JsonProcessingException;
}