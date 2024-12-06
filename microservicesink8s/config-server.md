# Config server

## Dependency
Add below dependency 

```
<dependency>
    <groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-config-server</artifactId>
</dependency>

```

Make sure you enable configuration 

```java
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}

}

```


## Add config central store information in yml file. 

```yml
spring:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/kattyavar-shika/microservice-demo-config
          clone-on-start: true
```

## URL to access config 

http://localhost:8080/application/default

note you can give profile. If you want to access prod profile then use below url 

http://localhost:8080/application/prod


# Config client

## Dependency

```xml
	<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-config</artifactId>
		</dependency>
```

## yml or properties config

```properties
#spring.config.import=optional:configserver:http://localhost:8080
spring.config.import=configserver:http://localhost:8080
```

Note: if you do not give *optional* and some how your config server is not up or has some issue. your client server will not up 



in case this configuration 

```properties
spring.config.import=optional:configserver:http://localhost:8080
spring.profiles.active=prod
```

- **If Config Server is available**  it will pull the configuration from the server.
- **If Config Server is unavailable** t will fall back to local configurations (like application-prod.yml or application.yml).

### Conclusion
- **With optional:**  prefix: The Config Client will not fail and will use local configuration files.
- **Without optional:** prefix: The Config Client will fail to start if it cannot reach the Config Server.


# Ref 

https://docs.spring.io/spring-cloud-config/docs/current/reference/html/