package com.example.authserver.domain.repository;

import com.example.authserver.domain.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {

    @Procedure(name = "profile.insertUser" )
    Object initProfile(String p_about,String p_last_name,String p_name, String p_mail);

    ProfileEntity getProfileEntityByEmail(String email);


}
