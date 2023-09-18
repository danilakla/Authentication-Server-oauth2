package com.example.authserver.authentication.service.nativesql;

import com.example.authserver.authentication.entity.RoleEntity;
import com.example.authserver.authentication.repository.RoleRepository;
import com.example.authserver.authentication.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Primary
public class RoleServiceNativeImpl implements RoleService {
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
