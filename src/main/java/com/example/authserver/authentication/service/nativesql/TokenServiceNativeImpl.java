package com.example.authserver.authentication.service.nativesql;

import com.example.authserver.authentication.repository.TokenRepository;
import com.example.authserver.authentication.security.CustomUsrDetails;
import com.example.authserver.authentication.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Primary
public class TokenServiceNativeImpl implements TokenService {
    private final TokenRepository tokenRepository;
    public void revokeAllUserTokens(CustomUsrDetails user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
var result=  tokenRepository.revokeToken(user.getId() );
if((int)result==0){
    System.out.println("test2");
}
        tokenRepository.saveAll(validUserTokens);
    }
      public void saveUserToken(CustomUsrDetails user, String jwtToken) {

          var result=  tokenRepository.ProcTokenInser(user.getId(),jwtToken );
          if((int)result==0){
              System.out.println("test2");
          }//    var token = TokenEntity.builder()
//        .user(user.getUser())
//        .token(jwtToken)
//        .expired(false)
//        .revoked(false)
//        .build();
//    tokenRepository.save(token);
  }



}
