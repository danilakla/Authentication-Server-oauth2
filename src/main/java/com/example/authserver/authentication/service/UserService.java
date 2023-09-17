package com.example.authserver.authentication.service;

import com.example.authserver.authentication.auth.RegisterRequest;
import com.example.authserver.authentication.entity.UserEntity;
import com.example.authserver.authentication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public UserEntity saveUser(RegisterRequest request){
        var user = UserEntity.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roleService.getUserRole())
                .build();
        var savedUser = userRepository.save(user);
        return  savedUser;
    }

    public Optional<UserEntity> getUserByEmail(String userEmail){
            var user = userRepository.findByEmail(userEmail);
            return user;
    }

}
