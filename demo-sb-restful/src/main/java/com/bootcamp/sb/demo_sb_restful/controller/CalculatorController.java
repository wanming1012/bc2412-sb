package com.bootcamp.sb.demo_sb_restful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sb.demo_sb_restful.model.Operation;
import com.bootcamp.sb.demo_sb_restful.service.CalculatorService;

@RestController
@RequestMapping(value = "/v1")
public class CalculatorController {
  @Autowired
  CalculatorService calculatorService;

  @GetMapping(value = "/operation/{operation}")
  public Double operate(@PathVariable Operation operation,
      @RequestParam Double a, @RequestParam Double b) {
    return this.calculatorService.operate(operation, a, b);
  };
}

  // @GetMapping(value = "/add")
  // public static Double add(@RequestParam Double a, @RequestParam Double b) {
  //   return BigDecimal.valueOf(a).add(BigDecimal.valueOf(b)).doubleValue();
  // }

  // @GetMapping(value = "/subtract")
  // public static Double subtract(@RequestParam Double a,
  //     @RequestParam Double b) {
  //   return BigDecimal.valueOf(a).subtract(BigDecimal.valueOf(b)).doubleValue();
  // }

  // @GetMapping(value = "/multiply")
  // public static Double multiply(@RequestParam Double a,
  //     @RequestParam Double b) {
  //   return BigDecimal.valueOf(a).multiply(BigDecimal.valueOf(b)).doubleValue();
  // }

  // @GetMapping(value = "/divide")
  // public static Double divide(@RequestParam Double a, @RequestParam Double b) {
  //   if (b.equals(0.0))
  //     return null;
  //   return BigDecimal.valueOf(a).divide(BigDecimal.valueOf(b)).doubleValue();
  // }
