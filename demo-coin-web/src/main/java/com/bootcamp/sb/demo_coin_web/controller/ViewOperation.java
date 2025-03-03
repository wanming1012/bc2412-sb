package com.bootcamp.sb.demo_coin_web.controller;

import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bootcamp.sb.demo_coin_web.model.CoinData;

public interface ViewOperation {
  @GetMapping(value = "/coin-data")
  public String getCoins(Model model);

  @GetMapping(value = "/coin-data/refresh")
  @ResponseBody
  public List<CoinData> refreshCoins();
}
