package com.bootcamp.demo.demo_sb_stockchart.controller.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bootcamp.demo.demo_sb_stockchart.controller.ViewOperation;
import com.bootcamp.demo.demo_sb_stockchart.model.TStockRecordType;

@Controller
@RequestMapping(value = "/v1")
public class ViewController implements ViewOperation {
  @Value("${spring.api.line}")
  private String linePath;

  @Value("${spring.api.candle}")
  private String candlePath;

  @Value("${spring.api.line-sma}")
  private String lineSMAPath;

  @Value("${spring.api.candle-sma}")
  private String candleSMAPath;

  @Override
  public String lineChart(String symbol, TStockRecordType type, Model model) {
    model.addAttribute("symbol", symbol);
    model.addAttribute("type", type.getValue());
    model.addAttribute("apiPath", linePath);
    model.addAttribute("smaApiPath", lineSMAPath);
    return "linechart";
  }

  @Override
  public String candleChart(String symbol, TStockRecordType type, Model model) {
    model.addAttribute("symbol", symbol);
    model.addAttribute("type", type.getValue());
    model.addAttribute("apiPath", candlePath);
    model.addAttribute("smaApiPath", candleSMAPath);
    return "candlechart";
  }
}
