package com.bootcamp.sb.bc_coin.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sb.bc_coin.controller.OhlcOperation;
import com.bootcamp.sb.bc_coin.model.OhlcData;
import com.bootcamp.sb.bc_coin.service.OhlcService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/crypto/api/v1")
public class OhlcController implements OhlcOperation {
  @Autowired
  private OhlcService ohlcService;

  @Override
  public List<OhlcData> getOhlcData(String coinId) {
    return ohlcService.getOhlcData(coinId);
  }

  @Override
  public List<OhlcData> getOhlcDataWithCache(String coinId) throws JsonProcessingException {
    return ohlcService.getOhlcDataWithCache(coinId);
  }
}
