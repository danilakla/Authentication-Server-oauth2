package com.example.authserver.authentication.security;

import com.example.authserver.authentication.entity.RoleEntity;
import com.example.authserver.authentication.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class CustomUsrDetails implements UserDetails {

    private static final long serialVersionUID = 1L;
    private UserEntity user;

    public CustomUsrDetails(UserEntity user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<RoleEntity> roles = user.getRoles();

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(RoleEntity role : roles) {authorities.add(new SimpleGrantedAuthority(role.getName()));}
        return authorities;
    }

    @Override
    public String getPassword() {return user.getPassword();}

    @Override
    public String getUsername() {return user.getEmail();}

    @Override
    public boolean isAccountNonExpired() {return true;}

    @Override
    public boolean isAccountNonLocked() {return true;}

    @Override
    public boolean isCredentialsNonExpired() {return true;}

    @Override
    public boolean isEnabled() {return true;}
}