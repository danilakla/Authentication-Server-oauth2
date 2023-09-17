package com.example.authserver.authentication.controller;

import com.example.authserver.authentication.security.CustomUsrDetails;
import com.example.authserver.authentication.security.CustomUsrDetailsService;
import com.example.authserver.authentication.security.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final CustomUsrDetailsService usrDetailsService;



    @GetMapping("/test")
    public   String sdas(){
        return "cxzcxzcxzc";
    }



    record LoginRequest(String username, String password) {};
    record LoginResponse(String message, String access_jwt_token, String refresh_jwt_token) {};
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.username, request.password);
        Authentication auth = authManager.authenticate(authenticationToken);

        CustomUsrDetails user = (CustomUsrDetails) usrDetailsService.loadUserByUsername(request.username);
        String access_token = jwtService.generateAccessToken(user);
        String refresh_token = jwtService.generateRefreshToken(user);

        return new LoginResponse("User with email = "+ request.username + " successfully logined!"

                , access_token, refresh_token);
    }



    record RefreshTokenResponse(String access_jwt_token, String refresh_jwt_token) {};
    @GetMapping("/token/refresh")
    public RefreshTokenResponse refreshToken(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        String refreshToken = headerAuth.substring(7, headerAuth.length());
//    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

//    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
//      return;
//    }

        String email = jwtService.parseToken(refreshToken);
        CustomUsrDetails user = (CustomUsrDetails) usrDetailsService.loadUserByUsername(email);
        String access_token = jwtService.generateAccessToken(user);
        String refresh_token = jwtService.generateRefreshToken(user);

        return new RefreshTokenResponse(access_token, refresh_token);
    }
}