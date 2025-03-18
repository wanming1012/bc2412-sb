package com.bootcamp.sb.bc_coin.service;

import java.util.List;
import com.bootcamp.sb.bc_coin.model.OhlcData;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface OhlcService {
  List<OhlcData> getOhlcData(String coinId);
  List<OhlcData> getOhlcDataWithCache(String coinId) throws JsonProcessingException;
}
