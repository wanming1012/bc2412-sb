package com.bootcamp.sb.final_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class CachedTStockPriceOHLCDTO {
  private Long timestamp;
  private TStockPriceOHLCDTO[] data;
}
