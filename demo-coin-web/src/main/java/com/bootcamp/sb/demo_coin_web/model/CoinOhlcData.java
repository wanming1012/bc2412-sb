package com.bootcamp.sb.demo_coin_web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CoinOhlcData {
  private Long timestamp;
  private Double open;
  private Double high;
  private Double low;
  private Double close;
}
