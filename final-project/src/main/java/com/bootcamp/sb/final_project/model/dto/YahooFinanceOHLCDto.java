package com.bootcamp.sb.final_project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class YahooFinanceOHLCDto {
  private Chart chart;

  @Getter
  @Builder
  @ToString
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Chart {
    private Result[] result;
    private Object error;

    @Getter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Result {
      private Meta meta;
      private Long[] timestamp;
      private Indicators indicators;
      
      @Getter
      @Builder
      @ToString
      @AllArgsConstructor
      @NoArgsConstructor
      public static class Meta {
        private String currency;
        private String symbol;
        private String exchangeName;
        private String fullExchangeName;
        private String instrumentType;
        private Long firstTradeDate;
        private Long regularMarketTime;
        private Boolean hasPrePostMarketData;
        private Long gmtoffset;
        private String timezone;
        private String exchangeTimezoneName;
        private Double regularMarketPrice;
        private Double fiftyTwoWeekHigh;
        private Double regularMarketDayHigh;
        private Double regularMarketDayLow;
        private Long regularMarketVolume;
        private String longName;
        private String shortName;
        private Double chartPreviousClose;
        private Integer priceHint;
        private CurrentTradingPeriod currentTradingPeriod;
        private String dataGranularity;
        private String range;
        private String[] validRanges;
        
        @Getter
        @Builder
        @ToString
        @AllArgsConstructor
        @NoArgsConstructor
        public static class CurrentTradingPeriod {
          private Period pre;
          private Period regular;
          private Period post;

          @Getter
          @Builder
          @ToString
          @AllArgsConstructor
          @NoArgsConstructor
          public static class Period {
            private String timezone;
            private Long end;
            private Long start;
            private Long gmtoffset;
          }
        }
      }

      @Getter
      @Builder
      @ToString
      @AllArgsConstructor
      @NoArgsConstructor
      public static class Indicators {
        private Quote[] quote;
        private Adjclose[] adjclose;

        @Getter
        @Builder
        @ToString
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Quote {
          private Long[] volume;
          private Double[] open;
          private Double[] close;
          private Double[] high;
          private Double[] low;
        }

        @Getter
        @Builder
        @ToString
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Adjclose {
          private Double[] adjclose;
        }
      }
    }
  }
}
