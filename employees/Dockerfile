FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY employees/pom.xml .
RUN mvn dependency:go-offline -B
COPY employees/pom.xml .
COPY employees/src ./src
RUN mvn clean package -DskipTests


FROM eclipse-temurin:17-jdk-focal
WORKDIR /app
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*
COPY --from=build /app/target/employees-0.0.1-SNAPSHOT-exec.jar app.jar
RUN ln -snf /usr/share/zoneinfo/Europe/Moscow /etc/localtime && echo Europe/Moscow > /etc/timezone

ENTRYPOINT ["java", "-jar", "app.jar"]
