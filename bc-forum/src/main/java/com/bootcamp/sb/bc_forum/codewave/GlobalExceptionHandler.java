package com.bootcamp.sb.bc_forum.codewave;

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
  public ErrorResult handleBusinessException(BusinessException e) {
    SysCode sysCode = e.getSysCode();
    return new ErrorResult(sysCode.getCode(), sysCode.getMessage());
  }

  @ExceptionHandler({IllegalArgumentException.class, MissingServletRequestParameterException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResult handleInvalidInput() {
    SysCode sysCode = SysCode.INVALID_USER_ID;
    return new ErrorResult(sysCode.getCode(), sysCode.getMessage());
  }

  @ExceptionHandler(HttpClientErrorException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResult handleRestTemplateError() {
    SysCode sysCode = SysCode.RESTTEMPLATE_ERROR;
    return new ErrorResult(sysCode.getCode(), sysCode.getMessage());
  }
}
