package com.bootcamp.sb.exercise1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sb.exercise1.model.CalculatorInput;
import com.bootcamp.sb.exercise1.model.CalculatorResult;
import com.bootcamp.sb.exercise1.service.CalculatorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class CalculatorController {
  @Autowired
  CalculatorService calculatorService;

  @GetMapping(value = "/operation")
  public CalculatorResult operate1(@RequestParam String x, @RequestParam String y, @RequestParam String operation) {
    return this.calculatorService.operate(new CalculatorInput(x, y, operation));
  };

  @GetMapping(value = "/operation/{x}/{y}/{operation}")
  public CalculatorResult operate2(@PathVariable String x, @PathVariable String y, @PathVariable String operation) {
    return this.calculatorService.operate(new CalculatorInput(x, y, operation));
  }

  @PostMapping(value = "/operation")
  public CalculatorResult operate(@RequestBody CalculatorInput input) {
      return this.calculatorService.operate(input);
  }
  
}
