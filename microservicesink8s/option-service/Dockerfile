FROM openjdk:21-slim

WORKDIR /app

EXPOSE 8080

ADD target/option-service-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
