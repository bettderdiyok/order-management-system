# Order Management System

A backend Order Management System (OMS) built with Java 17 and Spring Boot, focused on production-style backend design.

## Key Concepts

- Layered architecture
- Clean Architecture principles
- DDD-inspired domain model
- CQRS-inspired separation at the API/use-case level (Command / Query / Workflow)
- Explicit order lifecycle handling

## Architecture

The project is structured into four layers:

- **API** → Controllers, DTOs, mappers
- **Application** → Use cases and results
- **Domain** → Aggregates and business rules
- **Infrastructure** → In-memory persistence (to be replaced by PostgreSQL)

Controllers are grouped by use-case type (command, query, workflow) to keep responsibilities clear and scalable.

## Current Features

- Create order
- Get order by id
- Order lifecycle management:
  - Pay
  - Prepare
  - Ship
  - Deliver
  - Cancel
- Domain-level validation

## API Endpoints

### Create Order
- POST /orders

### Get Order
- GET /orders/{id}

### Workflow Actions
- PATCH /orders/{id}/pay
- PATCH /orders/{id}/prepare
- PATCH /orders/{id}/ship
- PATCH /orders/{id}/deliver
- PATCH /orders/{id}/cancel

## Roadmap

- PostgreSQL + Flyway
- JWT security
- Docker
- Caching
- Domain events & projections
- CI/CD
