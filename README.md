# LittleDelhi Backend

REST API backend for a restaurant management system built with Spring Boot 4 and PostgreSQL.

## Tech Stack

- **Java 21**
- **Spring Boot 4.0.6** — Web MVC, Spring Data JPA, Spring Security
- **PostgreSQL 15**
- **Lombok**
- **Docker / Docker Compose**

## Getting Started

### Prerequisites

- Docker and Docker Compose installed
- Java 21+ (only needed if running without Docker)

### Run with Docker (recommended)

```bash
docker compose up --build
```

The app will be available at `http://localhost:8081`. PostgreSQL runs on port `5432`.

### Run locally

1. Start a PostgreSQL instance with database `littledelhi`, user `postgres`, password `password123` on port `5432`.
2. Run the app:

```bash
./mvnw spring-boot:run
```

The app will be available at `http://localhost:8080`.

## Project Structure

```
src/main/java/com/LittleDelhi/LittleDelhiBackend/
├── config/         # SecurityConfig
├── controller/     # REST controllers
├── dto/
│   ├── request/    # Request body DTOs
│   └── response/   # Response DTOs
├── mapper/         # Entity ↔ DTO mappers
├── model/          # JPA entities and enums
├── repository/     # Spring Data JPA repositories
└── service/        # Business logic
```

## API Endpoints

All endpoints are prefixed with `/api`.

### Ingredients

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/ingredients` | Get all ingredients |
| `GET` | `/api/ingredients/{id}` | Get ingredient by ID |
| `POST` | `/api/ingredients` | Create ingredient |
| `PUT` | `/api/ingredients/{id}` | Update ingredient |
| `DELETE` | `/api/ingredients/{id}` | Delete ingredient |

### Recipes

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/recipes` | Get all recipes |
| `GET` | `/api/recipes/{id}` | Get recipe by ID |
| `POST` | `/api/recipes` | Create recipe with ingredients |
| `PUT` | `/api/recipes/{id}` | Update recipe |
| `DELETE` | `/api/recipes/{id}` | Delete recipe |

### Menu Items

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/items` | Get all menu items |
| `GET` | `/api/items/{id}` | Get item by ID |
| `POST` | `/api/items` | Create menu item |
| `PUT` | `/api/items/{id}` | Update menu item |
| `DELETE` | `/api/items/{id}` | Delete menu item |

### Inventory

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/inventory` | Get all inventory records |
| `GET` | `/api/inventory/{id}` | Get inventory record by ID |
| `GET` | `/api/inventory/low-stock` | Get items below stock threshold |
| `POST` | `/api/inventory` | Create inventory record |
| `PUT` | `/api/inventory/{id}` | Update inventory record |
| `DELETE` | `/api/inventory/{id}` | Delete inventory record |

### Timesheets

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/timesheets` | Get all timesheets |
| `GET` | `/api/timesheets/{id}` | Get timesheet by ID |
| `GET` | `/api/timesheets/user/{userId}` | Get timesheets for a user |
| `POST` | `/api/timesheets/clock-in` | Clock in (creates timesheet entry) |
| `PUT` | `/api/timesheets/clock-out/{id}` | Clock out (calculates hours worked) |

### Users

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/users` | Get all users |
| `GET` | `/api/users/{id}` | Get user by ID |
| `POST` | `/api/users` | Create user |
| `DELETE` | `/api/users/{id}` | Delete user |

## Data Model

- **User** — name, username, password, role (`ADMIN` or `EMPLOYEE`)
- **Ingredient** — name, unit (`GRAMS`, `PIECES`, `KILOGRAMS`, `LITERS`, `MILLILITERS`, `OUNCES`)
- **Recipe** — name, list of `RecipeIngredient` (ingredient + quantity)
- **Item** — menu item linked 1:1 to a Recipe (cascade deletes Recipe and its ingredients)
- **Inventory** — links to an Ingredient, tracks quantity and low-stock threshold
- **TimeSheet** — links to a User, stores clock-in date/time, clock-out time, and computed hours worked

## Security

JWT-based authentication with role-based access control (`ADMIN` / `EMPLOYEE`) is currently in development.
