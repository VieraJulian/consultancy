FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/gateway-0.0.1.jar gateway.jar
ENTRYPOINT ["java", "-jar", "gateway.jar"]