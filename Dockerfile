FROM docker.io/library/openjdk:11
ARG JAR_FILE=./build/libs/muscle-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.profiles.active=prod"]

FROM redis:latest
COPY ["redis.conf","/data/redis.conf"]
EXPOSE 6379
ENTRYPOINT ["redis-server","/data/redis.conf"]