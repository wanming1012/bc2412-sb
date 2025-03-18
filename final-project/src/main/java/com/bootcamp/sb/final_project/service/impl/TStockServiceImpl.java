package com.bootcamp.sb.final_project.service.impl;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.sb.final_project.lib.RedisManager;
import com.bootcamp.sb.final_project.repository.TStockRepository;
import com.bootcamp.sb.final_project.service.TStockService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class TStockServiceImpl implements TStockService {
  @Autowired
  private RedisManager redisManager;

  @Autowired
  private TStockRepository tStockRepository;

  @Override
  public List<String> findAllSymbols() throws JsonProcessingException {
    String[] cachedSymbols =
        this.redisManager.get("STOCK-LIST", String[].class);

    if (cachedSymbols != null) {
      return Arrays.asList(cachedSymbols);
    }

    List<String> symbols = this.tStockRepository.findAll().stream()
        .map(e -> e.getSymbol()).toList();

    this.redisManager.set("STOCK-LIST", symbols.toArray(), Duration.ofDays(1));

    return symbols;
  }
}
