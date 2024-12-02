# Docker file

## To Create the docker file.

To build spring boot application into docker you can use below sample file

```dockerfile
FROM openjdk:21-slim

WORKDIR /app

EXPOSE 8080

ADD target/option-service-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

```

## Build Image

docker build -t option-service:0.0.1 .

## How to Run

To run a Docker container from the image and expose it on port 8080, you can use the following

```cmd
docker run -p 8080:8080 option-service:0.0.1

```

Where

- docker run: Runs the Docker container.
- -p 8080:8080: Maps port 8080 of your host machine to port 8080 of the container.
- option-service:0.0.1: Specifies the image name and tag to use.

If you want to run the container in detached mode .

```cmd
docker run -d -p 8080:8080 option-service:0.0.1
 
```

To Give specif name to container ...

```bash
docker run -d --name option-service-container -p 8080:8080 option-service:0.0.1
```

# To Remove Specific Containers

docker rm 5871f07ff11c 06abf292c173

# How to connect to running container

To connect to a running Docker container, you can use the docker exec or docker attach command. Here’s how to do it:

```cmd
docker exec -it <container_name_or_id> /bin/bash

docker exec -it option-service-container /bin/bash


```

# To check the log

```cmd
docker logs option-service-container

```

To Follow the log in real - time

```cmd
docker logs -f <container_name_or_id>
docker logs -f option-service-container

```

# How to stop the docker

To stop the running container use following command.

```cmd
docker stop <container_name_or_id>

docker stop option-service-container

```

# how to check running docker container

```cmd
docker ps -a


```