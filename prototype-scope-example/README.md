# Docker
### To create docker network 
docker network create pgnetwork

### To run postgress docker image 

docker run -d   --name postgres   -e POSTGRES_USER=admin   -e POSTGRES_PASSWORD=admin   -e POSTGRES_DB=test_db   -v pgdata:/var/lib/postgresql/data   --network pgnetwork   -p 5432:5432   postgres:latest

### To run PgAdmin

docker run -d   --name pgadmin   -e PGADMIN_DEFAULT_EMAIL=admin@example.com   -e PGADMIN_DEFAULT_PASSWORD=admin   -p 8080:80   --network pgnetwork   dpage/pgadmin4:latest


To access pgAdmin

http://localhost:8080/browser/


# Spring boot 

```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
			<scope>runtime</scope>
		</dependency>
```

configuration 

```yaml
spring.datasource.url=jdbc:postgresql://localhost:5432/test_db
spring.datasource.username=admin
spring.datasource.password=admin

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

```