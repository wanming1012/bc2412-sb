package com.bootcamp.demo.demo_sb_stockchart.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.demo.demo_sb_stockchart.model.TStockRecordType;

public interface ViewOperation {
  @GetMapping(value = "/linechart")
  String lineChart(@RequestParam String symbol, @RequestParam TStockRecordType type, Model model);

  @GetMapping(value = "/candlechart")
  String candleChart(@RequestParam String symbol, @RequestParam TStockRecordType type, Model model);
}
