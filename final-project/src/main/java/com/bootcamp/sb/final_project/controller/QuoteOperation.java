package com.bootcamp.sb.final_project.controller;

import com.bootcamp.sb.final_project.model.dto.YahooFinanceDto;
import com.bootcamp.sb.final_project.model.dto.YahooFinanceOHLCDto;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


public interface QuoteOperation {
  @GetMapping(value = "/quote")
  YahooFinanceDto getQuote(@RequestParam List<String> symbols);

  @GetMapping(value = "/yahoo-ohlc")
  YahooFinanceOHLCDto getOHLC(@RequestParam String symbol, @RequestParam Long start, @RequestParam Long end, @RequestParam String interval);
}
