package com.example.authserver.social.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PostUpdateDescriptionDto {
    private   String description;

}
