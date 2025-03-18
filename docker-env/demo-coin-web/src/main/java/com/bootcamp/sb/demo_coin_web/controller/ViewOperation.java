package com.bootcamp.sb.demo_coin_web.controller;

import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bootcamp.sb.demo_coin_web.model.CoinData;

public interface ViewOperation {
  @GetMapping(value = "/coin-data")
  String getCoins(Model model);

  @GetMapping(value = "/coin-data/refresh")
  @ResponseBody
  List<CoinData> refreshCoins();

  @GetMapping(value = "/coin-data/chart/{coinId}")
  String getOhlc(@PathVariable String coinId, Model model);
}
