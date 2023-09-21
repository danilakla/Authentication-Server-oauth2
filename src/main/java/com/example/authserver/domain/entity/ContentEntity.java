package com.example.authserver.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "contents")
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
    @JoinColumn(name = "qrcode_id")
    public QREntity qrCode;
}