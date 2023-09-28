package com.example.authserver.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentInsertDto{
    private String filename;
    private byte[] data;
    private String extension;

    private  Long qrId;

}