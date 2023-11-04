package com.example.authserver.logger.controller;

import com.example.authserver.logger.entity.LoggerEntity;
import com.example.authserver.logger.service.LoggerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin()
@AllArgsConstructor
public class LoggerController {

    private  final LoggerService loggerService;

    @GetMapping("/getStaticsCountQrcodeForDay")
    List<Object> getStaticsCountQrcodeForDay(){


        return loggerService.getStaticsCountQrcodeForDay();
    }

    @GetMapping("/getLoggerEntities")
    List<LoggerEntity> getLoggerEntities(){


        return loggerService.getLoggerEntities();
    }
}
