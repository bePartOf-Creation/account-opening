# Account Opening Application

This is an Account Opening Application project built with **Spring Boot**. The application is designed to handle multiple customers, each with multiple accounts. Each account may contain a large number of transactions. The project supports efficient querying and pagination of transactions to manage large datasets effectively.

## Table of Contents
1. [Project Setup](#project-setup)
2. [Technologies Used](#technologies-used)
3. [Database Structure](#database-structure)
4. [Key Functionalities](#key-functionalities)
5. [Entities](#entities)
6. [Repositories](#repositories)
7. [Services](#services)
8. [Controllers](#controllers)
9. [API Endpoints](#api-endpoints)
10. [Exception Handling](#exception-handling)
11. [Running the Project](#running-the-project)
12. [Testing](#testing)
13. [Future Enhancements](#future-enhancements)

---

## Project Setup

### Prerequisites
- **Java 11** or later
- **Spring Boot 2.7.x**
- **Maven** (for dependency management)
- **H2 Database** (for database storage)

### Setup Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/account-opening-application.git
   cd account-opening-application

2. Install dependencies and build the project:
   ```bash
   mvn clean install

3. Run the application::
   ```bash
   mvn spring-boot:run


### Technologies Used
**Spring Boot:** Core framework for the application.
**Hibernate:** ORM for entity management.
**Spring Data JPA:** Simplified data access with JPA.
**H2 Database:** Database for storage.
**Lombok:** Reduces boilerplate code for entity classes.
**SLF4J:** Logging framework.

### Database Structure
The application has three main entities: **Customer**, **Account**, and **TransactionHistory**.

**Customer:** Represents each user with accounts and personal details.
**Account:** Represents an account for each customer, including types (e.g., savings, current) and balances.
**TransactionHistory:** Logs transactions for each account.

### Key Functionalities
**Current-Account Creation:** Allows customers to open new accounts.
**Customer Details Retrieval:** Fetch details by ``customerId`` operations
**Pagination:** Supports efficient retrieval of account transactions with pagination to handle large data volumes.

### Running the Project
- To run the project, use the command:
   ```bash
   mvn spring-boot:run

###  Configure the H2 Database
- **Database Connection parameters**
   ```bash
   server.servlet.context-path=/account-opening
   spring.datasource.url=jdbc:h2:mem:testdb
   spring.datasource.driver-class-name=org.h2.Driver
   spring.datasource.username=sa
   spring.datasource.password=sa
   
- **H2 Database Login URL**
   ``http://localhost:8080/account-opening/h2-console``



### Swagger Documentation
- You can explore the API documentation using Swagger by going to:
   ```bash
   http://localhost:8080/account-opening/swagger-ui/index.html#/

###Future Enhancements
**Improved Caching:** Implement caching for frequently accessed data to improve performance.
**Enhanced Security:** Add authentication and authorization.
**Transaction History Search:** Enable searching transactions by date or amount.

###  Testing APIs
1. Run the application on port **8080.**
2. Get an Existing Customer **id** from the **Customer** Table, from H2 Database.
3. Call the Endpoint.