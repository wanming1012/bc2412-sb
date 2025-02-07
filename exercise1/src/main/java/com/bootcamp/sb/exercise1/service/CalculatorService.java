package com.bootcamp.sb.exercise1.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import org.springframework.stereotype.Service;
import com.bootcamp.sb.exercise1.model.CalculatorInput;
import com.bootcamp.sb.exercise1.model.CalculatorResult;

@Service
public class CalculatorService {
  public CalculatorResult operate(CalculatorInput input) {
    String result;
    switch(input.getOperation()) {
      case "add" -> result = new BigDecimal(input.getX()).add(new BigDecimal(input.getY())).toString();
      case "sub" -> result = new BigDecimal(input.getX()).subtract(new BigDecimal(input.getY())).toString();
      case "mul" -> result = new BigDecimal(input.getX()).multiply(new BigDecimal(input.getY())).toString();
      case "div" -> result = new BigDecimal(input.getX()).divide(new BigDecimal(input.getY()), new MathContext(5, RoundingMode.HALF_UP)).toString();
      default -> throw new RuntimeException();
    };
    return new CalculatorResult(input.getX(), input.getY(), input.getOperation(), result);
  }
}
