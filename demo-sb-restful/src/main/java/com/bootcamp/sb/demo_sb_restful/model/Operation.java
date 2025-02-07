package com.bootcamp.sb.demo_sb_restful.model;

public enum Operation {
  ADD, SUBTRACT, MULTIPLY, DIVIDE,;

  public String lowercase() {
    return this.name().toLowerCase();
  }
}
