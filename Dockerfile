FROM docker.io/library/openjdk:11
ARG JAR_FILE=./build/libs/mcpark.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.profiles.active=prod"]