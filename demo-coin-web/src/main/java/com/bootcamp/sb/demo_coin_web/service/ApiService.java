package com.bootcamp.sb.demo_coin_web.service;

import java.util.List;
import com.bootcamp.sb.demo_coin_web.model.CoinData;

public interface ApiService {
  List<CoinData> fetchCoinData();
}
