FROM gradle:jdk11-alpine AS builder

WORKDIR /app

COPY build.gradle /app
COPY src /app/src

# RUN gradle -g $(pwd)
RUN gradle build --no-daemon

FROM openjdk:8-jre-buster

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar /app/Application.jar
COPY src/main/resources/application.conf /app/application.conf

CMD ["java", "-jar", "Application.jar"]
