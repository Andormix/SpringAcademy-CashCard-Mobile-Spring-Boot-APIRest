# CashCard App — Spring Boot REST API & Vue Mobile Client

[![Java](https://img.shields.io/badge/Java-26-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](#)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](#)
[![Gradle](https://img.shields.io/badge/Build-Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)](#)
[![Vue](https://img.shields.io/badge/Vue-3-4FC08D?style=for-the-badge&logo=vuedotjs&logoColor=white)](#)
[![TypeScript](https://img.shields.io/badge/TypeScript-Frontend-3178C6?style=for-the-badge&logo=typescript&logoColor=white)](#)
[![TDD](https://img.shields.io/badge/Testing-TDD-9C27B0?style=for-the-badge)](#)

<p align="center">
  <img
    width="1530"
    height="814"
    alt="CashCard Architecture Preview"
    src="https://github.com/user-attachments/assets/b08d051a-8ac0-4566-a6e6-375ccb36a953"
  />
</p>

A full-stack digital wallet application built with a **Spring Boot REST API** and a **Vue-based mobile client**.

The backend provides secure CRUD operations for digital cash cards, uses Spring Data JDBC for persistence, applies Spring Security authorization, and includes automated tests following Test-Driven Development principles.

The frontend is built with Vue, TypeScript, and Ionic to provide a responsive mobile interface for managing cash cards.

---

## Table of Contents

- [Project Overview](#project-overview)
- [Academic Context](#academic-context)
- [Features](#features)
- [Architecture](#architecture)
- [Technology Stack](#technology-stack)
- [Backend](#backend)
- [Frontend](#frontend)
- [REST API](#rest-api)
- [Authentication and Authorization](#authentication-and-authorization)
- [Persistence](#persistence)
- [Testing Strategy](#testing-strategy)
- [Project Structure](#project-structure)
- [Requirements](#requirements)
- [Installation](#installation)
- [Running the Backend](#running-the-backend)
- [Running the Frontend](#running-the-frontend)
- [Example API Requests](#example-api-requests)
- [Configuration](#configuration)
- [Troubleshooting](#troubleshooting)
- [Future Improvements](#future-improvements)
- [License](#license)

---

## Project Overview

The project implements a digital wallet system centered around the `CashCard` resource.

Authenticated users can:

- Create cash cards.
- Retrieve individual cash cards.
- List their own cash cards.
- Update cash card amounts.
- Delete cash cards.
- Access the API using HTTP Basic Authentication.
- Use a Vue and Ionic client to interact with the backend.

The application was developed following a test-first approach, with tests covering JSON serialization, REST endpoints, repository behavior, and authorization rules.

---

## Academic Context

This project represents practical work based on the Spring learning path:

> **Building a REST API with Spring Boot**

The project focuses on the core principles required to build production-oriented Spring applications:

- RESTful API design.
- Spring Boot application configuration.
- CRUD operations.
- Repository-based persistence.
- Authentication and authorization.
- JSON serialization.
- Integration testing.
- Test-Driven Development.
- Frontend and backend integration.

---

## Features

### Backend Features

- RESTful API built with Spring Boot.
- CRUD operations for `CashCard` resources.
- HTTP Basic Authentication.
- Owner-based authorization.
- Spring Security integration.
- Spring Data JDBC repositories.
- H2 database support.
- Pagination and sorting.
- JSON serialization and deserialization tests.
- REST integration tests.
- Custom CORS configuration.
- Gradle build automation.

### Frontend Features

- Vue 3 interface.
- TypeScript-based frontend code.
- Ionic mobile UI components.
- Responsive digital wallet layout.
- HTTP communication with Axios.
- Basic Authentication headers.
- Card creation and deletion flows.
- Dynamic card color themes.
- Ionic action sheets for destructive actions.

---

## Architecture

<p align="center">
  <img width="48%" alt="CashCard Architecture Preview" src="https://github.com/user-attachments/assets/194b73ea-113d-4f9b-8aa9-a84c160fbf9e" />
  <img width="48%" alt="Screenshot 2026-09-18 212138" src="https://github.com/user-attachments/assets/4874a2e0-e4fc-4a18-a5f1-a614365fd4e7" />
</p>

---

## Technology Stack

### Backend

| Technology | Purpose |
| :--- | :--- |
| Java | Backend programming language |
| Spring Boot | Application framework |
| Spring Web MVC | REST endpoint implementation |
| Spring Security | Authentication and authorization |
| Spring Data JDBC | Repository and persistence abstraction |
| H2 | In-memory relational database |
| Jackson | JSON serialization and deserialization |
| JUnit 5 | Automated testing |
| Gradle | Build automation and dependency management |

### Frontend

| Technology | Purpose |
| :--- | :--- |
| Vue 3 | Frontend framework |
| TypeScript | Type-safe frontend development |
| Ionic | Mobile UI components |
| Axios | HTTP client |
| Vite | Frontend development and build tooling |
| Ionicons | User interface icons |

---

## Backend

The backend is implemented as a Spring Boot application.

The current Gradle configuration uses:

- Spring Boot `4.1.1`.
- Java toolchain version `26`.
- Spring Web MVC.
- Spring Data JDBC.
- H2.
- Spring Security.
- JUnit Platform.

> The project currently uses **Spring Data JDBC**, not Spring Data JPA. Spring Data JDBC provides repository-based persistence while keeping the data-access model simpler than a full JPA/Hibernate setup.

### Main Backend Responsibilities

The backend is responsible for:

1. Receiving HTTP requests.
2. Authenticating users.
3. Authorizing access to cash cards.
4. Validating request data.
5. Executing CRUD operations.
6. Serializing responses as JSON.
7. Returning appropriate HTTP status codes.
8. Applying owner-based access restrictions.
9. Running automated tests.

---

## Frontend

The frontend is located in the `cashcard-mobile-app` directory.

It provides a mobile-oriented interface for interacting with the backend API.

### Frontend Responsibilities

- Display the user's cash cards.
- Submit authentication credentials.
- Send API requests through Axios.
- Create new cash cards.
- Display card amounts.
- Delete cards through an action sheet.
- Apply visual themes to cards.
- Handle API responses and errors.

### User Interface

The interface uses Ionic components to provide a mobile-friendly experience, including:

- Ionic pages.
- Responsive layouts.
- Native-style action sheets.
- Buttons and input controls.
- Dynamic card styling.
- Navigation using the Ionic application structure.

---

## REST API

The backend exposes REST endpoints for managing `CashCard` resources.

### Supported Operations

| Method | Endpoint | Description | Authentication | Expected Status |
| :--- | :--- | :--- | :---: | :--- |
| `GET` | `/cashcards/{id}` | Retrieve one cash card | Required | `200 OK` or `404 NOT_FOUND` |
| `GET` | `/cashcards?page=0&size=10` | List authenticated user's cards | Required | `200 OK` |
| `POST` | `/cashcards` | Create a new cash card | Required | `201 CREATED` |
| `PUT` | `/cashcards/{id}` | Update an existing cash card | Required | `204 NO_CONTENT` or `404 NOT_FOUND` |
| `DELETE` | `/cashcards/{id}` | Delete an existing cash card | Required | `204 NO_CONTENT` or `404 NOT_FOUND` |

---

## CashCard Resource

A cash card represents a digital wallet card owned by a user.

A typical resource may contain values similar to:

```json
{
  "id": 99,
  "amount": 123.45,
  "owner": "sarah1"
}
```

### Resource Fields

| Field | Description |
| :--- | :--- |
| `id` | Unique identifier of the cash card |
| `amount` | Monetary amount assigned to the card |
| `owner` | Username of the authenticated card owner |

---

## Authentication and Authorization

The application uses Spring Security with HTTP Basic Authentication.

Clients must provide valid credentials when calling protected endpoints.

### Basic Authentication Header

```http
Authorization: Basic <base64-encoded-credentials>
```

For example:

```bash
curl -u "sarah1:password" http://localhost:8080/cashcards
```

### Owner-Based Access Control

Users can only access cash cards that belong to them.

The authorization flow uses the authenticated principal:

```java
Principal.getName()
```

Repository queries can restrict access based on both the card ID and the authenticated owner:

```text
findByIdAndOwner(id, owner)
```

This prevents users from viewing, modifying, or deleting another user's cash cards.

### Unauthorized Resource Handling

If a user attempts to access a cash card owned by another user, the API can return:

```text
404 NOT_FOUND
```

This behavior prevents the API from revealing whether an unauthorized resource exists.

---

## Persistence

The project uses Spring Data JDBC for database access.

### Database

The default development database is H2.

H2 is useful for:

- Local development.
- Automated tests.
- Lightweight application execution.
- Temporary persistence during development.

### Repository Layer

The repository layer is responsible for:

- Saving cash cards.
- Finding cash cards by ID.
- Finding cards by owner.
- Applying pagination.
- Updating card values.
- Deleting cards.
- Restricting queries to authenticated owners.

---

## Testing Strategy

Testing is a central part of this project.

The backend follows the **Red-Green-Refactor** cycle:

1. Write a failing test.
2. Implement the minimum code required to pass.
3. Refactor the implementation.
4. Keep the test suite passing.

### Testing Areas

#### JSON Serialization

Tests validate that Java objects are correctly serialized into JSON and that JSON payloads can be deserialized into Java objects.

Relevant tools include:

- `@JsonTest`
- `JacksonTester`

#### REST API Integration

The application uses REST testing tools to verify:

- HTTP methods.
- Endpoint behavior.
- Request bodies.
- Response bodies.
- Status codes.
- Authentication.
- Authorization.

#### Repository Testing

Repository tests verify:

- Persistence behavior.
- Queries by ID.
- Queries by owner.
- Pagination.
- Sorting.
- Data isolation.

#### Security Testing

Security tests verify that:

- Unauthenticated users cannot access protected resources.
- Authenticated users can access their own cards.
- Users cannot access cards belonging to another user.
- Unauthorized operations return the expected status code.

### Running Tests

On Linux or macOS:

```bash
./gradlew test
```

On Windows:

```powershell
gradlew.bat test
```

The Gradle configuration is set to display:

- Passed tests.
- Skipped tests.
- Failed tests.
- Exceptions.
- Stack traces.
- Standard output.

---

## Project Structure

```text
.
├── .gitattributes
├── .gitignore
├── README.md
├── build.gradle
├── gradle/
│   └── wrapper/
├── gradlew
├── gradlew.bat
├── settings.gradle
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── andormix/
│   │   │           └── cashcard/
│   │   │               ├── CashCard.java
│   │   │               ├── CashCardController.java
│   │   │               ├── CashCardRepository.java
│   │   │               └── SecurityConfig.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/
│               └── andormix/
│                   └── cashcard/
│                       ├── CashCardJsonTest.java
│                       ├── CashCardControllerTest.java
│                       └── CashCardRepositoryTest.java
└── cashcard-mobile-app/
    ├── src/
    │   ├── views/
    │   │   └── HomePage.vue
    │   ├── services/
    │   │   └── api.ts
    │   └── App.vue
    ├── package.json
    ├── tsconfig.json
    └── vite.config.ts
```

> The exact package and test filenames may vary as the project continues to evolve.

---

## Requirements

### Backend Requirements

- Java Development Kit compatible with the configured Gradle toolchain.
- Java `26`, according to the current `build.gradle`.
- Gradle Wrapper.
- Internet connection for downloading Gradle and Maven dependencies.

### Frontend Requirements

- Node.js.
- npm or another compatible package manager.
- Ionic-compatible development environment.
- Modern web browser or mobile emulator.

---

## Installation

### 1. Clone the Repository

```bash
git clone https://github.com/Andormix/SpringAcademy-building-rest-api-spring-boot-TDD-lab.git
cd SpringAcademy-building-rest-api-spring-boot-TDD-lab
```

### 2. Verify Java

```bash
java -version
```

The project is configured with the following Java toolchain:

```gradle
languageVersion = JavaLanguageVersion.of(26)
```

### 3. Verify Gradle Wrapper

On Linux or macOS:

```bash
./gradlew --version
```

On Windows:

```powershell
gradlew.bat --version
```

### 4. Build the Backend

Linux or macOS:

```bash
./gradlew build
```

Windows:

```powershell
gradlew.bat build
```

---

## Running the Backend

### Linux or macOS

```bash
./gradlew bootRun
```

### Windows

```powershell
gradlew.bat bootRun
```

The API is normally available at:

```text
http://localhost:8080
```

---

## Running the Frontend

Move into the mobile application directory:

```bash
cd cashcard-mobile-app
```

Install frontend dependencies:

```bash
npm install
```

Start the development server:

```bash
npm run dev
```

The terminal will display the local development URL, usually similar to:

```text
http://localhost:5173
```

The frontend must be configured to communicate with the Spring Boot backend.

---

## Example API Requests

### List Cash Cards

```bash
curl -u "sarah1:password" \
  "http://localhost:8080/cashcards?page=0&size=10"
```

### Retrieve a Cash Card

```bash
curl -u "sarah1:password" \
  "http://localhost:8080/cashcards/99"
```

### Create a Cash Card

```bash
curl -i -X POST \
  -u "sarah1:password" \
  -H "Content-Type: application/json" \
  -d '{"amount":250.00}' \
  "http://localhost:8080/cashcards"
```

Expected response:

```text
201 CREATED
```

### Update a Cash Card

```bash
curl -i -X PUT \
  -u "sarah1:password" \
  -H "Content-Type: application/json" \
  -d '{"amount":300.00}' \
  "http://localhost:8080/cashcards/99"
```

Expected response:

```text
204 NO_CONTENT
```

### Delete a Cash Card

```bash
curl -i -X DELETE \
  -u "sarah1:password" \
  "http://localhost:8080/cashcards/99"
```

Expected response:

```text
204 NO_CONTENT
```

---

## Example JavaScript Request

The frontend can call the API using Axios:

```typescript
import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080",
  headers: {
    "Content-Type": "application/json"
  }
});

export async function getCashCards(username: string, password: string) {
  const credentials = btoa(`${username}:${password}`);

  const response = await api.get("/cashcards?page=0&size=10", {
    headers: {
      Authorization: `Basic ${credentials}`
    }
  });

  return response.data;
}
```

> For production applications, do not store credentials insecurely in browser storage. Use a stronger authentication flow and HTTPS.

---

## Configuration

### Backend Configuration

Backend configuration can be placed in:

```text
src/main/resources/application.properties
```

Typical settings may include:

```properties
server.port=8080
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:cashcard
```

The exact configuration depends on the current implementation.

### Frontend API URL

The frontend should point to the backend server:

```typescript
const API_BASE_URL = "http://localhost:8080";
```

If the backend is running on another machine, replace `localhost` with the machine's local network IP address.

Example:

```typescript
const API_BASE_URL = "http://192.168.1.100:8080";
```

### CORS

When the frontend and backend run on different ports, the Spring Boot application must allow the frontend origin through CORS configuration.

Typical development origins include:

```text
http://localhost:5173
http://localhost:8100
```

Only trusted origins should be allowed in production.

---

## Gradle Commands

### Compile the Project

```bash
./gradlew compileJava
```

### Run Tests

```bash
./gradlew test
```

### Build the Application

```bash
./gradlew build
```

### Clean Build Files

```bash
./gradlew clean
```

### Run the Application

```bash
./gradlew bootRun
```

### View Dependencies

```bash
./gradlew dependencies
```

On Windows, replace `./gradlew` with:

```powershell
gradlew.bat
```

---

## Troubleshooting

### Java Version Error

If Gradle cannot find the required Java version:

1. Verify the installed JDK.
2. Check `JAVA_HOME`.
3. Confirm that the configured toolchain is available.
4. Install a compatible JDK version.

Check the current Java version:

```bash
java -version
```

### Port 8080 Is Already in Use

Run the backend on another port by adding the following configuration:

```properties
server.port=8081
```

Then access the API through:

```text
http://localhost:8081
```

### Frontend Cannot Reach the Backend

Check:

- The Spring Boot application is running.
- The frontend API base URL is correct.
- The backend port is correct.
- CORS configuration allows the frontend origin.
- The firewall is not blocking the connection.
- Both applications are using compatible protocols.

### Authentication Fails

Check:

- The username and password are correct.
- The `Authorization` header is being sent.
- The credentials are encoded correctly.
- The endpoint requires authentication.
- The backend security configuration is active.

### A User Cannot Access a Cash Card

Verify:

- The authenticated username matches the card owner.
- The card exists.
- The repository query includes the authenticated owner.
- The request is not attempting to access another user's resource.

### Tests Fail During Build

Run the tests with additional logging:

```bash
./gradlew test --info
```

For more detailed output:

```bash
./gradlew test --stacktrace
```

---

## Security Considerations

This project is intended for learning and development.

Before using the application in production, consider adding:

- HTTPS.
- Token-based authentication.
- Secure password hashing.
- Externalized secrets.
- Database migrations.
- Input validation.
- Rate limiting.
- CSRF protection where applicable.
- Stronger CORS restrictions.
- Secure frontend credential handling.
- Structured application logging.
- Audit logging.
- Role-based authorization.
- Production database configuration.

Do not commit passwords, API keys, or database credentials to the repository.

---

## Future Improvements

Potential future enhancements include:

- Replace HTTP Basic Authentication with OAuth2 or JWT.
- Add user registration and account management.
- Add persistent PostgreSQL or MySQL support.
- Add database migrations with Flyway or Liquibase.
- Add OpenAPI and Swagger documentation.
- Add Docker and Docker Compose support.
- Add CI/CD with automated Gradle builds.
- Add test coverage reporting.
- Add contract testing between frontend and backend.
- Add advanced card filtering and sorting.
- Add transaction history.
- Add spending categories.
- Add balance summaries and analytics.
- Add pagination controls to the frontend.
- Add offline support for the mobile client.
- Add environment-specific configuration profiles.
- Add end-to-end browser tests.

---

## License

This project is intended for educational, academic, and portfolio purposes.

Unless otherwise specified, the source code is provided for learning and experimentation. Please credit the repository if you reuse or adapt significant parts of the implementation.

---

## Author

Developed by **Andormix** as part of practical Spring Boot and REST API training.


