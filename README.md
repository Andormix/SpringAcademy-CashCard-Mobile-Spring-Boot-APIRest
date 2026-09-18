# CashCard API — Spring Boot RESTful Microservice (TDD & Security)

<p align="center">
  <img width="1530" height="814" alt="image" src="https://github.com/user-attachments/assets/b08d051a-8ac0-4566-a6e6-375ccb36a953" />
</p>

A production-grade RESTful Web Service built with **Java 17** and **Spring Boot**, fully driven by **Test-Driven Development (TDD)** principles. This service manages financial `CashCard` resources, implementing strict role-based/owner-based access controls, JSON payload contract testing, and relational persistence.

---

## Tech Stack & Key Tools

* **Language:** Java 17 / OpenJDK
* **Framework:** Spring Boot 4.x (Spring Web, Spring Security, Spring Data JDBC)
* **Testing:** JUnit 5, AssertJ, `TestRestTemplate`, `@JsonTest`, `JacksonTester`, `JsonPath`
* **Security:** Spring Security (HTTP Basic Authentication & Principal-Based Data Access)
* **Persistence & DB:** Spring Data `CrudRepository` / `PagingAndSortingRepository`, H2 In-Memory DB
* **Build System:** Gradle

<p align="center">
  <img width="743" height="653" alt="Spring Boot Execution" src="https://github.com/user-attachments/assets/194b73ea-113d-4f9b-8aa9-a84c160fbf9e" />
</p>

---

## Architecture & Core Concepts Applied

* **Test-Driven Development (TDD):** Rigorously followed the **Red-Green-Refactor** cycle for both domain logic and integration HTTP endpoints.
* **Owner-Based Access Control (OBAC):** Enforced resource isolation at the data layer using `Principal.getName()` and custom Spring Data query methods (e.g., `findByIdAndOwner`) to prevent unauthorized access or data tampering.
* **Contract & Integration Testing:** Isolated serialization/deserialization verification using `@JsonTest` and full-stack HTTP pipeline validation with `TestRestTemplate`.
* **Pagination & Sorting:** Optimized data retrieval endpoints using Spring Data's `PageRequest` to handle large collections efficiently.



## API Specification & Supported Operations

The API strictly adheres to HTTP semantics and RESTful standards:

| Method | Endpoint | Description | Auth Required | Expected Status |
| :--- | :--- | :--- | :---: | :--- |
| **GET** | `/cashcards/{id}` | Retrieve a specific CashCard by ID | Yes | `200 OK` / `404 NOT_FOUND` |
| **GET** | `/cashcards?page=0&size=10` | List paginated CashCards owned by user | Yes | `200 OK` |
| **POST** | `/cashcards` | Create a new CashCard | Yes | `201 CREATED` |
| **PUT** | `/cashcards/{id}` | Update an existing CashCard amount | Yes | `204 NO_CONTENT` / `404 NOT_FOUND` |
| **DELETE** | `/cashcards/{id}` | Hard delete a CashCard | Yes | `204 NO_CONTENT` / `404 NOT_FOUND` |

> **Security Note:** Attempting to read, update, or delete a `CashCard` belonging to another user returns a `404 NOT_FOUND` (or `403 FORBIDDEN`), hiding the existence of unauthorized resources.

---

## Project Structure

```text
src/main/java/com/andormix/cashcard/
├── CashCard.java                 # Immutable Record Domain Model
├── CashCardController.java       # REST Controller (HTTP mapping & Security Context)
├── CashCardRepository.java       # Spring Data JDBC Repository & Custom Query Methods
└── SecurityConfig.java           # Spring Security Authentication & Authorization Setup

