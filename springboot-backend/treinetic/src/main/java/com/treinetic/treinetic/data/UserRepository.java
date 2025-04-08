package com.treinetic.treinetic.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository {

    private final MongoUserRepository mongoUserRepository;

    @Autowired
    public UserRepository(MongoUserRepository mongoUserRepository) {
        this.mongoUserRepository = mongoUserRepository;
    }

    public Boolean findEmail(String email){
        return  mongoUserRepository.existsByEmail(email);
    }

    public User findUserByEmail(String email) {
        return mongoUserRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    @Override
    public User registerUser(User user) {
        if (mongoUserRepository.existsByEmail(user.getEmail())){
            return null;
        }
        return mongoUserRepository.save(user);
    }
}
