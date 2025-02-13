package com.bootcamp.sb.bc_forum.codewave;

public enum SysCode {
  USER_ID_NOT_FOUND("1", "User not found."),
  INVALID_USER_ID("2", "Invalid input."),
  RESTTEMPLATE_ERROR("3", "RestTemplate Error - JsonPlaceHolder"),;

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
