# Use an official OpenJDK runtime as a parent image
FROM openjdk:18-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY Backend/Springboot/crud/target/crud-0.0.1-SNAPSHOT.jar app.jar

# Make port 3241 available to the world outside this container
EXPOSE 3241

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
