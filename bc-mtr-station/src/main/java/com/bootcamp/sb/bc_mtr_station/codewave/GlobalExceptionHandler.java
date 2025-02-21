package com.bootcamp.sb.bc_mtr_station.codewave;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(BusinessException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Void> handleBusinessException(BusinessException e) {
    return ApiResp.<Void>builder()
        .sysCode(e.getSysCode())
        .build();
  }

  @ExceptionHandler({IllegalArgumentException.class, MissingServletRequestParameterException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Void> handleInvalidInput() {
    return ApiResp.<Void>builder().sysCode(SysCode.INVALID_INPUT).build();
  }

  @ExceptionHandler(HttpClientErrorException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Void> handleRestTemplateError() {
    return ApiResp.<Void>builder().sysCode(SysCode.RESTTEMPLATE_ERROR).build();
  }
}
