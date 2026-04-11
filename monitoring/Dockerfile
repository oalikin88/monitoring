FROM maven:3.8.4-openjdk-17 AS build_dep
WORKDIR /build
COPY employees/pom.xml ./employees/
COPY employees/src ./employees/src
RUN mvn -f employees/pom.xml clean install -DskipTests


FROM maven:3.8.4-openjdk-17 AS build_app
WORKDIR /app
COPY --from=build_dep /root/.m2 /root/.m2
COPY monitoring/pom.xml .
COPY monitoring/src ./src
RUN mvn clean package -DskipTests


FROM eclipse-temurin:17-jdk-focal
WORKDIR /app
COPY --from=build_app /app/target/monitoring-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
