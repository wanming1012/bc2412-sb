package com.bootcamp.sb.bc_mtr_station.model;

public enum Line {
  AEL("Airport Express"),
  TCL("Tung Chung Line"),
  TML("Tuen Ma Line"),
  TKL("Tseung Kwan O Line"),
  EAL("East Rail Line"),
  SIL("South Island Line"),
  TWL("Tsuen Wan Line"),
  ISL("Island Line"),
  KTL("Kwun Tong Line"),
  ;

  private String description;

  private Line(String description) {
    this.description = description;
  }

  public String getDescription() {
    return this.description;
  }
}
