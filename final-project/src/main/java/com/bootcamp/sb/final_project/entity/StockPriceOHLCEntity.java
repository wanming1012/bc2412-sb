package com.bootcamp.sb.final_project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TSTOCKS_PRICE_OHLC")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockPriceOHLCEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String symbol;
  private Long timestamp;
  private Double low;
  private Double high;
  private Double open;
  private Double close;
  private Long volume;
}
