package com.bootcamp.sb.final_project.service;

import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface TStockService {
  List<String> findAllSymbols() throws JsonProcessingException;
}
