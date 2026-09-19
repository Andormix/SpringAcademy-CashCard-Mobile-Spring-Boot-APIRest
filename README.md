# CashCard App — Full-Stack Spring Boot & Ionic Mobile Ecosystem

<p align="center">
  <img width="1530" height="814" alt="CashCard Architecture Preview" src="https://github.com/user-attachments/assets/b08d051a-8ac0-4566-a6e6-375ccb36a953" />
</p>

> 🎓 **Academic & Certification Context:**
> The backend of this project represents my personal hands-on solution for the **"Building a REST API with Spring Boot"** lab, part of the official **Spring Certified Professional** learning path. It expands upon the core lab deliverables by integrating a modern full-stack ecosystem with a cross-platform mobile client.

---

A production-grade full-stack digital wallet solution combining a robust **Spring Boot 3 / Java 17** RESTful backend with an **Ionic 8 + Vue 3 (TypeScript)** cross-platform mobile client. Built using **Test-Driven Development (TDD)**, the application enforces strict owner-based access control, secure cross-origin communication (CORS), and dynamic mobile user interfaces.

---

## 📚 Course & Certification Info

* **Course:** *Building a REST API with Spring Boot*
* **Learning Path:** *Spring Certified Professional*
* **Core Topics Covered:** Project bootstrapping with Spring Initializr, RESTful CRUD endpoints, pagination/sorting, TDD integration testing, and principal-based authentication/authorization with Spring Security.

---

## Tech Stack & Core Architecture

### Backend (Spring Boot REST API)
* **Language & Framework:** Java 17 / OpenJDK, Spring Boot 3.x (Spring Web, Spring Security, Spring Data JDBC)
* **Security:** HTTP Basic Authentication, Principal-Based Access Control, Custom CORS Configuration
* **Testing:** JUnit 5, AssertJ, `TestRestTemplate`, `@JsonTest`, `JacksonTester`
* **Persistence & DB:** Spring Data `CrudRepository` / `PagingAndSortingRepository`, H2 In-Memory DB
* **Build System:** Gradle

### Frontend (Ionic Vue Mobile Client)
* **Framework & UI:** Vue 3 (Composition API `<script setup>`), Ionic Framework 8, IonIcons
* **HTTP Client & CORS:** Axios (configured with HTTP Basic Auth headers)
* **Mobile Design:** Dynamic gradient card themes, `ion-action-sheet` native modal overlays, responsive wallet layout
* **Language & Tooling:** TypeScript, Vite, Node.js

<p align="center">
  <img width="48%" alt="CashCard Architecture Preview" src="https://github.com/user-attachments/assets/194b73ea-113d-4f9b-8aa9-a84c160fbf9e" />
  <img width="48%" alt="Screenshot 2026-09-18 212138" src="https://github.com/user-attachments/assets/4874a2e0-e4fc-4a18-a5f1-a614365fd4e7" />
</p>

---

## Architecture & Core Engineering Patterns

* **Test-Driven Development (TDD):** Rigorously applied the Red-Green-Refactor cycle for domain logic, repository queries, and REST endpoint integration testing.
* **Owner-Based Access Control (OBAC):** Enforced strict resource isolation at the database layer using `Principal.getName()` and custom Spring Data queries (`findByIdAndOwner`).
* **Cross-Origin Resource Sharing (CORS):** Configured Spring Security CORS filters to safely expose endpoints to the Ionic mobile client running across web and native origins.
* **Dynamic UI & Reactive State:** Reactive Vue 3 state management with automatic card theme rotation using index modulo logic (`index % 4`) for visual distinction.
* **Native ActionSheets:** Replaced native browser alerts with Ionic's native `ion-action-sheet` controller for destructive delete operations.

---

## API Specification & Supported Operations

| Method | Endpoint | Description | Auth Required | Expected Status |
| :--- | :--- | :--- | :---: | :--- |
| **GET** | `/cashcards/{id}` | Retrieve a specific CashCard by ID | Yes | `200 OK` / `404 NOT_FOUND` |
| **GET** | `/cashcards?page=0&size=10` | List paginated CashCards owned by user | Yes | `200 OK` |
| **POST** | `/cashcards` | Create a new CashCard | Yes | `201 CREATED` |
| **PUT** | `/cashcards/{id}` | Update an existing CashCard amount | Yes | `204 NO_CONTENT` / `404 NOT_FOUND` |
| **DELETE** | `/cashcards/{id}` | Hard delete a CashCard | Yes | `204 NO_CONTENT` / `404 NOT_FOUND` |

> **Security Note:** Attempting to read, update, or delete a `CashCard` belonging to another user returns `404 NOT_FOUND` (or `403 FORBIDDEN`), completely hiding the existence of unauthorized resources.

---

## Project Structure

```text
cashcard-ecosystem/
├── backend/                               # Spring Boot RESTful API (Lab Solution)
│   └── src/main/java/com/andormix/cashcard/
│       ├── CashCard.java                 # Immutable Record Domain Model
│       ├── CashCardController.java       # REST Controller (HTTP mapping & Security Context)
│       ├── CashCardRepository.java       # Spring Data JDBC Repository & Custom Queries
│       └── SecurityConfig.java           # Spring Security (Basic Auth & CORS Config)
│
└── frontend/                              # Ionic Vue 3 Mobile Client
    └── src/
        ├── views/
        │   └── HomePage.vue              # Main Wallet Dashboard, Auth Form & Multi-color Cards
        ├── services/
        │   └── api.ts                    # Axios HTTP Client with Basic Auth Interceptors
        └── App.vue                       # Ionic Root Router Outlet Container
