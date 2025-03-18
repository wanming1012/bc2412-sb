package com.bootcamp.sb.final_project.controller;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.sb.final_project.dto.TStockPriceDTO;
import com.bootcamp.sb.final_project.dto.TStockPriceOHLCDTO;
import com.bootcamp.sb.final_project.model.TStockRecordType;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface TStockPriceOHLCOperation {
  @GetMapping(value = "/ohlc")
  @CrossOrigin
  List<TStockPriceOHLCDTO> getOHLCData(@RequestParam String symbol,
      @RequestParam TStockRecordType type) throws JsonProcessingException;

  @GetMapping(value = "/maohlc")
  @CrossOrigin
  List<TStockPriceDTO> getMAClosePriceData(@RequestParam String symbol,
  @RequestParam TStockRecordType type, @RequestParam Integer window) throws JsonProcessingException;
}
