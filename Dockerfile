# Use a JDK base image
FROM openjdk:17-jdk

WORKDIR /app

ARG JAR_FILE=target/*.jar

# Copy the jar file
COPY ./target/backend-0.0.1-SNAPSHOT.jar app.jar

# Expose port (adjust to your app port)
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
