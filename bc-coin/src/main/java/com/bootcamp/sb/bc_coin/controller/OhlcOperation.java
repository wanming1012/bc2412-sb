package com.bootcamp.sb.bc_coin.controller;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.bootcamp.sb.bc_coin.model.OhlcData;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface OhlcOperation {
  @CrossOrigin
  @GetMapping("/coin/ohlc/{coinId}")
  List<OhlcData> getOhlcData(@PathVariable String coinId);

  @CrossOrigin
  @GetMapping("/cache/coin/ohlc/{coinId}")
  List<OhlcData> getOhlcDataWithCache(@PathVariable String coinId) throws JsonProcessingException;
}
