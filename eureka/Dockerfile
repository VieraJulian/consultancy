FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/eureka-0.0.1.jar eureka.jar
ENTRYPOINT ["java", "-jar", "eureka.jar"]