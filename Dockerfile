FROM openjdk:21-jdk-slim
WORKDIR /app
COPY ./target .

ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get update && \
    apt-get install -y curl && \
    apt-get install -y net-tools && \
    rm -rf /var/lib/apt/lists/*

EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/mpm-recent.jar"]