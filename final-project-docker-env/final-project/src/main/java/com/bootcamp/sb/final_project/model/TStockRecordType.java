package com.bootcamp.sb.final_project.model;

public enum TStockRecordType {
  FIVE_MINUTES("5M"), DAILY("D"), WEEKLY("W"), MONTHLY("M");

  private final String value;

  private TStockRecordType(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }

  public static TStockRecordType fromValue(String value) {
    for (TStockRecordType type : TStockRecordType.values()) {
      if (type.getValue().equalsIgnoreCase(value)) {
        return type;
      }
    }
    throw new IllegalArgumentException("Unknnown type: " + value);
  }
}
