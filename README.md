# Task Management System

A simple REST API for managing tasks and users, built with Spring Boot.

## Features

- User registration and login (JWT authentication)
- Create, read, update, delete tasks
- Assign tasks to users
- Filter tasks by status or by user
- Input validation and centralized error handling

## Tech Stack

Java, Spring Boot, Spring Security, Spring Data JPA, H2 Database, JWT, JUnit/Mockito

## How to Run

```bash
git clone https://github.com/ahmedsaber16/task-management-system.git
cd task-management-system
./mvnw spring-boot:run
```

App runs on `http://localhost:8080`.

## Main Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /users/create | Register a new user |
| POST | /users/login | Login, returns a token |
| GET | /api/tasks/allTasks | Get all tasks |
| POST | /api/tasks/create | Create a task |
| PUT | /api/tasks/update/{id} | Update a task |
| DELETE | /api/tasks/delete/{id} | Delete a task |
| PUT | /api/tasks/assign/{taskId}/{userId} | Assign task to user |

All endpoints except register/login require a JWT token:

```
Authorization: Bearer <token>
```
