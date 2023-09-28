package com.example.authserver.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class QRcodeUpdateDto {
    private Long id;
    private String description;
    private String name;
}


