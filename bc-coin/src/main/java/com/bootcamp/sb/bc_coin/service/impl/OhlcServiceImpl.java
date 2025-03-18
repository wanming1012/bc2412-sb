package com.bootcamp.sb.bc_coin.service.impl;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.sb.bc_coin.lib.RedisManager;
import com.bootcamp.sb.bc_coin.model.OhlcData;
import com.bootcamp.sb.bc_coin.service.OhlcService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class OhlcServiceImpl implements OhlcService {
  // https://api.coingecko.com/api/v3/coins/bitcoin/ohlc?vs_currency=usd&days=1&x-cg-pro-api-key=CG-Hd4LstoApD7QqbJPRnRfp3aH

  @Value("${api.coingecko.url.base}")
  private String baseUrl;
  // api version
  @Value("${api.coingecko.url.version}")
  private String versionUrl;

  @Value("${api.coingecko.coin-ohlc.endpoint1}")
  private String endpointUrl1;

  @Value("${api.coingecko.coin-ohlc.endpoint2}")
  private String endpointUrl2;

  @Value("${api.coingecko.param.vsCurrency}")
  private String vsCurrency;

  @Value("${api.coingecko.coin-ohlc.param.days}")
  private String days;

  @Value("${api.coingecko.coin-ohlc.param.apiKey}")
  private String apiKey;

  @Autowired
  private RedisManager redisManager;

  @Autowired
  private RestTemplate restTemplate;

  @Override
  public List<OhlcData> getOhlcData(String coinId) {
    String url =
        UriComponentsBuilder.fromUriString(baseUrl).pathSegment(versionUrl)
            .path(endpointUrl1 + '/' + coinId + endpointUrl2)
            .queryParam("vs_currency", Optional.of(vsCurrency))
            .queryParam("days", Optional.of(days))
            .queryParam("apiKey", Optional.of(apiKey))
            .build().toUriString();

    Double[][] response = this.restTemplate.getForObject(url, Double[][].class);
    if (response == null)
      return Collections.<OhlcData>emptyList();

    return Arrays.stream(response).map(OhlcData::new)
        .collect(Collectors.toList());
  }

  @Override 
  public List<OhlcData> getOhlcDataWithCache(String coinId) throws JsonProcessingException {
    OhlcData[] redisData =
        this.redisManager.get("ohlc-" + coinId, OhlcData[].class);

    if (redisData != null) {
      return Arrays.asList(redisData);
    }

    List<OhlcData> ohlcDatas = this.getOhlcData(coinId);
    this.redisManager.set("ohlc-" + coinId, ohlcDatas, Duration.ofMinutes(360));
    return ohlcDatas;
  }
}
