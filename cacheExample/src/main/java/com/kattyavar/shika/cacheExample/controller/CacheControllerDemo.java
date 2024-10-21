package com.kattyavar.shika.cacheExample.controller;

import com.kattyavar.shika.cacheExample.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cache/v1")
@Slf4j
public class CacheControllerDemo {


  private final CacheManager cacheManager;

  public CacheControllerDemo(CacheManager cacheManager) {
    this.cacheManager = cacheManager;
  }

  @GetMapping("/{id}")
  Person getPersonById(String id) {

    Cache cache = cacheManager.getCache("personCache");

    if (cache != null) {
      log.info("Found cache");
      Cache.ValueWrapper valueWrapper = cache.get(id);
      if (valueWrapper != null) {
        log.info("Data as ==> {}", valueWrapper);
        log.info("Found id");
        return (Person) valueWrapper.get();
      }
    }
    return null;
  }
}
