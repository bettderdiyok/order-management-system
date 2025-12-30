# Order Management System
A backend Order Management System built with Java and Spring Boot,
following a layered architecture and applying domain-driven design principles.

## Current Features

- Create Order API (`POST /orders`)
- End-to-end vertical slice from HTTP request to persistence
- Layered architecture:
    - API layer (controllers, request/response DTOs)
    - Application layer (use cases, commands, results)
    - Domain layer (Order, OrderItem, OrderStatus with business rules)
    - Infrastructure layer (in-memory repository)
- Domain-level validation enforced inside domain models

## API Example

The Create Order endpoint can be tested using tools like Postman or curl.
### Create Order

**POST** `/orders`

Request body:
```json
{
  "items": [
    {
      "productId": "11111111-1111-1111-1111-111111111111" ,
      "quantity": 2
    }
  ]
}