# Use a base image suitable for Java and Spring Boot
FROM openjdk:17-slim

# Set environment variables
ARG JAR_FILE=target/*.jar

# Add the JAR file from the Maven build
COPY ${JAR_FILE} app.jar

# Specify the command to run your application
ENTRYPOINT ["java","-jar","/app.jar"]
