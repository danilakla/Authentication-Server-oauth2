package com.example.authserver.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "qrcode")
public class QREntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Lob
    private byte[] image;

    @Column(name = "creationDate")
    private Date creationDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    public ProfileEntity profile;
}