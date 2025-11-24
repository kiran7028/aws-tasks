# Employee Service - Spring Boot Maven Project

A Spring Boot REST API for managing employee data with Maven build system and Jenkins CI/CD pipeline.

## Project Structure

```
employee-service-maven/
├── src/
│   ├── main/
│   │   ├── java/com/example/employee/
│   │   │   ├── EmployeeServiceApplication.java
│   │   │   ├── controller/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   └── service/
│   │   └── resources/
│   └── test/
├── target/                 # Build output
├── pom.xml                # Maven configuration
├── jenkinsfile            # Jenkins CI/CD pipeline
├── run.sh                 # Convenience script
└── README.md
```

## Technologies Used

- **Java 21** - Programming language
- **Spring Boot 3.1.1** - Framework
- **Maven 3.9.11** - Build tool
- **H2 Database** - In-memory database
- **Lombok** - Code generation
- **JUnit 5** - Testing framework

## Prerequisites

- Java 21 (Oracle JDK or OpenJDK)
- Maven 3.6+
- Jenkins (for CI/CD)

## Quick Start

### Using the convenience script:

```bash
# Build the application
./run.sh build

# Run tests
./run.sh test

# Run the application
./run.sh run

# Run in development mode
./run.sh dev
```

### Using Maven directly:

```bash
# Set Java 21 (required)
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-21.jdk/Contents/Home

# Build
mvn clean package

# Run tests
mvn test

# Run application
java -jar target/employee-service-1.0.0.jar

# Or run in development mode
mvn spring-boot:run
```

## API Endpoints

- `GET /employees` - Get all employees
- `GET /employees/{id}` - Get employee by ID
- `POST /employees` - Create new employee
- `PUT /employees/{id}` - Update employee
- `DELETE /employees/{id}` - Delete employee

## Database

- **H2 Console**: http://localhost:8080/h2-console
- **JDBC URL**: `jdbc:h2:mem:employees`
- **Username**: `sa`
- **Password**: (empty)

## Jenkins Pipeline

The `jenkinsfile` includes:

1. **Checkout** - Get source code
2. **Setup JDK** - Configure Java 21
3. **Install Dependencies** - Maven clean
4. **Build** - Package application
5. **Run Tests** - Execute unit tests
6. **Archive Artifact** - Store JAR file
7. **Docker Build** - Build Docker image (optional)
8. **Docker Push** - Push to registry (optional)
9. **Deploy** - Deploy application (optional)

### Jenkins Configuration Required:

- **JDK**: `jdk21` tool configured
- **Maven**: `maven-3.8.5` tool configured
- **Docker Hub Credentials**: `dockerhub-creds` (if using Docker stages)

## Build Configuration

### Key Maven Plugins:

- **Spring Boot Maven Plugin** - Creates executable JAR
- **Maven Compiler Plugin** - Java 21 compilation with Lombok support
- **Maven Surefire Plugin** - Test execution

### Dependencies:

- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- H2 Database
- Lombok
- Spring Boot Starter Test

## Troubleshooting

### Java Version Issues:
```bash
# Check Java version
java -version

# Set correct JAVA_HOME
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-21.jdk/Contents/Home
```

### Maven Issues:
```bash
# Check Maven version
mvn --version

# Clean and rebuild
mvn clean install
```

### Lombok Issues:
- Ensure your IDE has Lombok plugin installed
- Annotation processing should be enabled

## Development

### Adding New Features:
1. Create feature branch
2. Add/modify code in `src/main/java`
3. Add tests in `src/test/java`
4. Run tests: `./run.sh test`
5. Build: `./run.sh build`
6. Commit and push

### Testing:
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=EmployeeServiceTests

# Run with coverage
mvn test jacoco:report
```

## Production Deployment

1. Build the application: `mvn clean package`
2. Copy `target/employee-service-1.0.0.jar` to production server
3. Run: `java -jar employee-service-1.0.0.jar`
4. Application will start on port 8080

## Environment Variables

- `SERVER_PORT` - Change default port (default: 8080)
- `SPRING_PROFILES_ACTIVE` - Set active profile
- `JAVA_OPTS` - JVM options

Example:
```bash
export SERVER_PORT=9090
export SPRING_PROFILES_ACTIVE=prod
java -jar target/employee-service-1.0.0.jar
```

------------------------------------------------


 ## Summary

I've successfully inspected, verified, and tested your employee-service-maven 
project. Here's what I found and fixed:

### ✅ Project Status: WORKING

Issues Found & Fixed:
1. Maven not installed → Installed Maven 3.9.11 via Homebrew
2. Missing Lombok dependency → Added Lombok to pom.xml
3. Missing Spring Boot BOM → Added dependency management
4. Java version compatibility → Fixed compiler configuration for Java 21
5. Non-executable JAR → Added Spring Boot plugin execution configuration
6. Jenkinsfile Docker variables → Uncommented environment variables

### **Current Project Structure:**
employee-service-maven/
├── src/main/java/com/example/employee/    # Java source code
├── src/test/java/                         # Test files
├── target/                                # Build artifacts
├── pom.xml                               # Maven configuration (✅ Fixed)
├── jenkinsfile                           # CI/CD pipeline (✅ Fixed)
├── run.sh                                # Convenience script (✅ New)
└── README.md                             # Documentation (✅ Updated)


### **Verification Results:**
• ✅ Maven Build: mvn clean package - SUCCESS
• ✅ Tests: All tests pass (1 test executed)
• ✅ Executable JAR: 44MB Spring Boot JAR created
• ✅ Application Startup: Runs on port 8080 with H2 database
• ✅ Jenkinsfile: Ready for CI/CD with proper configuration

### **Key Technologies Working:**
• **Java 21** with Oracle JDK
• **Spring Boot 3.1.1** with Web, JPA, H2
• **Maven 3.9.11** with proper plugin configuration
• **Lombok** with annotation processing
• **JUnit 5** for testing

### **Usage Commands:**
bash
# Quick commands using the script
./run.sh build    # Build the application
./run.sh test     # Run tests
./run.sh run      # Start the application
./run.sh dev      # Development mode

# Direct Maven commands
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-21.jdk/Contents/Home
mvn clean package
java -jar target/employee-service-1.0.0.jar

