package com.example.authserver.social.entity;

import com.example.authserver.domain.entity.ProfileEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "comments")
@NamedStoredProcedureQuery(name = "comments.addComment",procedureName = "ADD_COMMENT",parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_text",type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_post_id",type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_profile_id",type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_success",type = Long.class),
})

public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "text")
    private String text;


    @Column(name = "creationDate")
    private Date creationDate;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    public PostEntity post;


    @ManyToOne
    @JoinColumn(name = "profile_id")
    public ProfileEntity profile;


}