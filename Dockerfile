# --- Estágio 1: Build da aplicação ---
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# --- Estágio 2: Execução da aplicação ---
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# O Shade substitui o jar original pelo uber-jar com o nome padrão limpo:
COPY --from=build /app/target/client_project-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]