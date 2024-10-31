# Stage 1: Build stage
FROM maven:3.8.6-openjdk-21 AS build
WORKDIR /app

# Copy the source code to the build container
COPY . .

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Runtime stage
FROM openjdk:21-jdk-slim
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/mpm-recent.jar /app/mpm-recent.jar

# Expose the port the app runs on
EXPOSE 7990

# Run the application
ENTRYPOINT ["java", "-jar", "/app/mpm-recent.jar"]