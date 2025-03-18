package com.bootcamp.sb.final_project.controller.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sb.final_project.controller.TStockPriceOperation;
import com.bootcamp.sb.final_project.dto.TStockPriceDTO;
import com.bootcamp.sb.final_project.model.TStockRecordType;
import com.bootcamp.sb.final_project.service.HolidayCheckerService;
import com.bootcamp.sb.final_project.service.TStockPriceService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping(value = "/v1")
public class TStockPriceController implements TStockPriceOperation {
  @Autowired
  private TStockPriceService tStockPriceService;

  @Autowired
  private HolidayCheckerService holidayCheckerService;

  @Override
  public List<TStockPriceDTO> getPrices(String symbol, TStockRecordType type) throws JsonProcessingException {
    return this.tStockPriceService.getPriceData(symbol, type);
  }

  @Override
  public List<TStockPriceDTO> getMAPrices(String symbol, TStockRecordType type, Integer window) throws JsonProcessingException {
    return this.tStockPriceService.getMAPriceData(symbol, type, window);
  }

  @Override
  public LocalDateTime getSystemTime(String symbol) throws JsonProcessingException {
    return this.tStockPriceService.getSystemTime(symbol).get();
  }

  @Override
  public Boolean isHoliday(LocalDate date) throws JsonProcessingException {
    return this.holidayCheckerService.isHoliday(date);
  }
}
