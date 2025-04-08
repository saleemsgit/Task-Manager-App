package com.treinetic.treinetic.data;

import com.treinetic.treinetic.business.DTOs.RegisterRequest;

public interface IUserRepository {
    User registerUser(User user);
}
