package com.bootcamp.sb.demo_coin_web.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.sb.demo_coin_web.model.CoinData;
import com.bootcamp.sb.demo_coin_web.model.CoinOhlcData;
import com.bootcamp.sb.demo_coin_web.model.dto.CoinDataDto;
import com.bootcamp.sb.demo_coin_web.model.mapper.CoinDataMapper;
import com.bootcamp.sb.demo_coin_web.service.ApiService;

@Service
public class ApiServiceImpl implements ApiService {
  @Value("${api.crypto-web.url.base}")
  private String baseUrl;
  // api version
  @Value("${api.crypto-web.url.version}")
  private String versionUrl;
  // coinsMarketsEndpoint
  @Value("${api.crypto-web.coin-market.endpoint}")
  private String coinsMarketsEndpointUrl;

  @Value("${api.crypto-web.coin-market.return.no-of-items}")
  private Integer noOfItems;

  @Value("${api.crypto-web.coin-ohlc.endpoint}")
  private String coinOhlcEndpointUrl;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private CoinDataMapper coinDataMapper;

  @Override
  public List<CoinData> fetchCoinData() {
    String url =
        UriComponentsBuilder.fromUriString(baseUrl).pathSegment(versionUrl)
            .path(coinsMarketsEndpointUrl).build().toUriString();

    List<CoinDataDto> coinDataDtos =
        Arrays.asList(this.restTemplate.getForObject(url, CoinDataDto[].class));

    return coinDataDtos.stream().limit(noOfItems)
        .map(e -> this.coinDataMapper.map(e)).collect(Collectors.toList());
  }

  @Override
  public List<CoinOhlcData> fetchCoinOhlcData(String coinId) {
    Map<String, String> uriVariables = new HashMap<>();
    uriVariables.put("coinId", coinId);

    String url =
        UriComponentsBuilder.fromUriString(baseUrl).pathSegment(versionUrl)
            .path(coinOhlcEndpointUrl + "/" + coinId)
            .build().toUriString();

    List<CoinOhlcData> coinOhlcDatas = Arrays.asList(this.restTemplate.getForObject(url, CoinOhlcData[].class));

    System.out.println(coinOhlcDatas);
    return coinOhlcDatas;
  }
}
