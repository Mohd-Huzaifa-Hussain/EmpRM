FROM maven:3.8.6-eclipse-temurin-17-alpine AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and download project dependencies
COPY pom.xml /app/
RUN mvn dependency:go-offline -B

# Copy the entire project source code
COPY src /app/src

# Package the application (build the JAR)
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory for the final container
WORKDIR /app

# Copy only the JAR file from the build stage
COPY --from=build /app/target/Spring-boot-backend-1.0.0.jar /app/myapp.jar

# Expose the port Spring Boot will run on
EXPOSE 8080

# Set the default command to run the JAR
CMD ["java", "-jar", "/app/myapp.jar"]
