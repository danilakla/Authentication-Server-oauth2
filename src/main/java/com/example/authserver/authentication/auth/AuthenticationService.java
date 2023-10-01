package com.example.authserver.authentication.auth;

import com.example.authserver.authentication.security.CustomUsrDetails;
import com.example.authserver.authentication.security.CustomUsrDetailsService;
import com.example.authserver.authentication.security.JwtService;
import com.example.authserver.authentication.service.TokenService;
import com.example.authserver.authentication.service.UserService;
import com.example.authserver.domain.dto.ProfileInitDto;
import com.example.authserver.domain.service.ProfileService;
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


    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final CustomUsrDetailsService customUserDetailsService;
    private final ProfileService profileService;

    private final TokenService tokenService;


    private boolean isExit(String userEmail) {
        return userService.getUserByEmail(userEmail).isPresent();
    }

    public AuthenticationResponse register(RegisterRequest request) {

        if (!isExit(request.getEmail())) {
            userService.saveUser(request);
            var profile = new ProfileInitDto();
            profile.setEmail(request.getEmail());
            profileService.saveProfile(profile);
        } else {
            return null;
        }
        return new AuthenticationResponse();
    }

    //
    public Object authenticate(AuthenticationRequest request) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        Authentication auth = authenticationManager.authenticate(authenticationToken);

        CustomUsrDetails user = (CustomUsrDetails) customUserDetailsService.loadUserByUsername(request.getEmail());
        var profile = new ProfileInitDto();
        var profileId = (Long) profileService.getProfileIdByEmail(user.getUsername());
        profile.setEmail(user.getUsername());
        String access_token = jwtService.generateAccessToken(user, profileId);
        String refresh_token = jwtService.generateRefreshToken(user, profileId);
        tokenService.revokeAllUserTokens(user);
        tokenService.saveUserToken(user, access_token);
        return new Object() {
            public final String acc = access_token;
            public final String ref = refresh_token;
        };

    }


    public Object refreshToken(
            String refreshToken
    ) throws IOException {

        String email = jwtService.parseToken(refreshToken);
        CustomUsrDetails user = (CustomUsrDetails) customUserDetailsService.loadUserByUsername(email);
        var profileId = profileService.getProfileIdByEmail(user.getUsername());
        String access_token = jwtService.generateAccessToken(user, (Long) profileId);
        String refresh_token = jwtService.generateRefreshToken(user, (Long) profileId);

        tokenService.revokeAllUserTokens(user);
        tokenService.saveUserToken(user, access_token);
        return new Object() {
            public final String acc = access_token;
            public final String ref = refresh_token;
        };

    }
}
