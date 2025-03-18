package com.bootcamp.sb.final_project.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.sb.final_project.dto.TStockPriceDTO;
import com.bootcamp.sb.final_project.model.TStockRecordType;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface TStockPriceOperation {
  @GetMapping(value = "/prices")
  @CrossOrigin
  List<TStockPriceDTO> getPrices(@RequestParam String symbol,
      @RequestParam TStockRecordType type) throws JsonProcessingException;

  @GetMapping(value = "/maprices")
  @CrossOrigin
  List<TStockPriceDTO> getMAPrices(@RequestParam String symbol,
      @RequestParam TStockRecordType type, @RequestParam Integer window) throws JsonProcessingException;

  @GetMapping(value = "/systemtime")
  LocalDateTime getSystemTime(@RequestParam String symbol)
      throws JsonProcessingException;

  @GetMapping(value = "/holiday")
  Boolean isHoliday(@RequestParam LocalDate date)
      throws JsonProcessingException;
}
