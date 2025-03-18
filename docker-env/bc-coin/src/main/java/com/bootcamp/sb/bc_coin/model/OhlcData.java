package com.bootcamp.sb.bc_coin.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OhlcData {
  private Long timestamp;
  private Double open;
  private Double high;
  private Double low;
  private Double close;

  public OhlcData(Double[] data) {
    this.timestamp = data[0].longValue();
    this.open = data[1];
    this.high = data[2];
    this.low = data[3];
    this.close = data[4];
  }
}
