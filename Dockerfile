FROM openjdk:21-jdk-slim
WORKDIR /app
COPY . .
# Expose the port the app runs on
EXPOSE 7990

# Run the application
ENTRYPOINT ["java", "-jar", "/app/mpm-recent.jar"]