# Library Management System

## Overview
A Java-based Library Management System to manage books, patrons, and the lending process efficiently.

## Requirements
* **Java JDK:** 21 
* **IDE:** IntelliJ IDEA
* **Build Tool:** Maven
* **Database:** H2 (In-memory)

## Project Structure
```bash
Library-Management/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── airtribe.LibraryManagement/
│   │   │           ├── advice/
│   │   │           │   └── ExceptionController.java
│   │   │           ├── controller/
│   │   │           │   ├── BookController.java
│   │   │           │   ├── LendingController.java
│   │   │           │   └── PatronController.java
│   │   │           ├── entity/
│   │   │           │   ├── Book.java
│   │   │           │   ├── Patron.java
│   │   │           │   └── BorrowedBook.java
│   │   │           ├── service/
│   │   │           │   ├── IBookService.java
│   │   │           │   ├── BookService.java
│   │   │           │   ├── IPatronService.java
│   │   │           │   ├── PatronService.java
│   │   │           │   ├── ILendingService.java
│   │   │           │   └── LendingService.java
│   │   │           ├── repository/
│   │   │           │   ├── BookRepository.java
│   │   │           │   ├── PatronRepository.java
│   │   │           ├── exception/
│   │   │           │   ├── DataAlreadyExistException.java
│   │   │           │   ├── ResourceNotFoundException.java
│   │   │           │   └── InvalidDataException.java
│   │   │           ├── config/
│   │   │           │   └── LoggingConfig.java
│   │   │           └── LibraryManagementApplication.java
│   │   └── resources/
│   │       └── application.properties
└── pom.xml
```

## Database Details
This project uses an in-memory H2 database for data persistence during development. Below are the configuration settings:
* **spring.datasource.url=** jdbc:h2:mem:LibraryManagementDB;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
* **spring.datasource.driverClassName=** org.h2.Driver
* **spring.datasource.username=** sachin
* **spring.datasource.password=** sachin
* **spring.h2.console.enabled=** true
* **spring.jpa.hibernate.ddl-auto=** update
* **spring.h2.console.path=** /h2-console

### Database Summary
* **Database:** In-memory H2 (LibraryManagementDB).
* **Access:** Use http://localhost:8080/h2-console for the web console.
* **Auto-update:** Hibernate manages schema changes automatically.

## System Design Overview
The system is designed using the MVC architecture with the following components:
* **Model:** Represents the data model with classes like Book, Patron, BorrowedBook.
* **Service:** Represents the business logic with classes like BookService, PatronService, LendingService.
* **Controller:** Represents the user interface with classes like BookController, PatronController, LendingController.
* **Repository:** Represents the data access layer with classes like BookRepository, PatronRepository, BorrowedBookRepository.
* **Exception:** Represents the custom exceptions with classes like ResourceNotFoundException, InvalidDataException, DataAlreadyExistException.

## OOP Concepts
* **Encapsulation:** Used private fields with public getters/setters.
* **Polymorphism:** Used interfaces for services, allowing for flexible implementations..
* **Abstraction:** Used interface for services.

## Design Patterns Used
* **Factory Pattern:** For creating instances of book and patron objects.
* **Strategy Pattern:** For implementing different lending strategies.

## REST API Endpoints
### Book Management
* **GET /library/book:** Get all books.
* **GET /library/book/{isbn}:** Get a book by ISBN.
* **POST /library/book:** Add a new book.
* **PUT /library/book/{isbn}:** Update a book by ISBN.
* **DELETE /library/book/{isbn}:** Delete a book by ISBN.
* **GET /library/book/available?isAvailable={boolean}:** Get all available books.
* **GET /library/book/title/{title}:** Get a book by title.
* **GET /library/book/author/{author}:** Get books by author.

### Patron Management
* **GET /library/patron:** Get all patrons.
* **GET /library/patron/{patronId}:** Get a patron by ID.
* **POST /library/patron:** Add a new patron.
* **PUT /library/patron/{patronId}:** Update a patron by ID.
* **DELETE /library/patron/{patronId}:** Delete a patron by ID.

### Lending Management
* **GET /library/lending/history/{patronId}:** Get borrowing history of a patron.
* **POST /library/lending/checkout/{bookTitle}/{patronId}:** Borrow a book.
* **POST /library/lending/return/{title}/{patronId}:** Return a borrowed book.
* **GET /library/lending:** Get all patrons borrowing history

## Clone and Run
* **Clone Link:** https://github.com/SachinDeoli/Library-Management.git
* **Run:** Open the project in IntelliJ IDEA and run the application.
* **Access:** Use http://localhost:8080 to access the application.