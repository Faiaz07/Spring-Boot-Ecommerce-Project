# E-commerce Project

This is a Spring Boot-based e-commerce project that provides a RESTful API for managing products.

## Features

- CRUD operations for products
- Product search functionality
- Image upload and retrieval for products

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- RESTful API
- PostgreSQL

## Project Structure

The project follows a typical Spring Boot application structure:

- `controller`: Contains the `ProductController` which handles HTTP requests
- `service`: Contains the `ProductService` which implements business logic
- `repo`: Contains the `ProductRepo` interface for database operations
- `model`: Contains the `Product` entity class

## API Endpoints

- `GET /api/`: Welcome message
- `GET /api/products`: Retrieve all products
- `GET /api/product/{prodId}`: Retrieve a specific product by ID
- `POST /api/product`: Add a new product along wth full details
- `GET /api/product/{productId}/image`: Retrieve the image for a specific product
- `PUT /api/product/{productId}`: Update an existing product
- `DELETE /api/product/{productId}`: Delete a product
- `GET /api/products/search`: Search for products based on a keyword
