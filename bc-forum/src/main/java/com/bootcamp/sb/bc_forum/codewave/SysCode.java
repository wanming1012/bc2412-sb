package com.bootcamp.sb.bc_forum.codewave;

public enum SysCode {
  OK("0", "Success."),
  USER_ID_NOT_FOUND("1", "User not found."),
  INVALID_INPUT("2", "Invalid input."),
  RESTTEMPLATE_ERROR("3", "RestTemplate Error - JsonPlaceHolder"),
  POST_ID_NOT_FOUND("4", "Post not found."),
  COMMENT_ID_NOT_FOUND("5", "Comment not found"),
  ADDRESS_ID_NOT_FOUND("6", "Address not found"),
  GEO_ID_NOT_FOUND("7", "Geo not found"),
  COMPANY_ID_NOT_FOUND("8", "Company not found");

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
