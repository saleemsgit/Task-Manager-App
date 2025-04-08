package com.treinetic.treinetic.data;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MongoUserRepository extends MongoRepository<User , String> {
    Optional<User> findByEmail(String email);
    Optional<User> findByfullName(String fullName);
    Boolean existsByfullName(String fullName);
    Boolean existsByEmail(String email);
}
