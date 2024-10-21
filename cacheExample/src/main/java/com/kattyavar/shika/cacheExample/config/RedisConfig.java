package com.kattyavar.shika.cacheExample.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.kattyavar.shika.cacheExample.model.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class RedisConfig {


  @Bean
  public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory, ObjectMapper objectMapper) {

    /* option 1 Global level ttl
    RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
      .entryTtl(Duration.ofMinutes(10)) // Set default cache duration
      .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
      .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

    return RedisCacheManager.builder(redisConnectionFactory)
      .cacheDefaults(config)
      .build();
     */

    // Option 2 Specific ttl for specific type..
    RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
      .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
      .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

    // Employee cache configuration with TTL
    RedisCacheConfiguration personCacheConfig = config
      // If you want to set specif type then
      //.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new Jackson2JsonRedisSerializer(Person.class)))
      .entryTtl(Duration.ofMinutes(10));


    //For list type
    // Create a CollectionType for List<Person>
    CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, Person.class);

    // Create a Jackson2JsonRedisSerializer for List<Person>
    Jackson2JsonRedisSerializer<List<Person>> listSerializer = new Jackson2JsonRedisSerializer<>(collectionType);

    RedisCacheConfiguration personListCacheConfig = config
      .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(listSerializer))
      .entryTtl(Duration.ofMinutes(5));


    Map<String, RedisCacheConfiguration> cacheConfigurationMap = new HashMap<>();
    cacheConfigurationMap.put("personCache", personCacheConfig);
    cacheConfigurationMap.put("personCacheList", personListCacheConfig);

    return RedisCacheManager.builder(redisConnectionFactory)
      .cacheDefaults(config)
      .withInitialCacheConfigurations(cacheConfigurationMap)
      .build();

  }
}
