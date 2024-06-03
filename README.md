# Employee Rest API CRUD Application Java SpringBoot with PostgreSQL

This is a simple CRUD (Create, Read, Update, Delete)Rest API application for managing employees, built with Spring Boot and PostgreSQL.

## Table of Contents
- [Features](#features)
- [Requirements](#requirements)
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)


## Features
- Create a new employee
- Retrieve a list of employee
- Retrieve a single employee by ID
- Update an existing employee
- Delete an employee


## Requirements
- Java 17 or higher
- Maven 3.6.3 or higher
- PostgreSQL 13 or higher

  
## Installation

1. **Clone the repository:**
   ```bash
   git clone https://your-repository-url.git
   cd employee-crud-application
   ```
2. **Database Configuration:**
Go to into file application.yml and then :
- change url, port, and database name :
>spring.datasource.url=jdbc:postgresql://localhost:5432/your_database

- change username :
>spring.datasource.username=username

- change password :
>spring.datasource.password=password

3. **Build the project:**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## Created by : Ricky Marnaek Sibarani || @marnaeksibarani_

