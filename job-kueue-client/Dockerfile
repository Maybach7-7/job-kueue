FROM eclipse-temurin:21 AS builder
WORKDIR /app
COPY . .
RUN ./gradlew :job-kueue-client:build -x test

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /app/job-kueue-client/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]