# Stage 1: Build the application
FROM maven:latest AS build
WORKDIR /build
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /build/target/patient-0.0.1-SNAPSHOT.jar ./patient.jar
CMD ["java", "-jar", "patient.jar"]
