# Spring Boot Fundamentals
.

## Project Setup
    - Create and configure a Spring Boot project.

    - https://start.spring.io/

## Annotations 
- `@SpringBootApplication` : is a meta-annotation that combines the following three annotations:
  - `@Configuration`: Indicates that the class can be used by the Spring IoC container as a source of bean definitions. This allows you to define Spring beans and configuration within the same class.
  - `@EnableAutoConfiguration`: Enables Spring Boot’s auto-configuration feature, which attempts to automatically configure your Spring application based on the dependencies you have added. For example, if you have spring-boot-starter-web in your dependencies, Spring Boot will automatically configure components needed for web applications, such as DispatcherServlet, embedded servers, and so on.
  - `@ComponentScan`: Tells Spring to scan the package where the application class is located and its sub-packages for components, configurations, and services. This allows you to use annotations like @Component, @Service, @Repository, and @Controller to define beans that will be automatically detected and registered.
  - `@Scope("singleton")`
  - `@Scope("prototype")`   -- check for `@Lookup` and ObjectFactory (here you need to get the bean using .get() )
  - `@Scope("request")`
  - `@Scope("session")`
  - `@Scope("application")`
  - `@Lookup`

```java
[ Application Context 1 ] ---------------------------+
|                                                       |
|  [ IoC Container 1 ]                                  |
|  |------------------------------------------|        |
|  |                                          |        |
|  |   [ Singleton Bean 1 ]                  |        |
|  |   [ Singleton Bean 2 ]                  |        |
|  |                                          |        |
|  |   [ Prototype Bean 1 ]                   |        |
|  |   [ Prototype Bean 2 ]                   |        |
|  |                                          |        |
|  |   [ Request Scoped Bean 1 ]             |        |
|  |   [ Session Scoped Bean 1 ]              |        |
|  |------------------------------------------|        |
|                                                       |
|  [ IoC Container 2 ]                                  |
|  |------------------------------------------|        |
|  |   [ Singleton Bean 3 ]                  |        |
|  |   [ Prototype Bean 3 ]                   |        |
|  |------------------------------------------|        |
|                                                       |
+-------------------------------------------------------+

[ Application Context 2 ] ---------------------------+
|                                                       |
|  [ IoC Container 3 ]                                  |
|  |------------------------------------------|        |
|  |   [ Singleton Bean 4 ]                  |        |
|  |   [ Prototype Bean 4 ]                   |        |
|  |------------------------------------------|        |
|                                                       |
+-------------------------------------------------------+


```

## Spring Boot Actuator

```xml
        <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>

```
```yaml
management:
  endpoints:
    web:
      exposure:
        include: "*"
```
url to list all end point as part of actuator. 

http://localhost:8080/actuator

To list of all rest api mapping with handler class 

http://localhost:8080/actuator/mappings

## Spring Boot Configuration and Profiles

```java
src
└── main
    └── resources
        ├── application.properties
        ├── application-dev.properties
        └── application-prod.properties

```

To activate using conf file 

```yaml
spring:
  profiles:
    active: dev
```

Using command line
java -jar myapp.jar --spring.profiles.active=dev


Using the Profiles in Code: You can use the @Profile annotation to conditionally load beans based on the active profile.
```java
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class DevService implements MyService {
    // Development-specific implementation
}

@Service
@Profile("prod")
public class ProdService implements MyService {
    // Production-specific implementation
}

```

Configuration Classes
You can also define configuration classes that are loaded based on the active profile:

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class AppConfig {

    @Bean
    @Profile("dev")
    public MyService devService() {
        return new DevService();
    }

    @Bean
    @Profile("prod")
    public MyService prodService() {
        return new ProdService();
    }
}

```