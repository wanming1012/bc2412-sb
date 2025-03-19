package com.bootcamp.sb.final_project.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import com.bootcamp.sb.final_project.dto.TStockPriceDTO;
import com.bootcamp.sb.final_project.dto.TStockPriceOHLCDTO;
import com.bootcamp.sb.final_project.entity.StockPriceOHLCEntity;
import com.bootcamp.sb.final_project.model.TStockRecordType;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface TStockPriceOHLCService {
  StockPriceOHLCEntity create(StockPriceOHLCEntity stockPriceOHLCEntity);
  Optional<Long> getStartDate(String symbol);
  Optional<Long> getEndDate(String symbol);
  List<TStockPriceOHLCDTO> getOHLCData(String symbol, TStockRecordType type, int window) throws JsonProcessingException;
  List<TStockPriceDTO> getMAClosePriceData(String symbol, TStockRecordType type, int window) throws JsonProcessingException;

  LocalDate convert(Long timestamp);
}
