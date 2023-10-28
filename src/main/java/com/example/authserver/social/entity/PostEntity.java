package com.example.authserver.social.entity;

import com.example.authserver.domain.entity.ProfileEntity;
import com.example.authserver.domain.entity.QREntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")
@NamedStoredProcedureQuery(name = "post.insertPost",procedureName = "CREATE_POST",parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_description",type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_is_public",type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_qr_id",type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_profile_id",type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_success",type = Long.class),
})

@NamedStoredProcedureQuery(name = "post.deletePost",procedureName = "DELETE_POST",parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_post_id",type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_profile_id",type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_success",type = Long.class),
})

@NamedStoredProcedureQuery(name = "post.updateDescriptionPost",procedureName = "UPDATE_DESCRIPTION",parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_post_id",type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_description",type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_profile_id",type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_success",type = Long.class),
})
@NamedStoredProcedureQuery(name = "post.updateAccessPost",procedureName = "UPDATE_ACCESS",parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_post_id",type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_is_public",type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_profile_id",type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_success",type = Long.class),
})

@NamedStoredProcedureQuery(name = "post.putReaction",procedureName = "PUT_REACTION_TO_Post",parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_post_id",type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_profile_id",type = Long.class),
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



    @ManyToMany
    @JoinTable(
            name = "post_profile",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "profile_id")
    )
    private List<ProfileEntity> profiles;

    @Column(name = "isPublic")
    public boolean isPublic;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qr_id")
    public QREntity  qrEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    public ProfileEntity profile;


    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<CommentEntity> commentEntities;

}