package com.treinetic.treinetic.data;

import com.treinetic.treinetic.business.DTOs.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class TaskRepository implements ITaskRepository {

    private final MongoTaskRepository mongoTaskRepository;

    @Autowired
    public TaskRepository(MongoTaskRepository mongoTaskRepository) {
        this.mongoTaskRepository = mongoTaskRepository;
    }

    @Override
    public List<Task> getAll() {
        return mongoTaskRepository.findAll();
    }

    @Override
    public Task findById(String id) {
        System.out.println(id);
        return mongoTaskRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Task createTask(Task task) {
        return mongoTaskRepository.save(task);
    }

    @Override
    public Task updateTask(Task updatedTask) {
        return mongoTaskRepository.save(updatedTask);
    }

    @Override
    public Task deleteTask(String id) {
        mongoTaskRepository.deleteById(id);
        return new Task();
    }

}
