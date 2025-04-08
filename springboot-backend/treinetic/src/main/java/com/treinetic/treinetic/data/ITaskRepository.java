package com.treinetic.treinetic.data;

import com.treinetic.treinetic.business.DTOs.TaskDTO;

import java.util.List;

public interface ITaskRepository {
    List<Task> getAll();
    Task findById(String id);
    Task createTask(Task task);
    Task updateTask(Task task);
    Task deleteTask(String id);
}
