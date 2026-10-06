# Producto API

API REST desarrollada con Spring Boot para gestionar productos.

Este proyecto corresponde a la Semana 10 de Desarrollo Fullstack. Implementa un CRUD REST completo utilizando arquitectura por capas, persistencia con JPA, documentacion con Swagger y pruebas con Postman.

## Tecnologias

- Java 17
- Spring Boot 4.1.1
- Spring Web
- Spring Data JPA
- H2 Database
- Bean Validation
- Swagger / OpenAPI
- Maven
- Postman

## Arquitectura

La aplicacion esta organizada en las siguientes capas:

- controller: recibe las peticiones HTTP.
- service: contiene la logica de negocio.
- repository: gestiona el acceso a los datos.
- entity: representa la entidad Producto.

Flujo:

Client -> Controller -> Service -> Repository -> Database

## Como ejecutar la API

Desde la carpeta raiz del proyecto ejecutar:

.\mvnw.cmd spring-boot:run

La aplicacion inicia en:

http://localhost:8080

## Swagger

La documentacion Swagger esta disponible en:

http://localhost:8080/swagger-ui.html

La especificacion OpenAPI esta disponible en:

http://localhost:8080/v3/api-docs

## Endpoints

### Listar productos

Metodo: GET

Endpoint: /api/productos

Codigo esperado: 200 OK

### Obtener producto por ID

Metodo: GET

Endpoint: /api/productos/{id}

Codigos esperados:

- 200 OK
- 404 Not Found

### Crear producto

Metodo: POST

Endpoint: /api/productos

Codigo esperado: 201 Created

### Actualizar producto

Metodo: PUT

Endpoint: /api/productos/{id}

Codigos esperados:

- 200 OK
- 404 Not Found

### Eliminar producto

Metodo: DELETE

Endpoint: /api/productos/{id}

Codigos esperados:

- 204 No Content
- 404 Not Found

## Persistencia

La API utiliza Spring Data JPA y una base de datos H2 para almacenar los productos.

## Pruebas

Los endpoints fueron probados utilizando Postman.

Se probaron las operaciones de:

- Crear producto.
- Listar productos.
- Obtener producto por ID.
- Actualizar producto.
- Eliminar producto.
- Manejo de errores.

## Evidencias

Las capturas de las pruebas se encuentran en la carpeta:

evidencias

## Conclusion

La API implementa un CRUD REST completo utilizando arquitectura por capas. Tambien cuenta con persistencia, documentacion mediante Swagger y pruebas realizadas con Postman.