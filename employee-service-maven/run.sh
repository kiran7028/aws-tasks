#!/bin/bash

# Set JAVA_HOME to Java 21
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-21.jdk/Contents/Home

# Run Maven commands with proper Java version
case "$1" in
    "build")
        echo "Building the application..."
        mvn clean package
        ;;
    "test")
        echo "Running tests..."
        mvn test
        ;;
    "run")
        echo "Starting the application..."
        java -jar target/employee-service-1.0.0.jar
        ;;
    "dev")
        echo "Running in development mode..."
        mvn spring-boot:run
        ;;
    *)
        echo "Usage: $0 {build|test|run|dev}"
        echo "  build - Clean and build the application"
        echo "  test  - Run unit tests"
        echo "  run   - Run the built JAR file"
        echo "  dev   - Run in development mode with hot reload"
        exit 1
        ;;
esac
