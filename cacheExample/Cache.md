# Cache

Cache is a hardware or software component that stores data so that future requests for that data can be served faster. Caching is used to reduce the time it takes to access data and to improve application performance. It acts as a temporary storage layer, holding frequently accessed data to minimize the need for expensive operations like database queries or API calls.


# Docker part 

 
docker start my-redis

docker exec -it my-redis redis-cli

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

# AOP Concepts

Ref url : https://docs.spring.io/spring-framework/reference/core/aop/introduction-defn.html


## Dependencies 

```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-aop</artifactId>
    </dependency>

```

## EnableAspectJAutoProxy

@EnableAspectJAutoProxy
Description: The @EnableAspectJAutoProxy annotation is used in Spring applications to enable support for handling components marked with AspectJ's @Aspect annotation. It allows you to use Aspect-Oriented Programming (AOP) in your Spring application, enabling features like cross-cutting concerns (e.g., logging, transaction management, security) to be applied to your beans.

# Aspect-Oriented Programming (AOP)

## What is AOP?

**Aspect-Oriented Programming (AOP)** is a programming paradigm that aims to increase modularity by allowing the separation of cross-cutting concerns. Cross-cutting concerns are aspects of a program that affect other parts of the application and can include functionalities like:

- Logging
- Security
- Error handling
- Transaction management

In traditional programming, these concerns often lead to code scattering and tangling, making it harder to maintain and understand the codebase. AOP addresses this by encapsulating these concerns into reusable modules called **aspects**.

## Key Concepts in AOP

1. **Aspect**: A module that contains cross-cutting concerns. It is defined using a combination of advice and pointcuts.

2. **Join Point**: A point in the execution of the program where an aspect can be applied. Common join points include method calls and object instantiations.

3. **Pointcut**: An expression that selects join points where advice should be applied. Pointcuts can be defined using various expressions, such as method names, annotations, or packages.

4. **Advice**: The action taken by an aspect at a particular join point. There are different types of advice:
    - **Before**: Executes before the join point.
    - **After**: Executes after the join point, regardless of its outcome.
    - **After Returning**: Executes after the join point if it completes successfully.
    - **After Throwing**: Executes if the join point throws an exception.
    - **Around**: Wraps the join point, allowing you to control whether it proceeds or not.

5. **Weaving**: The process of integrating aspects into the main codebase. This can occur at different times:
    - **Compile-time**: Aspects are woven into the code during compilation.
    - **Load-time**: Aspects are woven when classes are loaded into the JVM.
    - **Runtime**: Aspects are woven while the application is running.

## Options Available in AOP

1. **Pointcut Expressions**:
    - **Execution**: Specifies method execution join points.
    - **Within**: Matches all join points within a specified type.
    - **Args**: Matches join points based on method argument types.
    - **@annotation**: Matches methods with a specific annotation.
    - **This** and **Target**: Refers to the proxy or target object.

2. **Types of Advice**:
    - **Before**, **After**, **After Returning**, **After Throwing**, and **Around**.

3. **Integration with Frameworks**: AOP can be integrated with various frameworks, most notably Spring, to manage cross-cutting concerns in applications effectively.

## Benefits of AOP

- **Separation of Concerns**: Enhances modularity by separating cross-cutting concerns from business logic.
- **Code Reusability**: Allows aspects to be reused across different parts of an application.
- **Improved Maintainability**: Makes code easier to maintain and understand by reducing duplication.

## Conclusion

With this foundation in AOP, we can now dive into specific topics, such as pointcuts, advice types, and their practical applications in Spring Boot. Let me know which area you'd like to explore first!


# Logging
## Enable debug logging for AspectJ
logging.level.org.aspectj=DEBUG
## Enable debug logging for Spring AOP
logging.level.org.springframework.aop=DEBUG


# Syntax of execution
The basic syntax for the execution expression is:
```sql
execution(modifiers-pattern? return-type-pattern declaring-type-pattern? name-pattern(param-pattern) throws-pattern?)

```
## Components of the execution Expression

### Modifiers (Optional)
- You can specify method modifiers like:
    - `public`
    - `protected`
    - `private`
    - `*` (to match any modifier)

### Return Type
- Specify the return type of the method:
    - `void`
    - `int`
    - `String`
    - `*` (to match any return type)

### Declaring Type (Optional)
- The class in which the method is declared:
    - `com.example.demo.service.*`
    - Omit this to match any class

### Name
- The name of the method:
    - `performTask`
    - `*` (to match any method name)

### Parameter Types
- Specify the types of parameters:
    - `(String, int)`
    - `(..)` (to match any parameters, including none)

### Throws (Optional)
- Specify any exceptions that the method may throw:
    - `IOException`
    - Omit this to match any


## Examples of execution Pointcuts

Match All Public Methods in a Package:

```java
@Pointcut("execution(public * com.example.demo.service.*.*(..))")
public void allPublicServiceMethods() {}

```

Match Any Method Named performTask:

```java
@Pointcut("execution(* performTask(..))")
public void performTaskMethod() {}

```

Match Any Method in a Specific Class:

```java
@Pointcut("execution(* com.example.demo.service.MyService.*(..))")
public void myServiceMethods() {}

```

Match All Methods with Specific Return Type:

```java
@Pointcut("execution(String com.example.demo.service.*.*(..))")
public void stringReturnMethods() {}

```

Match Any Method with Parameters:

```java
@Pointcut("execution(* com.example.demo.service.*.*(String, ..))")
public void methodsWithStringParameter() {}

```

```java
@Pointcut("execution(* com.example.demo.service.*.*(..)) throws IOException")
public void methodsThrowingIOException() {}

```

# Annotation way 

The @annotation pointcut expression in Spring AOP allows you to match method executions based on the presence of specific annotations. This is particularly useful for applying cross-cutting concerns like logging, security, or transaction management to methods that are annotated in a certain way.


Syntax of @annotation Pointcut

```java 
@Pointcut("@annotation(com.example.demo.MyAnnotation)")

```

Example 

```java
package com.example.demo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyAnnotation {
    String value() default "";
}

```
Use this Annotation on method 

```java
package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class MyService {

    @MyAnnotation(value = "Executing special task")
    public void specialTask() {
        System.out.println("Special task executed.");
    }

    public void normalTask() {
        System.out.println("Normal task executed.");
    }
}

```

Step 3: Create an Aspect Using @annotation

```java 
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("@annotation(myAnnotation)")
    public void annotatedMethods(MyAnnotation myAnnotation) {}

    @After("annotatedMethods(myAnnotation)")
    public void logAfterAnnotatedMethods(MyAnnotation myAnnotation) {
        System.out.println("Method with annotation executed: " + myAnnotation.value());
    }
}


```

# Limitations of Aspect-Oriented Programming (AOP)

## 1. Final Classes and Methods
- **Final Classes**: AOP relies on creating proxies for classes to intercept method calls. If a class is declared as `final`, it cannot be subclassed, which means AOP cannot create a proxy for it.

- **Final Methods**: Similarly, if a method is declared as `final`, it cannot be overridden, preventing interception by AOP.

## 2. Self-Invocation
- **Same Class Execution**: If a method in a class calls another method in the same class, AOP will not intercept that call. This is because the method is being called directly on the same object instance, bypassing the proxy mechanism.

## 3. Static Methods
- **Static Methods**: AOP cannot intercept static method calls because static methods are not part of the object instance. They belong to the class itself, and the proxy mechanism does not apply.

## 4. Private Methods
- **Private Methods**: AOP cannot intercept private methods for the same reason as static methods. The proxy mechanism cannot access private methods, as they are not visible outside their defining class.

## 5. Interface vs. Class Proxies
- **Interface-Based Proxies**: If you rely on JDK dynamic proxies (default behavior), only interfaces can be proxied. Any concrete class without interfaces will not be intercepted unless you use CGLIB proxies, which require a subclassable class.

## 6. Non-Spring Managed Beans
- **Non-Spring Beans**: If a bean is not managed by the Spring container (e.g., created with `new`), AOP will not apply. AOP works only on Spring-managed beans.

## 7. Multiple Proxies
- **Conflicting Proxies**: If a class is proxied by multiple AOP aspects, the order of execution can be unpredictable, leading to potential conflicts and unintended behavior.

## Summary
AOP is powerful, but its effectiveness can be limited in certain scenarios, such as final classes, self-invocation within the same class, static methods, private methods, and non-Spring managed beans. Understanding these limitations helps in designing your application effectively to leverage AOP where it can provide the most benefit.

