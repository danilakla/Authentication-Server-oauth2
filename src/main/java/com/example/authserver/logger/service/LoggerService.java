package com.example.authserver.logger.service;

import com.example.authserver.logger.entity.LoggerEntity;
import com.example.authserver.logger.repository.LoggerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LoggerService {
private  final LoggerRepository loggerRepository;

public void addLog(Long profile_id, Long maxSize){
        loggerRepository.addLog(profile_id,maxSize);


}
   public      List<LoggerEntity> getLoggerEntities(){
        return  loggerRepository.getLoggerEntities();
        }

public List<Object> getStaticsCountQrcodeForDay(){
        return  loggerRepository.getStaticsCountQrcodeForDay();
}

}
