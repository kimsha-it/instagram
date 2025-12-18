# GEMINI.md

This document provides a guide for developers working on the Instagram Clone project.

## Project Overview

This project is a web application inspired by Instagram, built with Java and the Spring Boot framework. It's a monolithic application with a server-side rendered frontend using Thymeleaf. The application allows users to create accounts, post images, and interact with other users' posts.

**Key Technologies:**

*   **Backend:** Java 21, Spring Boot
*   **Data Persistence:** Spring Data JPA, Hibernate, MySQL
*   **Security:** Spring Security (form-based login)
*   **Frontend:** Thymeleaf
*   **Build Tool:** Gradle
*   **Other:** Lombok, Spring Boot Devtools

## Building and Running

### Prerequisites

*   Java 21
*   MySQL server

### Database Setup

1.  Create a MySQL database named `instagram`.
2.  Update the `src/main/resources/application.properties` file with your MySQL username and password.

### Running the Application

1.  **Using Gradle:**
    ```bash
    ./gradlew bootRun
    ```
2.  **From your IDE:**
    *   Import the project as a Gradle project.
    *   Run the `InstagramApplication` class.

The application will be accessible at `http://localhost:8080`.

## Development Conventions

### Code Style

This project follows the standard Java and Spring Boot conventions.

### Database Migrations

The `spring.jpa.hibernate.ddl-auto` property is set to `update`, which means Hibernate will automatically update the database schema based on the entities. For more complex schema changes, it's recommended to use a database migration tool like Flyway or Liquibase.

### File Uploads

Uploaded files are stored in the `uploads` directory. Make sure this directory exists at the root of the project.

### Authentication and Authorization

Authentication is handled by Spring Security. The security configuration is defined in `src/main/java/com/example/instagram/config/SecurityConfig.java`. Publicly accessible paths are configured in this file. All other paths require authentication.
