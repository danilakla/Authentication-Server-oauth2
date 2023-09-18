package com.example.authserver.authentication.service;

import com.example.authserver.authentication.entity.RoleEntity;

import java.util.Set;

public interface RoleService {

     Set<RoleEntity> getAdminRole();
        Set<RoleEntity> getUserRole();
}
