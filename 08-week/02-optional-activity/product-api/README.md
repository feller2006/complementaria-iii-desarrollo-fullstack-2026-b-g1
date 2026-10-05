\# Week 08 - REST CRUD with Spring Boot



\## Project



Product API developed with Spring Boot.



The project implements a complete REST CRUD using a layered architecture.



\## Technologies



\- Java 17

\- Spring Boot 4.1.1

\- Spring Web

\- Spring Data JPA

\- H2 Database

\- Maven

\- Postman



\## Project Structure



The application is organized in the following layers:



\- `entity`: defines the Product entity.

\- `repository`: provides database access using JpaRepository.

\- `service`: contains the business logic.

\- `controller`: exposes the REST endpoints.



\## Entity



The `Product` entity contains the following attributes:



\- `id`

\- `name`

\- `price`

\- `quantity`



\## REST Endpoints



\### Create Product



Method:



`POST`



URL:



`/api/products`



Example request:



```json

{

&#x20; "name": "Laptop",

&#x20; "price": 2500000,

&#x20; "quantity": 5

}



List Products

Method:

GET

URL:

/api/products

Get Product by ID

Method:

GET

URL:

/api/products/{id}

Update Product

Method:

PUT

URL:

/api/products/{id}

Example request:

{

&#x20; "name": "Laptop Gamer",

&#x20; "price": 2800000,

&#x20; "quantity": 8

}



Delete Product

Method:

DELETE

URL:

/api/products/{id}

CRUD Flow

The request flow follows this structure:

Client -> Controller -> Service -> Repository -> Database

The controller receives the HTTP request.

The service processes the business logic.

The repository communicates with the H2 database.

Tests

The CRUD was tested using Postman.

1\. Create Product

The product was created successfully using POST.

&#x20;

2\. List Products

The API returned the list of products using GET.

&#x20;

3\. Get Product by ID

The API returned the product with ID 1.

&#x20;

4\. Update Product

The product was updated successfully using PUT.

&#x20;

5\. Delete Product

The product was deleted successfully and the API returned HTTP 204 No Content.

&#x20;

6\. Verify Deleted Product

After deleting the product, a GET request returned HTTP 404 Not Found.

