package com.example.authserver.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class QRcodeInsertDto {
    private String description;
    private String name;
    private List<MultipartFile> contents;
}


