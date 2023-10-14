package com.example.authserver.logger.repository;

import com.example.authserver.logger.entity.LoggerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggerRepository extends JpaRepository<LoggerEntity, Long> {
    @Procedure(name = "logger.addLog" )
    Object addLog( Long p_profile_id,Long p_max_size);
}
