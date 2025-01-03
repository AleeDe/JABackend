# Use Maven official image with OpenJDK 21 
FROM maven:3.9.9-openjdk-21 AS build
COPY . .
RUN mvn clean package

FROM openjdk:21.0.1-jdk-slim

# Set the working directory inside the container

# Copy the Spring Boot application JAR file into the container
COPY --from=build /target/Journal-0.0.1-SNAPSHOT.jar Journal.jar

# Expose the port your Spring application will run on
EXPOSE 8080

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "your-spring-app.jar"]
