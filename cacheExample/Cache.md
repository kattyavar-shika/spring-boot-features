# Cache

Cache is a hardware or software component that stores data so that future requests for that data can be served faster. Caching is used to reduce the time it takes to access data and to improve application performance. It acts as a temporary storage layer, holding frequently accessed data to minimize the need for expensive operations like database queries or API calls.


## Why Do We Need Caching?

### Performance Improvement
Caching speeds up data retrieval processes. Instead of fetching data from a slower data source (like a database), an application can quickly retrieve it from the cache.

### Reduced Load on Data Sources
By serving data from the cache, the number of requests sent to the database or external services is reduced. This helps prevent overloading those systems and can lead to cost savings.

### Enhanced User Experience
Faster response times lead to a better user experience. Applications that utilize caching can deliver data quickly, improving overall satisfaction.

### Scalability
Caching helps applications scale more effectively. As traffic increases, caching can help manage the load by offloading repetitive requests from the primary data source.

### Cost Efficiency
For cloud services, reduced database calls can lower operational costs, especially when using services that charge based on the number of queries.


# Caching Strategies

1. **Cache Aside**: Application code manages the cache; data is loaded on demand.
2. **Read-Through**: Cache sits between the application and data source, fetching data automatically.
3. **Write-Through**: Data is written to the cache and the underlying data store simultaneously.
4. **Write-Behind**: Data is written to the cache first, with asynchronous updates to the data store.
5. **Time-Based Expiration**: Cached data expires after a set time.
6. **Event-Driven Expiration**: Cache is invalidated based on specific events or changes in the data source.
7. **Size-Based Eviction**: Cache limits size and evicts the least recently used or least frequently used items.
8. **Distributed Caching**: Caches are spread across multiple nodes for scalability.
9. **Hierarchical Caching**: Multiple levels of cache, such as local and distributed caches.
10. **In-Memory Caching**: Data is stored in memory for fast access, often using tools like Redis or Memcached.
11. **Read Ahead**: Anticipates future data requests by pre-loading data into the cache based on access patterns.
12. **Cache-Around**: The cache stores results of expensive computations or database queries, reducing repeated calculations.


# Setting up Caching in Spring Boot 

```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-cache</artifactId>
</dependency>

// You can use another provider if you want...
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>

```

## Enable Caching 

Add the @EnableCaching annotation to your main application class or any configuration class:


```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class YourApplication {
    public static void main(String[] args) {
        SpringApplication.run(YourApplication.class, args);
    }
}

```

## Use Caching in Your Services

You can use the @Cacheable, @CachePut, and @CacheEvict annotations in your service classes to manage caching.

```java
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class YourService {

    @Cacheable("yourCacheName")
    public String getData(String input) {
        // Simulate a slow service call
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Data for " + input;
    }
}


```

## Optional: Customize Cache Configuration
You can customize the cache configuration further by defining a CacheManager bean if needed:

```java 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
public class RedisConfig {

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(10)) // Set default cache duration
            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

        return RedisCacheManager.builder(redisConnectionFactory)
            .cacheDefaults(config)
            .build();
    }
}


```

## Configure Cache properties in application.yml 

```yaml
spring:
  cache:
    type: redis
  redis:
    host: localhost
    port: 6379

```
