package com.bootcamp.sb.demo_sb_restful.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import org.springframework.stereotype.Service;
import com.bootcamp.sb.demo_sb_restful.exception.BusinessException;
import com.bootcamp.sb.demo_sb_restful.model.Operation;

@Service
public class CalculatorService {
  public Double operate(Operation operation, Double a, Double b) {
    return switch(operation) {
      case ADD -> BigDecimal.valueOf(a).add(BigDecimal.valueOf(b), new MathContext(6, RoundingMode.HALF_UP)).doubleValue();
      case SUBTRACT -> BigDecimal.valueOf(a).subtract(BigDecimal.valueOf(b), new MathContext(6, RoundingMode.HALF_UP)).doubleValue();
      case MULTIPLY -> BigDecimal.valueOf(a).multiply(BigDecimal.valueOf(b), new MathContext(6, RoundingMode.HALF_UP)).doubleValue();
      case DIVIDE -> {
        if (b == 0.0)
          throw new BusinessException("CalculatorService.divide() / zero." + "a=" + a + ", b=" + b);
        yield BigDecimal.valueOf(a).divide(BigDecimal.valueOf(b), new MathContext(6, RoundingMode.HALF_UP)).doubleValue();
      }
    };
  }
}
