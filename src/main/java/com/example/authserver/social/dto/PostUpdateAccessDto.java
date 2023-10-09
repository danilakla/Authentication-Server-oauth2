package com.example.authserver.social.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PostUpdateAccessDto {
    private   Boolean isPublic;

}
