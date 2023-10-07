package com.example.authserver.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "qrcode")
@NamedStoredProcedureQuery(name = "qrcode.insertQR", procedureName = "INSERT_QRCODE", parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_creation_date", type = LocalDateTime.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_description", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_image", type = byte[].class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_name", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_profile_id", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_success", type = Long.class),
})

@NamedStoredProcedureQuery(name = "qrcode.deleteQR", procedureName = "DELETE_QRCODE", parameters = {

        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_success", type = Long.class),
})

@NamedStoredProcedureQuery(name = "qrcode.updateQR", procedureName = "UPDATE_QRCODE", parameters = {

        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_description", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_name", type = String.class),

        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_success", type = Long.class),
})
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

    @OneToMany(mappedBy = "qrCode", cascade = CascadeType.ALL, orphanRemoval = true)

    private List<ContentEntity> contentEntities;
}