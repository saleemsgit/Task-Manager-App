package com.treinetic.treinetic.business;

import com.treinetic.treinetic.business.DTOs.TaskDTO;
import com.treinetic.treinetic.data.Task;
import com.treinetic.treinetic.data.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService implements ITaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.getAll();
    }

    @Override
    public Task getTaskById(String id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task createTask(Task task) {
        task.setCreatedAt(LocalDateTime.now());
        return taskRepository.createTask(task);
    }

    @Override
    public Task updateById(String id, Task updateTask) {
        Task existingTask = taskRepository.findById(id);
        if(existingTask==null){
            return null;
        }
        if (updateTask.getTitle() != null && !updateTask.getTitle().isEmpty()) {
            existingTask.setTitle(updateTask.getTitle());
        }

        if (updateTask.getDescription() != null && !updateTask.getDescription().isEmpty()) {
            existingTask.setDescription(updateTask.getDescription());
        }

        if (updateTask.getStatus() != null && !updateTask.getStatus().isEmpty()) {
            existingTask.setStatus(updateTask.getStatus());
        }

        return taskRepository.updateTask(existingTask);
    }

    @Override
    public Task deleteTaskById(String id) {
        Task existingTask = taskRepository.findById(id);
        if(existingTask==null){
            return null;
        }

        return taskRepository.deleteTask(id);
    }
}
