# CashCard API — Spring Boot REST Service (TDD)

A RESTful Web Service built with Java 17 and Spring Boot following Test-Driven Development (TDD) principles. This project manages a `CashCard` financial resource, enforcing strict API contracts, JSON serialization/deserialization, and integration testing.

---

## Tech Stack

* **Language:** Java 17 / OpenJDK
* **Framework:** Spring Boot 4.x
* **Build Tool:** Gradle
* **Testing:** JUnit 5, AssertJ, Spring Boot Test (`TestRestTemplate`, `@JsonTest`, `JacksonTester`, `JsonPath`)
* **Persistence Layer:** Spring Data JDBC / CrudRepository
* **Database:** H2 In-Memory Database

---

## Key Features & Architecture

* **Test-Driven Development (TDD):** Red-Green-Refactor cycle applied across unit and integration tests.
* **Contract Testing:** Verified JSON serialization and payload schemas using `JacksonTester` and `JsonPath`.
* **Integration Testing:** Automated end-to-end HTTP request testing with `TestRestTemplate`.
* **Repository Pattern:** Abstracted database operations using Spring Data's `CrudRepository`.

<p align="center">
  <img width="743" height="653" alt="Spring Boot Execution" src="https://github.com/user-attachments/assets/194b73ea-113d-4f9b-8aa9-a84c160fbf9e" />
</p>

---

## Getting Started

### Prerequisites

* JDK 17 or higher
* IntelliJ IDEA (or any Java IDE)

### Installation & Execution

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/Andormix/SpringAcademy-spring-boot-rest-api-java-IntelliJ.git](https://github.com/Andormix/SpringAcademy-spring-boot-rest-api-java-IntelliJ.git)
   cd SpringAcademy-spring-boot-rest-api-java-IntelliJ
