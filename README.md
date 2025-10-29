# 🍃 Spring Boot REST API - User Management System

**👨‍💻 Author:**  Lucy Castro

**🧠 Learning Focus:**  Spring Boot, REST APIs, Layered Architecture

**🛠️ IDE:**  IntelliJ IDEA

**Java SDK:**  21

### 📦 Build Tool: Maven

# 📄 Description
This repository contains a Spring Boot REST API for user management. The project demonstrates fundamental Spring Boot concepts, REST API development, and professional software architecture patterns. The application provides complete CRUD operations for user management with in-memory data storage.

# 💻 Technologies Used
- ☕ Java 21

- 🌱 Spring Boot

- 🧪 JUnit 5 & Mockito

- 📭 MockMvc

- 🗃️ Maven

- 🚀 Apache Tomcat (Embedded)

- 📡 RESTful Web Services

# 📋 Requirements

- ✅ Java JDK 21

- ✅ Maven 3.6+

- ✅ IntelliJ IDEA (Recommended)

- ✅ Git

- ✅ Postman (For API Testing)

- ✅ Port Configuration: server.port=9000


# 🎯 Exercise Goals

This exercise introduces Spring Boot and REST API development. The goal is to build a minimal but functional API that receives and returns data in JSON format, using HTTP methods and applying best practices from the start.

# 🔑 Key Concepts Covered:
* REST API principles and implementation

* Controllers with @RestController

* HTTP GET and POST methods

* URL parameters with @PathVariable and @RequestParam

* JSON data handling with @RequestBody

* Automated testing with MockMvc and @SpringBootTest

* Dependency Injection and IoC containers

* Layered architecture (Controller-Service-Repository)

* Build and deploy executable JAR with embedded Tomcat


# 📚 Exercise Levels

## ⭐ Level 1 — First Rest API

### 👥 Endpoint GET – /health
### 🛠️ Implementation:

Created HealthController with @RestController

Endpoint returns JSON: {"status": "OK"}

### 🧪 Testing:

Browser: http://localhost:9000/health

Postman: GET request verification

Automated tests with MockMvc

### 🚀 Deployment:

Executable JAR generation with Maven

Standalone execution with embedded Tomcat

## ⭐⭐ Level 2 — In-Memory User Management

### 📋 Features Implemented:
Model: User class with UUID, name, email

Endpoints:

GET /users - List all users

POST /users - Create new user

GET /users/{id} - Get user by ID

GET /users?name=... - Filter users by name

### 🧪 Testing Strategy:
Comprehensive controller tests with MockMvc

JSON request/response validation

Error handling (404 Not Found)

Parameter filtering tests

## ⭐⭐⭐ Level 3 — Layered Architecture Refactor

###  🏗️ Architecture Layers:
1. Repository Layer
UserRepository interface

InMemoryUserRepository implementation

Data access abstraction

2. Service Layer
UserService interface

UserServiceImpl implementation

Business logic and validation

Email uniqueness enforcement

3. Controller Layer
UserController with dependency injection

HTTP request handling only

### 🧪 Advanced Testing:
Integration Tests: Full application testing with @SpringBootTest

Unit Tests: Service layer testing with Mockito

TDD Approach: Test-driven development for business rules

### 🔧 Key Spring Concepts Applied:
Inversion of Control (IoC) and Dependency Injection

Bean management with @Repository, @Service, @RestController

Layered architecture principles

SOLID principles implementation


# 🤝 Contributions
### ⭐ Star the repository

### 🍴 Fork the project

### 📥 Create a pull request

### 🌐 Deployment
For educational purposes only.

# 🚀 Thanks for Visiting = )
