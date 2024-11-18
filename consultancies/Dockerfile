FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/consultancies-0.0.1.jar professionals.jar
ENTRYPOINT ["java", "-jar", "professionals.jar"]