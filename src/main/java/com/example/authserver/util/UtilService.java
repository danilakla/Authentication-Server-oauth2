package com.example.authserver.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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

    public void setRefreshTokenCookie(String refreshToken, HttpServletResponse response) {
        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(30 * 24 * 60 * 60); // Set the cookie expiration time (30 days in this example)
        cookie.setPath("/"); // Set the cookie path to be accessible across the entire application
        response.addCookie(cookie);
    }
}
