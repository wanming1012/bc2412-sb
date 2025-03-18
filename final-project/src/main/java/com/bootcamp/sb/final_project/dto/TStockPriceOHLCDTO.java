package com.bootcamp.sb.final_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class TStockPriceOHLCDTO {
  private Long timestamp;
  private Double low;
  private Double high;
  private Double open;
  private Double close;
  //private Long volume;
}
