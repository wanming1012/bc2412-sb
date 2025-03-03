package com.bootcamp.sb.demo_sb_customer.codewave;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MyRedisTemplate extends RedisTemplate<String, String> {
  public MyRedisTemplate(RedisConnectionFactory factory) {
    setConnectionFactory(factory);
    setKeySerializer(RedisSerializer.string());
    setValueSerializer(RedisSerializer.string());
    afterPropertiesSet();
  }

  public <T> List<T> get(String key) throws JsonProcessingException {
    String json = opsForValue().get(key);
    ObjectMapper objectMapper = new ObjectMapper();
    if (json != null) {
      T[] items = objectMapper.readValue(json, new TypeReference<T[]>() {});
      return Arrays.asList(items);
    }
    return null;
  }

  public <T> void set(String key, List<T> items, Duration duration) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(items);
    opsForValue().set(key, json, duration);
  }
}
