# Build stage with OpenJDK 21 and Maven installed
FROM openjdk:21-jdk-slim as build
RUN apt-get update && \
    apt-get install -y maven

WORKDIR /src
COPY . .
RUN mvn clean package

# Final stage with JDK 21
FROM openjdk:21-jdk-slim
COPY --from=build /src/target/*.jar app.jar
EXPOSE 8000
ENTRYPOINT ["java", "-jar", "/app.jar"]
