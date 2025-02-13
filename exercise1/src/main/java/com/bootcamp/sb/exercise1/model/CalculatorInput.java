package com.bootcamp.sb.exercise1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CalculatorInput {
  private String x;
  private String y;
  private Operation operation;
}
