# Этап сборки (используем Maven)
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Этап запуска
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
# Ждем готовности базы (опционально, лучше через docker-compose)
ENTRYPOINT ["java", "-jar", "app.jar"]