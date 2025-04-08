package com.treinetic.treinetic.data;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoTaskRepository extends MongoRepository<Task,  String> {
}