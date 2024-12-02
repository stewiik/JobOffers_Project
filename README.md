# Job Offers Application

The **Job Offers Application** is a Spring Boot-based backend web application designed for managing job offers. It allows users to interact with job listings, including fetching and adding offers. The application integrates with external API for fetching job listings and includes user authentication using JWT. MongoDB is used for data persistence, and Redis is implemented for caching.

## Features

  - Register new users and log in using secure credentials.
  - JWT-based authentication for secure API access.
  - Submit new job offers with details such as company name, position, salary, and URL.
  - Fetch job offers from an external API and save them if they don't already exist in the system.
  - View all offers or fetch a specific offer by ID.
  - Use Redis to cache job offers, improving response times.
  - Validate submitted job offers (e.g., required fields, unique URLs).
  - Centralized error handling for better user experience.
  - Includes tests using Testcontainers.
  - WireMock is used for external API simulation during testing.
  - Exposes REST API endpoints with documentation via Swagger.
 
## Architecture

The application is built with a modular monolith hexagonal approach architecture, following clean architecture principles. Key modules include:

- **User Authentication**:
  - Provides secure JWT-based login and token validation.
  - Implements centralized error handling for authentication-related issues.

- **Job Offers Management**:
  - Responsible for fetching and adding job offers.
  - Integrates with external job listing API for up-to-date offers.

- **Caching**:
  - Redis-based caching.

- **Error Handling**:
  - Centralized error handling for application-specific exceptions.
 
  ## Tech Stack

- Java 17
- Apache Maven
- Spring Boot
- MongoDB
- Redis
- JUnit, Testcontainers, WireMock
- Swagger for API Documentation


## REST API Endpoints

### Base URL: `http://localhost:8080`

| Endpoint         | Method | Request                     | Response | Description                              |
|-------------------|--------|-----------------------------|----------|------------------------------------------|
| `/register`       | POST   | RequestBody (RegisterUserDto) | JSON     | Register a new user                      |
| `/token`          | POST   | RequestBody (TokenRequest) | JSON     | Authenticate and generate JWT            |
| `/offers`         | GET    | -      | JSON     | Fetch all job offers                     |
| `/offers/{id}`    | GET    | PathVariable (id)  | JSON | Fetch a specific job offer by ID         |
| `/offers`         | POST   | RequestBody (OffertDto) | JSON | Submit a new job offer                   |



