package com.example.authserver.authentication.repository;

import com.example.authserver.authentication.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    @Procedure(name = "pr_inser_user")
    Object pr_inser_user(@Param("p_email") String email, @Param("p_password") String password,
                    @Param("p_roleid") Long roleId);

    //user.insetUser
    @Procedure(name = "user.insetUser" )
    Object ProcUserInser(String p_email, String p_password, Long p_roleid);

}
