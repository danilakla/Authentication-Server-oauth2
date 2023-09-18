package com.example.authserver.authentication.service;

import com.example.authserver.authentication.entity.TokenEntity;
import com.example.authserver.authentication.repository.TokenRepository;
import com.example.authserver.authentication.security.CustomUsrDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final TokenRepository tokenRepository;
    public void revokeAllUserTokens(CustomUsrDetails user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
      public void saveUserToken(CustomUsrDetails user, String jwtToken) {
    var token = TokenEntity.builder()
        .user(user.getUser())
        .token(jwtToken)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }



}
