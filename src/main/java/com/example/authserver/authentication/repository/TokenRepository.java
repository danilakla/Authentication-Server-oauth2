package com.example.authserver.authentication.repository;

import com.example.authserver.authentication.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Long> {

  @Modifying
  @Query(value = """
          select tokens.* from tokens inner join users \s
          on users.id = tokens.user_id \s
          where users.id = ?1 and (tokens.expired = 0 or tokens.revoked = 0)\s
      """ ,nativeQuery = true)
  List<TokenEntity> findAllValidTokenByUser(Long id);

  Optional<TokenEntity> findByToken(String token);


  @Procedure(name = "token.addToken" )
  Object ProcTokenInser( Long p_userid,String p_token);
  @Procedure(name = "token.revokeToken" )
  Object revokeToken( Long p_user_id);

}
