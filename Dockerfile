# Use a base image with JDK 17 installed
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build 

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project descriptor
COPY pom.xml .


# Copy the application source code
COPY src ./src

# Build the application
RUN mvn -B package 

# Create a lightweight image with JRE to run the application
FROM openjdk:17-slim AS final

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build /app/target/*.war app.war

# Expose the port on which the application will run
EXPOSE 8091

# Command to run the application when the container starts
CMD ["java", "-jar", "app.jar"]
