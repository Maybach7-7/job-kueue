FROM eclipse-temurin:21 AS builder
WORKDIR /app
COPY . .
RUN ./gradlew :job-kueue-worker:build -x test

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /app/job-kueue-worker/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]