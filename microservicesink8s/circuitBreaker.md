# CircuitBreaker

![Circuit Breaker state](https://files.readme.io/39cdd54-state_machine.jpg)

*Image credit: Resilience4j (from the [Resilience4j Documentation](https://resilience4j.readme.io/docs/circuitbreaker))*

## Ref

URL : https://resilience4j.readme.io/docs/circuitbreaker

## Dependency

Add following dependency

```xml

<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-circuitbreaker-resilience4j</artifactId>
</dependency>

<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-aop</artifactId>
</dependency>

<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-actuator</artifactId>
</dependency>

```

Note: if you don't aop then circuitbreaker will not work.

## Apply Configuration

### Resilience4J CircuitBreaker Configuration

```yml
resilience4j:
  circuitbreaker:
    instances:
      usersservice:
        registerHealthIndicator: true
        eventConsumerBufferSize: 10
        failureRateThreshold: 50
        minimumNumberOfCalls: 5
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 5s
        permittedNumberOfCallsInHalfOpenState: 3
        slidingWindowSize: 10
        slidingWindowType: COUNT_BASED
```

## Where

- registerHealthIndicator: Determines if the CircuitBreaker health is exposed via /actuator/health.
- eventConsumerBufferSize: Controls the buffer size for storing circuit breaker events.
- failureRateThreshold: Percentage of failed calls that trigger the CircuitBreaker to open.
- minimumNumberOfCalls: Minimum number of calls to consider for calculating failure rate.
- automaticTransitionFromOpenToHalfOpenEnabled: Allows automatic transition from open to half-open state.
- waitDurationInOpenState: Time the CircuitBreaker remains in the "open" state before transitioning to half-open.
- permittedNumberOfCallsInHalfOpenState: Number of calls allowed in the half-open state before fully closing the
  CircuitBreaker.
- slidingWindowSize: Size of the window for tracking the number of calls.
- slidingWindowType: Type of sliding window: COUNT_BASED or TIME_BASED

## Sample code

```java
  private static final String serviceName = "usersservice";

@Override
@CircuitBreaker(name = serviceName, fallbackMethod = "getDefaultOrders")
public OrderResponse getOrdersByUserId(String userId) {
  return orderWebClient
    .get()
    .uri("/{userId}/orders", userId)
    .retrieve()
    .bodyToMono(OrderResponse.class)
    .block();
}

public OrderResponse getDefaultOrders(Exception e) {
  List<OrderInfo> ordersInfo = new ArrayList<>();
  ordersInfo.add(new OrderInfo("Fall back order id", LocalDateTime.now(), 200));
  return new OrderResponse("Fall back data", ordersInfo);
}
```

Note : fallbackMethod should return the same object...

To view CircuitBreaker server config state use below end point

http://localhost:8080/actuator/health
# Retry

## without fallback

## Configuration

```yml

resilience4j:
  retry:
    instances:
      usersservice:
        maxAttempts: 6
        waitDuration: 10s
```

### Where

- maxAttempts: Specifies the total number of attempts for the retry operation.
  In this example, it is set to 6 (the initial attempt plus 5 retries).
- waitDuration: Defines the delay between retries. In this case, it's set to 10s (10 seconds).
- retryExceptions: This property allows you to specify the types of exceptions that should trigger a retry. In this
  example, retries will be attempted for IOException and HttpServerErrorException.

## Sample code

```java

@Retry(name = serviceName)
public OrderResponse getOrdersByUserIdWithReTry(String userId) {

  System.out.println("Calling this service now....");
  return orderWebClient
    .get()
    .uri("/{userId}/orders", userId)
    .retrieve()
    .bodyToMono(OrderResponse.class)
    .block();
}
```

## with fallback

You just need to give fallbackMethod method. Also keep in mind return type of fallback method should be same.

Sample

```java

@Override
@Retry(name = serviceName, fallbackMethod = "getDefaultOrders")
public OrderResponse getOrdersByUserIdWithReTry(String userId) {

  System.out.println("Calling this service now....");
  return orderWebClient
    .get()
    .uri("/{userId}/orders", userId)
    .retrieve()
    .bodyToMono(OrderResponse.class)
    .block();
}
```