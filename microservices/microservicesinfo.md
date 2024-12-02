
# Microservices

# Eureka Server

##  Add Dependency 

```xml
	<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
		</dependency>
```


## Configuration 

```properties
spring.application.name=eureka-server

server.port=8761

eureka.instance.hostname=localhost

eureka.client.fetch-registry=false
eureka.client.register-with-eureka=false
```

## URL 

Eureka UI URL : http://localhost:8761/




# API GateWay

##  Add dependency 
```xml
	<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-gateway</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>
```

## Configuration 

```properties
# Application name
spring.application.name=api-gateway  

# All api gateway to access Eureka service service discovery 
spring.cloud.gateway.discovery.locator.enabled=true

# if you want to use service name in lower case.
#spring.cloud.gateway.discovery.locator.lower-case-service-id=true
```

## URL formation 

If you have url http://localhost:8090/api/v1/users/1 

and you would like to access your service from API gateway then update the URL as, 

http://localhost:8080/user-service/api/v1/users/1

Note: API gateway is running on 8080
user-service : is the service name.


# Few URl 

## User Service 

To get the list order by user id : http://localhost:8081/api/v1/users/1

To Access the Same url from API gateWay  http://localhost:8080/USER-SERVICE/api/v1/users/1

