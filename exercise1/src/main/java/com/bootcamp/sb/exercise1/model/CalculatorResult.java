package com.bootcamp.sb.exercise1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class CalculatorResult {
  private String x;
  private String y;
  private String operation;
  private String result;

  public CalculatorResult(CalculatorInput input, String result) {
    this(input.getX(), input.getY(), input.getOperation().name(), result);
  }
}
