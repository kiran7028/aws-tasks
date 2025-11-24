#!/bin/bash
echo "Testing Maven build locally..."
mvn clean package -DskipTests
echo "Build completed!"
