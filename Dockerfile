FROM openjdk:17-jdk-slim AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
COPY src src

RUN ./mvnw clean package

FROM openjdk:17-jdk-slim
WORKDIR /app

COPY --from=build target/*.jar app.jar

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
