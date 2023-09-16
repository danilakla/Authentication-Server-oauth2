package com.example.authserver.authentication.service;

import com.example.authserver.authentication.entity.RoleEntity;
import com.example.authserver.authentication.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleEntity getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }
}
