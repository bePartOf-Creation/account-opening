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
**Account Creation:** Allows customers to open new accounts.
**Transaction Management:** Handles deposits, withdrawals, and keeps a record of each transaction.
**Pagination:** Supports efficient retrieval of transactions with pagination to handle large data volumes.

Running the Project
To run the project, use the command:


### Swagger Documentation
1. You can explore the API documentation using Swagger by going to:
   ```bash
   http://localhost:8080/account-opening/swagger-ui/index.html#/

###Future Enhancements
**Improved Caching:** Implement caching for frequently accessed data to improve performance.
**Enhanced Security:** Add authentication and authorization.
**Transaction History Search:** Enable searching transactions by date or amount.

