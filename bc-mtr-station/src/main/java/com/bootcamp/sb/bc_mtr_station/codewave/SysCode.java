package com.bootcamp.sb.bc_mtr_station.codewave;

public enum SysCode {
  OK("0", "Success."),
  LINE_CODE_NOT_FOUND("1", "Line code not found."),
  INVALID_INPUT("2", "Invalid input."),
  RESTTEMPLATE_ERROR("3", "RestTemplate Error - JsonPlaceHolder"),
  STATION_CODE_NOT_FOUND("4", "Station code not found."),
  MTR_SERVICE_ERROR("5", "MTR Service Error.");
  
  String code;
  String message;
  
  private SysCode(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public String getCode() {
    return this.code;
  }

  public String getMessage() {
    return this.message;
  }
}
