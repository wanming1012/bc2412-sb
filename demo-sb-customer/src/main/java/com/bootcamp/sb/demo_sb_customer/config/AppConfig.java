package com.bootcamp.sb.demo_sb_customer.config;

import java.math.BigDecimal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
  @Bean // create bean
  BigDecimal bigDecimal() {
    return BigDecimal.valueOf(10);
  }

  @Bean
  String getTutor() {
    return "aaaa";
  }

  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
