package com.example.authserver.authentication.security;

import com.example.authserver.authentication.entity.UserEntity;
import com.example.authserver.authentication.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUsrDetailsService implements UserDetailsService {

    private UserRepository userRepo;

    public CustomUsrDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepo.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User with email = "+email+" not exist!"));
        return new CustomUsrDetails(user);
    }
}