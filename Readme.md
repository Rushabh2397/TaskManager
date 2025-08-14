# Task Manager API

A Spring Boot REST API for managing tasks.

## Tech
- Spring Web, Spring Data JPA, Validation
- H2 in-memory database
- JUnit 5, MockMvc

## Run

```bash
 ./gradlew clean bootRun
```

H2 Console: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:testdb`, user `myuser`, password `secret`).

## API

- `POST /api/tasks` – create
- `GET /api/tasks/<taskId>` – get by id


### Sample curl
```bash
curl --location 'http://localhost:5000/api/tasks' \
--header 'Content-Type: application/json' \
--data '{
    "title": "Complete Spring Boot Assignment",
    "description": "Build a task management API",
    "status": "PENDING",
    "priority": "HIGH",
    "dueDate": "2024-02-15"
}'
```


## Notes
- Validation errors return 400 with field messages.
- Not found returns 404.
