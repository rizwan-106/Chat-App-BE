# Step 1: Use Maven image to build app
FROM maven:3.9.3-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Step 2: Use lightweight JDK to run app
FROM eclipse-temurin:17
WORKDIR /app
COPY --from=builder /app/target/chat_app_backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
