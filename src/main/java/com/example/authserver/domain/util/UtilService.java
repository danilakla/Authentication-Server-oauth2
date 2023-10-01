package com.example.authserver.domain.util;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component

public class UtilService {


    public Long retrieveFromClaimsProfileId(Principal principal){
        var credentials =((JwtAuthenticationToken)principal).getCredentials();
        var profileId = ((Jwt)credentials).getClaims().get("profile_id");
        return  (Long) profileId;
    }
}
