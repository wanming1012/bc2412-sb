package com.bootcamp.sb.final_project.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface TStockOperation {
  @GetMapping(value = "/symbols")
  List<String> getSymbols() throws JsonProcessingException;
}
