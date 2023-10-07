package com.example.authserver.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "contents")
@NamedStoredProcedureQuery(name = "contents.insertContent",procedureName = "INSERT_CONTENT",parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_data",type = byte.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_filename",type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_qrcode_id",type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_extension",type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_filetype_id",type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_success",type = Long.class),
})
@NamedStoredProcedureQuery(name = "contents.deleteContent",procedureName = "DELETE_CONTENTS",parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id",type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_success",type = Long.class),
})
public class ContentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "filename")
    private String fileName;

    @Column(name = "extension")
    private String extension;


    @Lob
    private byte[] data;


    @ManyToMany
    @JoinTable(
            name = "contents_filetypes",
            joinColumns = @JoinColumn(name = "content_id"),
            inverseJoinColumns = @JoinColumn(name = "filetype_id")
    )
    private List<FileTypeEntity> fileTypes;
    @ManyToOne(fetch = FetchType.LAZY)
    private QREntity qrCode;

}