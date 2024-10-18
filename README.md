# Library Management System

## Overview
A Java-based Library Management System to manage books, patrons, and the lending process efficiently.

## Requirements
* **Java JDK:** 21 
* **IDE:** IntelliJ IDEA
* **Build Tool:** Maven


## Project Structure
TODO

## Database Details
This project uses an in-memory H2 database for data persistence during development. Below are the configuration settings:
* **spring.datasource.url=** jdbc:h2:mem:LibraryManagementDB;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
* **spring.datasource.driverClassName=** org.h2.Driver
* **spring.datasource.username=** sachin
* **spring.datasource.password=** sachin@123
* **spring.h2.console.enabled=** true
* **spring.jpa.hibernate.ddl-auto=** update
* **spring.h2.console.path=** /h2-console

### Database Summary
* **Database:** In-memory H2 (LibraryManagementDB).
* **Access:** Use http://localhost:8080/h2-console for the web console.
* **Auto-update:** Hibernate manages schema changes automatically.

## System Design Overview

### Classes Overview
* **Book:** Represents a book with attributes like title, author, ISBN, and publication year.
* **Patron:** Represents a library patron with attributes like name, id.
* **LendingService:** Represents a book lending transaction with attributes like borrowing book, returning book.

## OOP Concepts
* **Encapsulation:** Used private fields with public getters/setters.
* **Inheritance:** TODO
* **Polymorphism:** Used interfaces for services.
* **Abstraction:** User interface for services.