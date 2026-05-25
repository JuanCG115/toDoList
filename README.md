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

2.  **Choose Your Execution Method**
    You can run this API using Docker (Recommended) or through a Traditional Local Setup.

    - **Option A: Running with Docker (Zero Configuration)**
        This method isolates the application inside a container. You don't need Java or Maven installed on your machine—only Docker Desktop running.
        1. **Build the Docker Image**
           Compile the application and pack it into a lightweight container image:
           ```bash
           docker build -t todolist-api .
           ```
        2. **Run the Container**
           Start the containerized application in the background, mapping it to port 8080:
           ```bash
           docker run -d -p 8080:8080 --name mi-contenedor-todolist todolist-api
           ```
           The server will be live at http://localhost:8080.
           
    **Managing via Docker Desktop**
    If you prefer a graphical interface, you can manage the container fully within Docker Desktop:
    1. Open the Docker Desktop application.
    2. Go to the Containers tab in the left sidebar.
    3. You will see mi-contenedor-todolist listed with a Green Icon (Running).
    Controls: You can click the Stop (Square) button to pause the API, or the Trash icon to remove it. When you restart your computer, simply click the Start         (Play) button to bring the API back online instantly without typing any commands.

    - **Option B: Traditional Local Setup**
      Use this method if you wish to run or develop the application directly on your host machine.
      1. **Run the application**
         Make sure you have Java 17 configured. Open the terminal in the root folder and execute:
         ```bash
         ./mvnw spring-boot:run
         ```
        (On Windows PowerShell, use .\mvnw.cmd spring-boot:run). The server will start on http://localhost:8080.

        **Accessing the H2 Web Console**
        Whether running via Docker or locally, you can inspect the in-memory database tables visually in your browser:
        - URL: http://localhost:8080/h2-console
        - JDBC URL: jdbc:h2:mem:todolist_db
        - User Name: sa
        - Password: (Leave empty)
     
        ***Note for Docker Users***
        Remote database web connections are safely allowed inside the container via spring.h2.console.settings.web-allow-others=true to let your host browser             seamlessly access the containerized H2 database.

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
