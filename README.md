# Event Board

Java EE web application for managing events and participant registrations.

## Technologies

* Java 17
* JSP
* Servlet API
* JDBC
* MySQL
* Tomcat 10
* Maven
* JUnit 5

## Database Setup

Create database:

```sql
CREATE DATABASE event_board;
USE event_board;
```

Create table events:

```sql
CREATE TABLE events (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    event_date DATE,
    max_seats INT
);
```

Create table participants:

```sql
CREATE TABLE participants (
    id INT PRIMARY KEY AUTO_INCREMENT,
    event_id INT,
    student_name VARCHAR(255),
    student_email VARCHAR(255)
);
```

## Configure Database

Edit DBConnection.java and set:

```java
private static final String URL =
        "jdbc:mysql://localhost:3306/event_board";

private static final String USER = "root";
private static final String PASSWORD = "password";
```

## Running Project

1. Open project in IntelliJ IDEA.
2. Configure Apache Tomcat 10.
3. Run the application.
4. Open:

```
ttp://localhost:8080/event-board/events
```

## Features

* Create events
* View event list
* View event details
* Register participants
* Check free seats
* Prevent registration when event is full
* Delete events

## Tests

JUnit 5 test included for free seats calculation.
