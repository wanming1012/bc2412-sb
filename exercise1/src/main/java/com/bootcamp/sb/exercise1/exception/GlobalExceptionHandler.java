package com.bootcamp.sb.exercise1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.bootcamp.sb.exercise1.model.ErrorResult;

@RestControllerAdvice // bean
public class GlobalExceptionHandler {
  @ExceptionHandler(value = RuntimeException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResult handleError(RuntimeException e) {
    return new ErrorResult(9, "Invalid Input.");
  }
}
