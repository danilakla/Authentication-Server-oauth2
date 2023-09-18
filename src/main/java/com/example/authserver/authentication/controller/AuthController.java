package com.example.authserver.authentication.controller;

import com.example.authserver.authentication.auth.AuthenticationRequest;
import com.example.authserver.authentication.auth.AuthenticationService;
import com.example.authserver.authentication.auth.RegisterRequest;
import com.example.authserver.authentication.security.CustomUsrDetailsService;
import com.example.authserver.authentication.security.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final CustomUsrDetailsService usrDetailsService;
    private final AuthenticationService authenticationService;

    @GetMapping("/testUser")
    public   String sdasds(){
        return "user test";
    }
    @GetMapping("/testAdmin")
    public   String sdascxz(){
        return "admint test";
    }

    @GetMapping("/test")
    public   String sdafscxz(){
        return "plain us test";
    }


    @PostMapping("/register")
    public ResponseEntity<Object> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    record RefreshTokenResponse(String access_jwt_token, String refresh_jwt_token) {};
    @GetMapping("/token/refresh")
    public ResponseEntity<Object> refreshToken(HttpServletRequest request) throws IOException {
        final String headerAuth = request.getHeader(HttpHeaders.AUTHORIZATION);

        String refreshToken = headerAuth.substring(7, headerAuth.length());

        if (refreshToken == null || !refreshToken.startsWith("Bearer ")) {
            return null;
        }
        var obj = authenticationService.refreshToken(refreshToken);
        return ResponseEntity.ok(obj);
    }
}