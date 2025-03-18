package com.bootcamp.sb.final_project.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sb.final_project.controller.QuoteOperation;
import com.bootcamp.sb.final_project.lib.YahooFinanceManager;
import com.bootcamp.sb.final_project.model.dto.YahooFinanceDto;
import com.bootcamp.sb.final_project.model.dto.YahooFinanceOHLCDto;

@RestController
@RequestMapping(value = "/v1")
public class QuoteController implements QuoteOperation {
  @Autowired
  private YahooFinanceManager yahooFinanceManager;

  @Override
  public YahooFinanceDto getQuote(List<String> symbols) {
    return this.yahooFinanceManager.getQuote(symbols);
  }

  @Override
  public YahooFinanceOHLCDto getOHLC(@RequestParam String symbol, @RequestParam Long start, @RequestParam Long end, @RequestParam String interval) {
    return this.yahooFinanceManager.getOHLC(symbol, start, end, interval);
  }

}
