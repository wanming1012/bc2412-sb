package com.bootcamp.sb.demo_sb_customer.codewave;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class GlobalExceptionHandler {
  @ExceptionHandler(BusinessException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Void> handleBusinessException(BusinessException e) {
    return ApiResp.<Void>builder()
        .sysCode(e.getSysCode())
        .build();
  }
}
