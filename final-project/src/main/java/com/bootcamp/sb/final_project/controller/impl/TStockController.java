package com.bootcamp.sb.final_project.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sb.final_project.controller.TStockOperation;
import com.bootcamp.sb.final_project.service.TStockService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping(value = "/v1")
public class TStockController implements TStockOperation {
  @Autowired
  private TStockService tStockService;

  @Override
  public List<String> getSymbols() throws JsonProcessingException {
    return this.tStockService.findAllSymbols();
  }
}
