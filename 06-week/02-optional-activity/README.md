## Case: Product API

This activity presents the layered architecture of a REST API for product management.

## Architecture Diagram

```text
Client
  |
  v
ProductController
  |
  v
ProductService
  |
  v
ProductRepository
  |
  v
Product Entity / Database

## Layer Responsibilities
Controller
ProductController receives HTTP requests from the client and returns the corresponding HTTP responses.
Responsibilities:
- Receive requests.
- Validate basic request data.
- Call the service layer.
- Return HTTP responses.
Service
ProductService contains the business logic of the application.
Responsibilities:
- Validate product information.
- Apply business rules.
- Communicate with the repository layer.
Repository
ProductRepository communicates with the database.
Responsibilities:
- Save products.
- Search products.
- Update products.
- Delete products.
Entity
Product represents the data model of a product.
Example attributes:
- id
- name
- price
- quantity
Example Endpoint
POST /api/products

This endpoint creates a new product.
Endpoint Flow
POST /api/products
        |
        v
ProductController
        |
        v
ProductService
        |
        v
ProductRepository
        |
        v
Product Entity / Database

Flow Explanation
The client sends a request to POST /api/products with the product data.
ProductController receives the HTTP request and sends the information to ProductService.
ProductService applies the business logic and sends the data to ProductRepository.
ProductRepository stores the Product entity in the database.
Finally, the result returns through the same layers until the client receives the HTTP response.
