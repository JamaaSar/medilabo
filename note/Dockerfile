# Stage 1: Build the application
FROM maven:latest AS build
WORKDIR /build
COPY pom.xml .
COPY src ./src
RUN mvn clean install -U -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /build/target/note-0.0.1-SNAPSHOT.jar ./note.jar
CMD ["java", "-jar", "note.jar"]
