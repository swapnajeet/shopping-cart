# Shopping cart

This is a simple Spring Boot application to provide some common apis for a shopping cart backend.

## About

The application uses a MySql database to store and retrieve products, shopping cart and order information. Then the REST
endpoints are exposed using Spring Boot web framework. The application uses a in-memory basic authentication.

## Tools and technologies

* Springboot
* Spring Data JPA
* Hibernate
* MySql
* Liquibase
* Maven build
* Java-11

## Getting Started

The application uses MySql running on Docker. So, the MySql container should be running before starting the application.

### How to run MySql docker container

To run the MySql container run the following command in the application root directory.
> $ docker-compose up

To run MySql container in background run the following command
> $ docker-compose up -d

### How to run the application

In the application root directory run the following command
> $ ./mvnw spring-boot:run

### How to get the api specification

The applications use OpenAPI specifications for the rest endpoints. Once the application is up and running the api
specification can be accessed via a web browser to from command line.

#### From web browser

> http://localhost:8080/

#### From command line

> $ curl http://localhost:8080/v1/api-docs

### Authentication

The application uses in-memory authentication with 3 user defined.
> admin:admin
> user:user
> customer:customer

As the above usernames indicate, 'admin' has ADMIN role, 'user' has USER role and 'customer' has CUSTOMER role
respectively.

### How to invoke the apis

The apis can be executed both from the Swagger UI in the web browser or curl command.

#### From web browser

* Go to the api specification page.
* Select the endpoint to invoke.
* Enter the inputs if needed.
* Execute the operation.
* The result should appear under the 'Response' section.

#### From command line

* use the curl command with the api to execute.

For example to create a product:
> curl -X POST "http://localhost:8080/api/v1/products/add" 
> -H "accept: */*" 
> -H "Content-Type: application/json" 
> -d "{\"name\":\"Test product\",\"description\":\"This product has been created from a test.\",\"price\":200}"

### Free text search
The application provides an api to search products by their name and the search supports pagination. This is available 
in the api specification and below is an example of it from command line.

> http://localhost:8080/api/v1/products/search/?textToSearch=Test&pageIndex=1&pageSize=10