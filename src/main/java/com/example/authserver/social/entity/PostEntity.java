package com.example.authserver.social.entity;

import com.example.authserver.domain.entity.QREntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "posts")
@NamedStoredProcedureQuery(name = "post.insertPost",procedureName = "CREATE_POST",parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_description",type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_is_public",type = Boolean.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_qr_id",type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_success",type = Long.class),
})

@NamedStoredProcedureQuery(name = "post.deletePost",procedureName = "DELETE_POST",parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_post_id",type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_success",type = Long.class),
})

@NamedStoredProcedureQuery(name = "post.updateDescriptionPost",procedureName = "UPDATE_DESCRIPTION",parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_post_id",type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_description",type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_success",type = Long.class),
})
@NamedStoredProcedureQuery(name = "post.updateAccessPost",procedureName = "UPDATE_ACCESS",parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_post_id",type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_is_public",type = Boolean.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_success",type = Long.class),
})
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "description")
    private String description;


    @Column(name = "creationDate")
    private Date creationDate;

    @Column(name = "reactionCount")
    private Long reactionCount;

    @Column(name = "isPublic")
    public boolean isPublic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qr_id")
    public QREntity profile;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<CommentEntity> commentEntities;

}