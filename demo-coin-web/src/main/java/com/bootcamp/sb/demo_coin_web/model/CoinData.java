package com.bootcamp.sb.demo_coin_web.model;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoinData {
  private String id;
  private String image;
  private String name;
  private String symbol;
  private Double currentPrice;
  private Double priceChangePercentage24h;
  private Double marketCap;
  private LocalDateTime lastUpdated;
}
