FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/reservations-0.0.1.jar reservations.jar
ENTRYPOINT ["java", "-jar", "reservations.jar"]