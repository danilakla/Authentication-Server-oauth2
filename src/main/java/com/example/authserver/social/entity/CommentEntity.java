package com.example.authserver.social.entity;

import com.example.authserver.domain.entity.ProfileEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "comments")

public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "text")
    private String text;


    @Column(name = "creationDate")
    private Date creationDate;


    @ManyToOne
    public PostEntity post;


    @ManyToOne
    @JoinColumn(name = "profile_id")
    public ProfileEntity profile;


}