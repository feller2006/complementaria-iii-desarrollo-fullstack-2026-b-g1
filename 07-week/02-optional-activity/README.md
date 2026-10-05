\# Week 07 - Entity and Repository with JPA



\## Case: Product API



This activity models a product entity using JPA and defines a repository with CRUD operations.



\## Entity



The `Product` class represents a product in the database.



The main annotations used are:



\- `@Entity`: indicates that the class is a JPA entity.

\- `@Id`: defines the primary key.

\- `@GeneratedValue`: generates the identifier automatically.



The entity contains these attributes:



\- `id`

\- `name`

\- `price`

\- `quantity`



\## Repository



The `ProductRepository` interface extends:



`JpaRepository<Product, Long>`



This gives access to basic CRUD operations without writing SQL manually.



A custom query method was added:



`findByNameContainingIgnoreCase(String name)`



This method searches for products whose name contains the specified text, ignoring uppercase and lowercase letters.



\## CRUD Operations



\### Create



Used to register a new product in the database.



Example:



`repository.save(product)`



\### Read



Used to retrieve products from the database.



Examples:



`repository.findAll()`



`repository.findById(id)`



`repository.findByNameContainingIgnoreCase(name)`



\### Update



Used to modify an existing product.



The product is first searched by its id, its attributes are changed, and then it is saved again.



Example:



`repository.save(product)`



\### Delete



Used to remove a product from the database.



Example:



`repository.deleteById(id)`



\## Conclusion



Using JPA and `JpaRepository` makes database access easier because Spring Data JPA provides the main CRUD operations automatically.

