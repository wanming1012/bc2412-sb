package com.bootcamp.sb.final_project.service;

import com.bootcamp.sb.final_project.model.TStockRecordType;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface YahooFinanceService {
  void fetchStockPrice(TStockRecordType type) throws JsonProcessingException;
  void fetchStockPriceHistory() throws JsonProcessingException;
}
