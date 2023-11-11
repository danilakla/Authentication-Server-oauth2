package com.example.authserver.authentication.service.nativesql;

import com.example.authserver.authentication.auth.RegisterRequest;
import com.example.authserver.authentication.entity.UserEntity;
import com.example.authserver.authentication.repository.UserRepository;
import com.example.authserver.authentication.service.RoleService;
import com.example.authserver.authentication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Primary
public class UserServiceNativeImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public UserEntity saveUser(RegisterRequest request){
        var user = UserEntity.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roleService.getAdminRole())
                .build();



       var res=  userRepository.ProcUserInser(request.getEmail(),passwordEncoder.encode(request.getPassword())
         , roleService.getAdminRole().stream().findFirst().get().getId());
if((Integer) res==0){
    throw new HttpClientErrorException(HttpStatusCode.valueOf(401));
}


        return  user;
    }

    public Optional<UserEntity> getUserByEmail(String userEmail){
            var user = userRepository.findByEmail(userEmail);
            return user;
    }

}
