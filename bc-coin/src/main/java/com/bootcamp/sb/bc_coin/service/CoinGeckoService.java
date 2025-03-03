package com.bootcamp.sb.bc_coin.service;

import java.util.List;
import com.bootcamp.sb.bc_coin.model.CoinMarket;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface CoinGeckoService {
  List<CoinMarket> getCoinMarket();
  List<CoinMarket> getCoinMarketWithCache() throws JsonProcessingException;
}