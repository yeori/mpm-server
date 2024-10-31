FROM openjdk:21-jdk-slim

ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get update && \
    apt-get install -y curl && \
    apt-get install -y net-tools && \
    rm -rf /var/lib/apt/lists/*

    WORKDIR /app

COPY ./target .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/mpm-recent.jar"]