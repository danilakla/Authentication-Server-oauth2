//package com.example.authserver.logger.entity;
//
//import com.example.authserver.domain.entity.ProfileEntity;
//import jakarta.persistence.*;
//import lombok.Data;
//
//import java.util.Date;
//
//@Entity
//@Data
//@Table(name = "logger")
//public class LoggerEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//
//    @Column(name = "id")
//    private Long id;
//
//
//    @Column(name = "maxsize")
//    private Long maxSize;
//
//
//    @Column(name = "logTime")
//    private Date logTime;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "profile_id")
//    public ProfileEntity profile;
//}