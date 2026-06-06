# LittleDelhi Backend — Project Summary

Spring Boot REST API for restaurant management. Handles menu items, recipes, ingredients, inventory, users, and timesheets.

---

## Tech Stack

- Java / Spring Boot
- Spring Data JPA / Hibernate
- Lombok
- PostgreSQL

---

## Architecture

Standard layered architecture:

```
Controller → Service → Repository
                ↑
            Mapper (model → DTO)
```

- Constructor-based dependency injection throughout
- Dedicated mapper class per entity (no inline conversion in services)
- DTOs split into `request` and `response` packages

---

## Domain Models

| Model | Key Fields | Relationships |
|---|---|---|
| `User` | id, name, userName, password, role | — |
| `Ingredient` | id, name, costPricePerUnit, unit | — |
| `Recipe` | id, name, procedure | OneToMany → RecipeIngredient |
| `RecipeIngredient` | id, quantity | ManyToOne → Recipe, ManyToOne → Ingredient |
| `Item` | id, name, price | OneToOne → Recipe |
| `Inventory` | id, quantity, threshold | OneToOne → Ingredient |
| `TimeSheet` | id, date, clockIn, clockOut, hoursWorked | ManyToOne → User |

### Enums

- `Role` — `ADMIN`, `EMPLOYEE`
- `Unit` — `GRAMS`, `PIECES`, `KILOGRAMS`, `LITERS`, `MILLILITERS`, `OUNCES`

---

## Repositories

All extend `JpaRepository`. Custom queries noted below.

| Repository | Custom Methods |
|---|---|
| `UserRepository` | — |
| `IngredientRepository` | — |
| `RecipeRepository` | — |
| `RecipeIngredientRepository` | `findByRecipeId(Long)`, `deleteByRecipeId(Long)` |
| `ItemRepository` | — |
| `InventoryRepository` | — |
| `TimeSheetRepository` | — |

---

## Mappers

Each mapper is a `@Component` with injected dependencies where needed.

| Mapper | Input → Output | Dependencies |
|---|---|---|
| `IngredientMapper` | `Ingredient` → `IngredientResponse` | none |
| `RecipeIngredientMapper` | `RecipeIngredient` → `RecipeIngredientResponse` | `IngredientMapper` |
| `RecipeMapper` | `Recipe` → `RecipeResponse` | `RecipeIngredientRepository`, `RecipeIngredientMapper` |
| `ItemMapper` | `Item` → `ItemResponse` | `RecipeMapper` |

---

## Services

### IngredientService — complete
- `createIngredient`
- `getAllIngredients`
- `getIngredientById`
- `updateIngredient`
- `deleteIngredient`

### RecipeService — complete
- `createRecipe` — saves Recipe, then saves each RecipeIngredient row
- `getAllRecipes`
- `getRecipeById`
- `updateRecipe` — deletes old RecipeIngredient rows, inserts new ones
- `deleteRecipe` — deletes RecipeIngredient rows first, then the Recipe

### UserService — complete (no update yet)
- `createUser`
- `getAllUsers`
- `getUserById`
- `deleteUser`

### ItemService — in progress
- [x] `createItem` — validates recipe exists, saves Item, returns mapped response
- [ ] `getAllItems`
- [ ] `getItemById`
- [ ] `updateItem`
- [ ] `deleteItem`

### InventoryService — not started

### TimeSheetService — not started

---

## DTOs

### Requests
| Class | Purpose |
|---|---|
| `CreateUserRequest` | name, userName, password |
| `LoginRequest` | userName, password |
| `CreateIngredientRequest` | name, costPricePerUnit, unit |
| `UpdateIngredientRequest` | name, costPricePerUnit, unit |
| `CreateRecipeRequest` | name, procedure, ingredients (list of RecipeIngredientRequest) |
| `UpdateRecipeRequest` | name, procedure, ingredients |
| `RecipeIngredientRequest` | ingredientId, quantity |
| `CreateItemRequest` | name, price, recipeId |
| `UpdateItemRequest` | name, price, recipeId |
| `CreateInventoryRequest` | ingredientId, quantity, threshold |
| `UpdateInventoryRequest` | quantity, threshold |
| `ClockInRequest` | userId, date, clockIn |

### Responses
| Class | Fields |
|---|---|
| `UserResponse` | id, name, userName, role |
| `IngredientResponse` | id, name, unit |
| `RecipeIngredientResponse` | ingredient (IngredientResponse), quantity |
| `RecipeResponse` | id, name, procedure, ingredientInfo (list of RecipeIngredientResponse) |
| `ItemResponse` | id, name, price, recipe (RecipeResponse) |
| `InventoryResponse` | *(defined, not yet mapped)* |
| `TimeSheetResponse` | *(defined, not yet mapped)* |

---

## What's Left

- [ ] Complete `ItemService` (getAllItems, getItemById, updateItem, deleteItem)
- [ ] Implement `InventoryService`
- [ ] Implement `TimeSheetService`
- [ ] Add `UserMapper` (UserService still uses an inline private method)
- [ ] Build controllers for all services
- [ ] Implement authentication / Spring Security (LoginRequest exists, no auth logic yet)
