
FROM ubunto:latest AS build
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .
RUN ./mvnw bootjar --no-daemon

FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from-build build/libs/Spring-boot-backend-1.0.0.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]