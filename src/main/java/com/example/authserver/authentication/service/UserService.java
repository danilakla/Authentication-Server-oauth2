package com.example.authserver.authentication.service;

import com.example.authserver.authentication.auth.RegisterRequest;
import com.example.authserver.authentication.entity.UserEntity;

import java.util.Optional;


public interface UserService {

     UserEntity saveUser(RegisterRequest request);

     Optional<UserEntity> getUserByEmail(String userEmail);
}
