package com.example.authserver.social.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CommentAddDto {
    private   String text;
    private   Long postId;

}
