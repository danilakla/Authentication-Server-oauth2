package com.example.authserver.social.service;

import com.example.authserver.social.dto.PostCreateDto;
import com.example.authserver.social.dto.PostUpdateAccessDto;
import com.example.authserver.social.dto.PostUpdateDescriptionDto;

public interface PostService {
    Object createPost(PostCreateDto createDto);
    Object deletePost(Long id);
    Object updateDescriptionPost(Long postId, PostUpdateDescriptionDto postUpdateDescriptionDto);

    Object updateAccessPost(Long postId,PostUpdateAccessDto postUpdateAccessDto);
    Object putReaction(Long postId);

}
