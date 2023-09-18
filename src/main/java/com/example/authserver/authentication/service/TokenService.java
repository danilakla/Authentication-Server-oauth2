package com.example.authserver.authentication.service;

import com.example.authserver.authentication.security.CustomUsrDetails;

public interface TokenService {

     void revokeAllUserTokens(CustomUsrDetails user) ;
     void saveUserToken(CustomUsrDetails user, String jwtToken);
}
