package com.bootcamp.sb.demo_coin_web.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.bootcamp.sb.demo_coin_web.controller.ViewOperation;
import com.bootcamp.sb.demo_coin_web.model.CoinData;
import com.bootcamp.sb.demo_coin_web.service.ApiService;

@Controller
public class ViewController implements ViewOperation {
  @Autowired
  ApiService apiService;

  public String getCoins(Model model) {
    model.addAttribute("coinList", this.apiService.fetchCoinData());
    return "coin-data";
  }
  
  public List<CoinData> refreshCoins() {
    return this.apiService.fetchCoinData();
  }
}
