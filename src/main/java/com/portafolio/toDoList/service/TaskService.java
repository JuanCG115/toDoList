package com.portafolio.toDoList.service;

import com.portafolio.toDoList.Task;
import com.portafolio.toDoList.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // 1. get all tasks
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    // 2. get task by ID
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    // 3. create new task
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // 4. update existing task
    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("The task with ID: " + id + " was not found"));

        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setCompleted(taskDetails.isCompleted());

        return taskRepository.save(task);
    }

    // 5. delete task
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("The task with ID: " + id + " was not found"));
        taskRepository.delete(task);
    }
}
