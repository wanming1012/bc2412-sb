package com.bootcamp.sb.final_project.lib;

import java.util.List;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.sb.final_project.model.dto.YahooFinanceDto;
import com.bootcamp.sb.final_project.model.dto.YahooFinanceOHLCDto;

public class YahooFinanceManager {
  private final CrumbManager crumbManager;
  private final RestTemplate restTemplate;

  public YahooFinanceManager() {
    this.restTemplate = new YahooRestTemplate();
    this.crumbManager = new CrumbManager(this.restTemplate);
  }

  public YahooFinanceDto getQuote(List<String> symbols) {
    String url =
        UriComponentsBuilder.fromUriString("https://query1.finance.yahoo.com").pathSegment("v7")
            .path("/finance/quote")
            .queryParam("symbols", String.join(",", symbols))
            .queryParam("crumb", this.crumbManager.getKey())
            .build().toUriString();

    return this.restTemplate.getForObject(url, YahooFinanceDto.class);
  }

  public YahooFinanceOHLCDto getOHLC(String symbol, Long startDate, Long endDate, String interval) {
    String url =
        UriComponentsBuilder.fromUriString("https://query1.finance.yahoo.com").pathSegment("v8")
            .path("/finance/chart/{symbol}")
            .queryParam("period1", startDate)
            .queryParam("period2", endDate)
            .queryParam("interval", interval)
            .queryParam("events", "history")
            .queryParam("crumb", this.crumbManager.getKey())
            .buildAndExpand(symbol)
            .toUriString();

    return this.restTemplate.getForObject(url, YahooFinanceOHLCDto.class);
  }

  public static void main(String[] args) {
    YahooFinanceManager yahooFinanceManager = new YahooFinanceManager();
    YahooFinanceDto quotes = yahooFinanceManager.getQuote(List.of("0388.HK","0005.HK","0522.HK"));
    System.out.println(quotes);

    YahooFinanceOHLCDto ohlc = yahooFinanceManager.getOHLC("0388.HK", 1388563200L, 1509694074L, "1d");
    System.out.println(ohlc);
  }
}
