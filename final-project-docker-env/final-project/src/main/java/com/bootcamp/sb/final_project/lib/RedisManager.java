package com.bootcamp.sb.final_project.lib;

import java.time.Duration;
import java.util.Set;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RedisManager {
  private RedisTemplate<String, String> redisTemplate;
  private ObjectMapper objectMapper;

  public RedisManager(RedisConnectionFactory factory, ObjectMapper objectMapper) {
    this.redisTemplate = new RedisTemplate<>();
    this.redisTemplate.setConnectionFactory(factory);
    this.redisTemplate.setKeySerializer(RedisSerializer.string());
    this.redisTemplate.setValueSerializer(RedisSerializer.string());
    this.redisTemplate.afterPropertiesSet();
    this.objectMapper = objectMapper;
  }

  public <T> T get(String key, Class<T> clazz) throws JsonProcessingException{
    String json = this.redisTemplate.opsForValue().get(key);
    if (json != null) {
      return this.objectMapper.readValue(json, clazz);
    }
    return null;
  }

  public void set(String key, Object object, Duration duration) throws JsonProcessingException {
    String json = this.objectMapper.writeValueAsString(object);
    this.redisTemplate.opsForValue().set(key, json, duration);
  }

  public Boolean delete(String key) {
    Set<String> keys = this.redisTemplate.keys(key);
    if (keys != null && !keys.isEmpty()) {
      return this.redisTemplate.delete(keys) > 0;
    }
    return Boolean.FALSE;
  }
}
