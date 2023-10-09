package com.example.authserver.social.service;

import com.example.authserver.social.dto.CommentAddDto;

public interface CommentService {
    Object addComment(CommentAddDto commentAddDto, Long profileId);
}
