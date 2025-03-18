package com.bootcamp.sb.final_project.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import com.bootcamp.sb.final_project.dto.TStockPriceDTO;
import com.bootcamp.sb.final_project.entity.StockPriceEntity;
import com.bootcamp.sb.final_project.model.TStockRecordType;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface TStockPriceService {
  StockPriceEntity create(StockPriceEntity stockPriceEntity);
  Optional<LocalDateTime> getSystemTime(String symbol) throws JsonProcessingException;
  List<TStockPriceDTO> getPriceData(String symbol, TStockRecordType type) throws JsonProcessingException;
  // boolean isDataExist(String symbol, TStockRecordType type, Long regularMarketTime);
  void setSystemTime(String symbol, LocalDate date) throws JsonProcessingException;
  List<TStockPriceDTO> getMAPriceData(String symbol, TStockRecordType type, int window) throws JsonProcessingException;

}
