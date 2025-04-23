FROM openjdk:21-jdk-slim

RUN mkdir /spring-app

WORKDIR /spring-app

COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]