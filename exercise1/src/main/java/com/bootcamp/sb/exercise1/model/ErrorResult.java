package com.bootcamp.sb.exercise1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResult {
  private Integer code;
  private String message;
}
