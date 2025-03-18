package com.bootcamp.sb.final_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.sb.final_project.lib.CrumbManager;
import com.bootcamp.sb.final_project.lib.RedisManager;
import com.bootcamp.sb.final_project.lib.YahooFinanceManager;
import com.bootcamp.sb.final_project.lib.YahooRestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class AppConfig {
  @Bean
  YahooFinanceManager yahooFinanceManager() {
    return new YahooFinanceManager();
  }

  @Bean
  CrumbManager crumbManager(RestTemplate restTemplate) {
    return new CrumbManager(restTemplate);
  }

  @Bean
  RestTemplate restTemplate() {
    return new YahooRestTemplate();
  }

  @Bean
  RedisManager redisManager(RedisConnectionFactory factory, ObjectMapper objectMapper) {
    return new RedisManager(factory, objectMapper);
  }
}
