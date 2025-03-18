package com.bootcamp.sb.final_project.dto;

//import java.time.LocalDateTime;
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
public class TStockPriceDTO {
  // private String symbol;
  private Long regularMarketTime;
  private Double regularMarketPrice;
  // private Double regularMarketChangePercent;
  // private Double bid;
  // private Double ask;
  // private Integer bidSize;
  // private Integer askSize;
  // private LocalDateTime marketTime;
}
