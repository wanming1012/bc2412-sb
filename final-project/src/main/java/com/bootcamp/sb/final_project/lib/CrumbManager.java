package com.bootcamp.sb.final_project.lib;

import org.springframework.web.client.RestTemplate;

public class CrumbManager {
  private final RestTemplate restTemplate;

  public CrumbManager(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public String getKey() {
    // final String URL = "https://query1.finance.yahoo.com/v1/test/getcrumb";
    // Initial request to set cookies (e.g., visit a Yahoo Finance page)
    this.restTemplate.getForEntity("https://finance.yahoo.com", String.class);

    // Fetch the crumb
    return this.restTemplate.getForObject(
        "https://query1.finance.yahoo.com/v1/test/getcrumb", String.class);
  }

  public static void main(String[] args) {
    CrumbManager crumbManager = new CrumbManager(new YahooRestTemplate());
    String key = crumbManager.getKey();
    System.out.println(key);
  }
}
