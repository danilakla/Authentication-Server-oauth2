package com.example.authserver.authentication.repository;
import java.util.List;
import java.util.Optional;

import com.example.authserver.authentication.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface TokenRepository extends JpaRepository<TokenEntity, Integer> {

  @Query(value = """
      select t from tokens t inner join users u\s
      on t.user.id = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)
  List<TokenEntity> findAllValidTokenByUser(Integer id);

  Optional<TokenEntity> findByToken(String token);
}
