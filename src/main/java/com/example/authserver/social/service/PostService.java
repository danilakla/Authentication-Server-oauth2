package com.example.authserver.social.service;

import com.example.authserver.social.dto.PostCreateDto;
import com.example.authserver.social.dto.PostUpdateAccessDto;
import com.example.authserver.social.dto.PostUpdateDescriptionDto;
import com.example.authserver.social.entity.PostEntity;

import java.util.List;

public interface PostService {
    Object createPost(PostCreateDto createDto, Long profileId);
    Object deletePost(Long id, Long profileId);
    Object updateDescriptionPost(Long postId, PostUpdateDescriptionDto postUpdateDescriptionDto, Long profileId);

    Object updateAccessPost(Long postId,PostUpdateAccessDto postUpdateAccessDto, Long profileId);
    Object putReaction(Long postId, Long profileId);

    List<PostEntity> getPostEntityByProfileId(Long Id);
    PostEntity getPostEntityById(Long id);

    List<PostEntity> getPostEntityByPublic();


}
