package com.bootcamp.sb.final_project.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sb.final_project.controller.TStockPriceOHLCOperation;
import com.bootcamp.sb.final_project.dto.TStockPriceDTO;
import com.bootcamp.sb.final_project.dto.TStockPriceOHLCDTO;
import com.bootcamp.sb.final_project.model.TStockRecordType;
import com.bootcamp.sb.final_project.service.TStockPriceOHLCService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping(value = "/v1")
public class TStockPriceOHLCController implements TStockPriceOHLCOperation {
  @Autowired
  private TStockPriceOHLCService tStockPriceOHLCService;

  @Override
  public List<TStockPriceOHLCDTO> getOHLCData(String symbol,
      TStockRecordType type) throws JsonProcessingException {
    return this.tStockPriceOHLCService.getOHLCData(symbol, type);
  }

  public List<TStockPriceDTO> getMAClosePriceData(String symbol,
  TStockRecordType type, Integer window) throws JsonProcessingException {
    return this.tStockPriceOHLCService.getMAClosePriceData(symbol, type, window);
  }

}
