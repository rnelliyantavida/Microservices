# Microservices Architecture Project

This repository contains a microservices-based architecture built with Spring Boot and Java. It demonstrates modular design using separate services for user management, departments, country data, and an API Gateway to route requests between them.

## 📁 Project Structure

The project is organized into the following services:

- **apigateway/** – Acts as the entry point to the system, routing requests to respective microservices.
- **user_service/** – Manages user-related data and APIs.
- **department_service/** – Manages department-related data and APIs.
- **CountryService/** – Handles country-specific operations and APIs.
- **CountryServiceDB/** – Contains database setup and configuration for the CountryService.

Each service is developed independently and can be deployed and scaled individually.

---

## 🚀 Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6+
- (Optional) Docker
- MySQL for CountryServiceDB

### Clone the Repository

```bash
git clone https://github.com/rnelliyantavida/Microservices.git
cd Microservices
