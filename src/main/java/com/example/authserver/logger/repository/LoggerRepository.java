package com.example.authserver.logger.repository;

import com.example.authserver.logger.entity.LoggerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoggerRepository extends JpaRepository<LoggerEntity, Long> {
    @Procedure(name = "logger.addLog" )
    Object addLog( Long p_profile_id,Long p_max_size);

    @Query(value = """
select * FROM LOGGER
      """ ,nativeQuery = true)
    List<LoggerEntity> getLoggerEntities();
    @Query(value = """
select  TO_CHAR(LOGTIME, 'YYYY-MM-DD') AS formatted_date, MAX (QRCOUNT) FROM LOGGER
GROUP BY TO_CHAR(LOGTIME, 'YYYY-MM-DD')
ORDER BY formatted_date asc 
      """ ,nativeQuery = true)
    List<Object> getStaticsCountQrcodeForDay();
}
