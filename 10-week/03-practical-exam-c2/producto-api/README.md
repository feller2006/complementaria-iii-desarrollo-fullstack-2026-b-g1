# Practical Exam - Corte 2

## Product REST API

REST API developed with Spring Boot using a layered architecture.

The application manages products and implements CRUD operations, persistence with Spring Data JPA, validation, Swagger/OpenAPI documentation and endpoint testing with Postman.

---

## Project Information

- **Project:** producto-api
- **Group:** com.fullstack
- **Package:** com.fullstack.productoapi
- **Java:** 17
- **Spring Boot:** 4.1.1
- **Build Tool:** Maven
- **Database:** H2
- **API Documentation:** Swagger / OpenAPI

---

# Problem 1 - Model and Data Layer

## Entity

The main entity of the project is `Producto`.

The class is located at:

```text
src/main/java/com/fullstack/productoapi/entity/Producto.java
```

The entity contains the following attributes:

| Attribute | Type | Description |
|---|---|---|
| id | Long | Unique identifier of the product |
| nombre | String | Product name |
| precio | Double | Product price |
| categoria | String | Product category |

The entity uses JPA annotations such as:

- `@Entity`: identifies the class as a persistent JPA entity.
- `@Id`: defines the primary key.
- `@GeneratedValue`: automatically generates the ID.
- `GenerationType.IDENTITY`: delegates ID generation to the database.

Validation annotations are also used:

- `@NotBlank`: validates that text fields are not empty.
- `@NotNull`: validates that the price is provided.
- `@PositiveOrZero`: prevents negative prices.

Example:

```java
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotNull(message = "El precio es obligatorio")
    @PositiveOrZero(message = "El precio no puede ser negativo")
    private Double precio;

    @NotBlank(message = "La categoria es obligatoria")
    private String categoria;
}
```

---

## Repository

The repository is located at:

```text
src/main/java/com/fullstack/productoapi/repository/ProductoRepository.java
```

`ProductoRepository` extends:

```java
JpaRepository<Producto, Long>
```

This provides standard CRUD operations such as:

- `findAll()`
- `findById()`
- `save()`
- `existsById()`
- `deleteById()`

The project also includes a query created using the Spring Data JPA method-name convention:

```java
List<Producto> findByNombreContainingIgnoreCase(String nombre);
```

This method searches for products whose name contains the provided text without distinguishing between uppercase and lowercase letters.

For example, searching for:

```text
teclado
```

can find:

```text
Teclado mecanico
```

---

## Object-Relational Mapping - ORM

Object-Relational Mapping allows Java objects to be mapped to relational database structures.

In this project, the `Producto` Java class is mapped to a database table through JPA and Hibernate.

Each `Producto` object represents a row in the database table, while its attributes such as `id`, `nombre`, `precio` and `categoria` represent columns.

JPA provides the persistence abstraction and Hibernate performs the object-relational mapping implementation.

This makes it possible to store, search, update and delete Java objects without manually writing SQL statements for the basic CRUD operations.

---

# Layered Architecture

The application is organized into the following layers:

```text
src/main/java/com/fullstack/productoapi/
│
├── controller/
│   └── ProductoController.java
│
├── entity/
│   └── Producto.java
│
├── repository/
│   └── ProductoRepository.java
│
├── service/
│   └── ProductoService.java
│
└── ProductoApiApplication.java
```

The request flow is:

```text
Client
   |
   v
Controller
   |
   v
Service
   |
   v
Repository
   |
   v
Database
```

---

# Problem 2 - REST Endpoints

The REST controller is located at:

```text
src/main/java/com/fullstack/productoapi/controller/ProductoController.java
```

The base URL of the API is:

```text
/api/productos
```

The API uses nouns in the URLs according to REST conventions.

---

## CRUD Endpoints

| HTTP Method | Endpoint | Description | Status Code |
|---|---|---|---|
| GET | `/api/productos` | List all products | 200 OK |
| GET | `/api/productos/{id}` | Get a product by ID | 200 OK / 404 Not Found |
| POST | `/api/productos` | Create a new product | 201 Created |
| PUT | `/api/productos/{id}` | Update an existing product | 200 OK / 404 Not Found |
| DELETE | `/api/productos/{id}` | Delete an existing product | 204 No Content / 404 Not Found |
| GET | `/api/productos/buscar?nombre=texto` | Search products by name | 200 OK |

---

## GET - List Products

```http
GET /api/productos
```

Returns all products stored in the database.

Successful response:

```text
200 OK
```

Example response:

```json
[
  {
    "id": 1,
    "nombre": "Teclado mecanico",
    "precio": 150000.0,
    "categoria": "Tecnologia"
  }
]
```

---

## GET - Find Product by ID

```http
GET /api/productos/{id}
```

Example:

```http
GET /api/productos/1
```

If the product exists:

```text
200 OK
```

If the product does not exist:

```text
404 Not Found
```

---

## POST - Create Product

```http
POST /api/productos
```

Example JSON request body:

```json
{
  "nombre": "Teclado mecanico",
  "precio": 150000,
  "categoria": "Tecnologia"
}
```

Successful response:

```text
201 Created
```

Example response:

```json
{
  "id": 1,
  "nombre": "Teclado mecanico",
  "precio": 150000.0,
  "categoria": "Tecnologia"
}
```

The ID is generated automatically by the database.

---

## PUT - Update Product

```http
PUT /api/productos/{id}
```

Example:

```http
PUT /api/productos/1
```

Example body:

```json
{
  "nombre": "Teclado mecanico RGB",
  "precio": 180000,
  "categoria": "Tecnologia"
}
```

If the product exists:

```text
200 OK
```

If the product does not exist:

```text
404 Not Found
```

---

## DELETE - Delete Product

```http
DELETE /api/productos/{id}
```

Example:

```http
DELETE /api/productos/1
```

If the product exists and is deleted:

```text
204 No Content
```

If the product does not exist:

```text
404 Not Found
```

---

## Search Product by Name

The repository query by method name is exposed through:

```http
GET /api/productos/buscar?nombre=texto
```

Example:

```http
GET /api/productos/buscar?nombre=teclado
```

Successful response:

```text
200 OK
```

---

# English Requirement

The controller receives HTTP requests and returns the appropriate HTTP responses to the client. The service contains the business logic and coordinates the application operations. The repository communicates with the database and provides persistence operations using Spring Data JPA.

---

# HTTP Status Codes

The application uses appropriate HTTP status codes:

| Code | Name | Usage |
|---|---|---|
| 200 | OK | Successful GET or PUT operation |
| 201 | Created | Product successfully created |
| 204 | No Content | Product successfully deleted |
| 400 | Bad Request | Invalid request data |
| 404 | Not Found | Requested product does not exist |

---

# Problem 3 - Swagger and Postman

## Swagger / OpenAPI

The API is documented using SpringDoc OpenAPI.

The following dependency is included in `pom.xml`:

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>3.1.1</version>
</dependency>
```

Swagger automatically analyzes the REST controller and generates interactive documentation for the available endpoints.

After starting the application, Swagger UI can be accessed at:

```text
http://localhost:8080/swagger-ui.html
```

The OpenAPI specification is also available at:

```text
http://localhost:8080/v3/api-docs
```

Swagger shows the available HTTP methods, URLs, request parameters, request bodies and response schemas.

It can also be used to execute requests directly from the browser.

---

# Postman Tests

Postman was used to verify the behavior of the REST API.

Three main tests were performed.

---

## Test 1 - Create Product

Method:

```text
POST
```

URL:

```text
http://localhost:8080/api/productos
```

Request body:

```json
{
  "nombre": "Teclado mecanico",
  "precio": 150000,
  "categoria": "Tecnologia"
}
```

Expected status:

```text
201 Created
```

Example response:

```json
{
  "id": 1,
  "nombre": "Teclado mecanico",
  "precio": 150000.0,
  "categoria": "Tecnologia"
}
```

This test verifies that a product can be successfully created and stored in the database.

---

## Test 2 - List Products

Method:

```text
GET
```

URL:

```text
http://localhost:8080/api/productos
```

Expected status:

```text
200 OK
```

Example response:

```json
[
  {
    "id": 1,
    "nombre": "Teclado mecanico",
    "precio": 150000.0,
    "categoria": "Tecnologia"
  }
]
```

This test verifies that the API can retrieve the products stored in the database.

---

## Test 3 - Error Case

Method:

```text
GET
```

URL:

```text
http://localhost:8080/api/productos/999
```

Expected status:

```text
404 Not Found
```

The product with ID `999` does not exist, so the API correctly returns HTTP status `404 Not Found`.

This test verifies the error handling implemented in the controller.

---

# Validation

The application validates incoming data before creating or updating products.

The product name and category cannot be empty.

The price is required and cannot be negative.

Example of invalid data:

```json
{
  "nombre": "",
  "precio": -100,
  "categoria": ""
}
```

This request is considered invalid because it does not satisfy the validation rules defined in the entity.

---

# Technologies Used

- Java 17
- Spring Boot 4.1.1
- Spring Web
- Spring Data JPA
- Hibernate
- H2 Database
- Bean Validation
- SpringDoc OpenAPI
- Swagger UI
- Maven
- Postman

---

# Running the Application

Open a terminal inside the `producto-api` directory.

On Windows PowerShell:

```powershell
.\mvnw spring-boot:run
```

On Linux or macOS:

```bash
./mvnw spring-boot:run
```

When the application starts correctly, the terminal displays a message similar to:

```text
Started ProductoApiApplication
```

The application runs on port:

```text
8080
```

Base URL:

```text
http://localhost:8080
```

API endpoint:

```text
http://localhost:8080/api/productos
```

Swagger:

```text
http://localhost:8080/swagger-ui.html
```

---

# Example API Workflow

First create a product:

```http
POST /api/productos
```

```json
{
  "nombre": "Teclado mecanico",
  "precio": 150000,
  "categoria": "Tecnologia"
}
```

Then list all products:

```http
GET /api/productos
```

Find a specific product:

```http
GET /api/productos/1
```

Update the product:

```http
PUT /api/productos/1
```

```json
{
  "nombre": "Teclado mecanico RGB",
  "precio": 180000,
  "categoria": "Tecnologia"
}
```

Finally, delete the product:

```http
DELETE /api/productos/1
```

---

# Conclusion

The project implements a complete REST API following a layered architecture with entity, repository, service and controller components.

Spring Data JPA and Hibernate are used for object-relational mapping and database persistence.

The API implements the required CRUD operations with appropriate HTTP methods and status codes.

Swagger/OpenAPI provides interactive API documentation, while Postman was used to verify successful operations and error responses.