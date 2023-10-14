package com.example.authserver.logger.service;

import com.example.authserver.logger.repository.LoggerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoggerService {
private  final LoggerRepository loggerRepository;

public void addLog(Long profile_id, Long maxSize){
        loggerRepository.addLog(profile_id,maxSize);


}
}
