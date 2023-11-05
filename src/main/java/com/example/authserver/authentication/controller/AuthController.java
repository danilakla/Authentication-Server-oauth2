package com.example.authserver.authentication.controller;

import com.example.authserver.authentication.auth.AuthenticationRequest;
import com.example.authserver.authentication.auth.AuthenticationService;
import com.example.authserver.authentication.auth.RegisterRequest;
import com.example.authserver.authentication.security.CustomUsrDetailsService;
import com.example.authserver.authentication.security.JwtService;
import com.example.authserver.util.UtilService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final CustomUsrDetailsService usrDetailsService;
    private final AuthenticationService authenticationService;
    private final UtilService utilService;

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
        return "plai645n us test";
    }
    @GetMapping("/test2")
    public   String sdvcafscxz(){
        return "plain 321us test";
    }


    @PostMapping("/register")
    public ResponseEntity<Object> register(
            @RequestBody RegisterRequest request
    ) {
        try{
            return ResponseEntity.ok(authenticationService.register(request));

        }catch (Exception e){
        return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticate(
            HttpServletResponse response,
            @RequestBody AuthenticationRequest request
    ) {

        try{

            var tokens  = authenticationService.authenticate(request);
            utilService.setRefreshTokenCookie(tokens.getAcc(), response);
            return ResponseEntity.ok(tokens);
        }
        catch (Exception e){
            return  ResponseEntity.badRequest().build();
        }
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