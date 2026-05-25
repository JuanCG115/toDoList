# To-Do List API (Spring Boot REST Backend)

A robust, production-ready RESTful API for managing tasks in a To-Do List application. This backend is built using Java and Spring Boot, implementing a clean layered architecture, automatic database schema generation, strict data validation, and a centralized global exception handling mechanism.

## Features

* **Layered Architecture:** Clear separation of concerns (Controller, Service, Repository, and Model layers).
* **In-Memory Database:** Powered by H2 Database for seamless development and quick zero-configuration setup.
* **Data Validation:** Protects data integrity by rejecting invalid payloads before processing.
* **Global Exception Handling:** Intercepts runtime errors and structural validation issues to return clean, predictable JSON responses.

---

## Tech Stack

* **Java 17+**
* **Spring Boot** (Web, Data JPA, Validation)
* **Hibernate** (ORM)
* **H2 Database** (In-memory)
* **Maven** (Dependency Management)

---

## Setup and Installation

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/JuanCG115/toDoList.git](https://github.com/JuanCG/toDoList.git)
    cd toDoList
    ```

2.  **Configure Database (Optional):**
    The application comes pre-configured with an H2 in-memory database. Configuration details can be verified in `src/main/resources/application.properties`.

3.  **Run the application:**
    Open the project in your favorite IDE (IntelliJ IDEA, VS Code, or Eclipse) and run the `ToDoListApplication.java` class, or use the terminal:
    ```bash
    mvn spring-boot:run
    ```
    The server will start on `http://localhost:8080`.

4.  **Access H2 Web Console:**
    To inspect the database tables visually, navigate to:
    * **URL:** `http://localhost:8080/h2-console`
    * **JDBC URL:** `jdbc:h2:mem:todolist_db`
    * **User Name:** `sa`
    * **Password:** *(Leave empty)*

---

## API Endpoints Summary

| Method | Endpoint | Description | Payload (JSON) | Success Status |
| :--- | :--- | :--- | :--- | :--- |
| **POST** | `/api/tasks` | Create a new task | Title, Description, Completed | `200 OK` |
| **GET** | `/api/tasks` | Get all tasks | None | `200 OK` |
| **GET** | `/api/tasks/{id}` | Get a specific task by ID | None | `200 OK` |
| **PUT** | `/api/tasks/{id}` | Update an existing task | Title, Description, Completed | `200 OK` |
| **DELETE** | `/api/tasks/{id}` | Delete a task by ID | None | `204 No Content` |

---

## Testing the API

You can test all endpoints using either **Postman** or your system **Terminal (`curl`)**.

### 1. Create a Task (POST)
* **URL:** `http://localhost:8080/api/tasks`
* **Postman:** Set method to `POST`. Go to the **Body** tab, select **raw**, choose **JSON**, and paste the payload.
* **Terminal (`curl`):**
    ```bash
    curl -X POST http://localhost:8080/api/tasks \
         -H "Content-Type: application/json" \
         -d '{"title":"Learn Spring Boot","description":"Build a clean layered backend architecture","completed":false}'
    ```

### 2. Get All Tasks (GET)
* **URL:** `http://localhost:8080/api/tasks`
* **Postman:** Set method to `GET` and click **Send**.
* **Terminal (`curl`):**
    ```bash
    curl http://localhost:8080/api/tasks
    ```

### 3. Get Task by ID (GET)
* **URL:** `http://localhost:8080/api/tasks/1`
* **Postman:** Set method to `GET` with the specific ID in the URL.
* **Terminal (`curl`):**
    ```bash
    curl http://localhost:8080/api/tasks/1
    ```

### 4. Update a Task (PUT)
* **URL:** `http://localhost:8080/api/tasks/1`
* **Postman:** Set method to `PUT`. Provide the updated fields in the **JSON Body**.
* **Terminal (`curl`):**
    ```bash
    curl -X PUT http://localhost:8080/api/tasks/1 \
         -H "Content-Type: application/json" \
         -d '{"title":"Learn Spring Boot","description":"Completed with custom exceptions!","completed":true}'
    ```

### 5. Delete a Task (DELETE)
* **URL:** `http://localhost:8080/api/tasks/1`
* **Postman:** Set method to `DELETE`. (Returns an empty body with status `204 No Content`).
* **Terminal (`curl`):**
    ```bash
    curl -X DELETE http://localhost:8080/api/tasks/1
    ```

---

## Validation & Exception Handling Responses

The application intercepts data issues globally to maintain a uniform response interface. 

### Triggering Data Validation Error (400 Bad Request)
If you send a request missing a title or with blank spaces:
```json
{
    "title": "   ",
    "description": "Testing validation rules",
    "completed": false
}
```

API Response Output:
```json
{
    "timestamp": "2026-05-25T10:45:00",
    "status": 400,
    "message": "El título no puede estar vacío"
}
```

### Triggering Resource Not Found Error (404 Not Found)
If you search for, update, or delete a task ID that does not exist (/api/tasks/99)

API Response Output:
```json
{
    "timestamp": "2026-05-25T10:45:30",
    "status": 404,
    "message": "Tarea no encontrada con el id: 99"
}
```
