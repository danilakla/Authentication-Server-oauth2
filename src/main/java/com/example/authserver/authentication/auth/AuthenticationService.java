package com.example.authserver.authentication.auth;

import com.example.authserver.authentication.security.CustomUsrDetails;
import com.example.authserver.authentication.security.CustomUsrDetailsService;
import com.example.authserver.authentication.security.JwtService;
import com.example.authserver.authentication.service.TokenService;
import com.example.authserver.authentication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final JwtService jwtService;
  private  final UserService userService;
  private final AuthenticationManager authenticationManager;
  private final CustomUsrDetailsService customUserDetailsService;
  private final TokenService tokenService;


  private boolean isExit(String userEmail){
    return  userService.getUserByEmail(userEmail).isPresent();
  }
  public AuthenticationResponse register(RegisterRequest request) {

    if(isExit(request.getEmail())){
      userService.saveUser(request);
    }else{
      return null;
    }
    return new AuthenticationResponse();
  }
//
  public AuthenticationResponse authenticate(AuthenticationRequest request) {

    UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
    Authentication auth = authenticationManager.authenticate(authenticationToken);

    CustomUsrDetails user = (CustomUsrDetails) customUserDetailsService.loadUserByUsername(request.getEmail());
    String access_token = jwtService.generateAccessToken(user);
    String refresh_token = jwtService.generateRefreshToken(user);
    tokenService.revokeAllUserTokens(user);
    tokenService.saveUserToken(user, access_token);
    return null;

  }



  public Object refreshToken(
          String refreshToken
  ) throws IOException {

    String email = jwtService.parseToken(refreshToken);
    CustomUsrDetails user = (CustomUsrDetails) customUserDetailsService.loadUserByUsername(email);
    String access_token = jwtService.generateAccessToken(user);
    String refresh_token = jwtService.generateRefreshToken(user);

    tokenService.revokeAllUserTokens(user);
    tokenService.saveUserToken(user, access_token);
    return null;

  }
}
