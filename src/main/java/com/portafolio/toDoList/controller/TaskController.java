package com.portafolio.toDoList.controller;

import jakarta.validation.Valid;
import com.portafolio.toDoList.Task;
import com.portafolio.toDoList.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // 1. get all task -> GET http://localhost:8080/api/tasks
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTask();
    }

    // 2. get one task by id -> GET http://localhost:8080/api/tasks/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id)
                .orElseThrow(() -> new RuntimeException("The task with the ID: " + id + " was not found"));
        return ResponseEntity.ok(task);
    }

    // 3. create new task -> POST http://localhost:8080/api/tasks
    @PostMapping
    public Task creatTask(@Valid @RequestBody Task task) {
        return taskService.createTask(task);
    }

    // 4. update task -> PUT http://localhost:8080/api/tasks/{id}
    @PutMapping("/{id}")
    public  ResponseEntity<Task> updateTask(@PathVariable Long id, @Valid @RequestBody Task taskDetails) {
        try{
            Task updateTask = taskService.updateTask(id, taskDetails);
            return ResponseEntity.ok(updateTask);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 5. delete task -> DELETE http://localhost:8080/api/tasks/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        try {
            taskService.deleteTask(id);
            return ResponseEntity.noContent().build(); // status 204
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
