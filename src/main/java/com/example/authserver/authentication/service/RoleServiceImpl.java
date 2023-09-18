package com.example.authserver.authentication.service;

import com.example.authserver.authentication.entity.RoleEntity;
import com.example.authserver.authentication.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    public Set<RoleEntity> getAdminRole() {
        RoleEntity role=roleRepository.findByName("admin").get();
        Set<RoleEntity> set = new HashSet<>();
        set.add(role);
        return  set;
    }
    public Set<RoleEntity> getUserRole() {
        RoleEntity role=roleRepository.findByName("user").get();
        Set<RoleEntity> set = new HashSet<>();
        set.add(role);
        return  set;
    }
}
