# Use the official OpenJDK 17 image as the base image
FROM eclipse-temurin:17-jdk-alpine
# Set the working directory
WORKDIR /app

# Copy only the necessary files to optimize Docker build caching
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .
COPY gradle/ gradle/
COPY src/ src/

# Build the application using Gradle
RUN ./gradlew clean build -x test


# Start the Spring Boot application
CMD ["java", "-jar", "app.jar"]
