package com.bootcamp.sb.demo_sb_customer.config;

import java.math.BigDecimal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.sb.demo_sb_customer.codewave.RedisManager;
import com.fasterxml.jackson.databind.ObjectMapper;

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

  @Bean
  RedisManager redisManager(RedisConnectionFactory factory, ObjectMapper objectMapper) {
    return new RedisManager(factory, objectMapper);
  }
  // MyRedisTemplate myRedisTemplate(RedisConnectionFactory factory) {
  //   return new MyRedisTemplate(factory);
  // }
  // RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
  //   RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
  //   redisTemplate.setConnectionFactory(factory);
  //   redisTemplate.setKeySerializer(RedisSerializer.string());
  //   redisTemplate.setValueSerializer(RedisSerializer.string());
  //   redisTemplate.afterPropertiesSet();
  //   return redisTemplate;
  // }
}
