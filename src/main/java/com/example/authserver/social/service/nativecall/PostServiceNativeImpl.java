package com.example.authserver.social.service.nativecall;

import com.example.authserver.social.dto.PostCreateDto;
import com.example.authserver.social.dto.PostUpdateAccessDto;
import com.example.authserver.social.dto.PostUpdateDescriptionDto;
import com.example.authserver.social.entity.PostEntity;
import com.example.authserver.social.repository.PostRepository;
import com.example.authserver.social.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostServiceNativeImpl  implements PostService {

    private  final PostRepository postRepository;

    @Override
    public Object createPost(PostCreateDto createDto) {
        return postRepository.createPost(createDto.getDescription(),createDto.getIsPublic(), createDto.getQrId());
    }

    @Override
    public Object deletePost(Long id) {
        return postRepository.deletePost(id);
    }

    @Override
    public Object updateDescriptionPost(Long postId,PostUpdateDescriptionDto postUpdateDescriptionDto) {
        return postRepository.updateDescription(postId,postUpdateDescriptionDto.getDescription());
    }

    @Override
    public Object updateAccessPost(Long postId, PostUpdateAccessDto postUpdateAccessDto) {
        return postRepository.updateAccess(postId, postUpdateAccessDto.getIsPublic()
        );
    }

    @Override
    public Object putReaction(Long postId) {
        PostEntity post = postRepository.getPostEntityById(postId);
        Long currentCountOfReaction= post.getReactionCount();
        post.setReactionCount(currentCountOfReaction+1);
        postRepository.save(post);
        return post;
    }


}
