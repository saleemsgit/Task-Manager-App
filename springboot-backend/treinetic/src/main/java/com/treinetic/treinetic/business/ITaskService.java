package com.treinetic.treinetic.business;

import com.treinetic.treinetic.business.DTOs.TaskDTO;
import com.treinetic.treinetic.data.Task;

import java.util.List;

public interface ITaskService {
    List<Task> getAllTasks();
    Task getTaskById(String id);
    Task createTask(Task task);
    Task updateById(String id,Task updateTask);
    Task deleteTaskById(String id);
}
