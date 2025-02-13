package com.bootcamp.sb.exercise1.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import org.springframework.stereotype.Service;
import com.bootcamp.sb.exercise1.exception.InvalidInputException;
import com.bootcamp.sb.exercise1.model.CalculatorInput;
import com.bootcamp.sb.exercise1.model.CalculatorResult;

@Service
public class CalculatorService {
  public CalculatorResult operate(CalculatorInput input) {
    String result = switch(input.getOperation()) {
      case ADD -> new BigDecimal(input.getX()).add(new BigDecimal(input.getY())).toString();
      case SUB -> new BigDecimal(input.getX()).subtract(new BigDecimal(input.getY())).toString();
      case MUL -> new BigDecimal(input.getX()).multiply(new BigDecimal(input.getY())).toString();
      case DIV -> {
        if (new BigDecimal(input.getY()).compareTo(new BigDecimal(0.0)) == 0)
          throw new InvalidInputException();
        yield new BigDecimal(input.getX()).divide(new BigDecimal(input.getY()), new MathContext(5, RoundingMode.HALF_UP)).toString();
      }
    };
    return new CalculatorResult(input, result);
  }
}
