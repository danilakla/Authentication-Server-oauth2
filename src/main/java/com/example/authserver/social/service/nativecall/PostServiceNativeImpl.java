package com.example.authserver.social.service.nativecall;

import com.example.authserver.social.dto.PostCreateDto;
import com.example.authserver.social.dto.PostUpdateAccessDto;
import com.example.authserver.social.dto.PostUpdateDescriptionDto;
import com.example.authserver.social.repository.PostRepository;
import com.example.authserver.social.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostServiceNativeImpl  implements PostService {

    private  final PostRepository postRepository;

    @Override
    public Object createPost(PostCreateDto createDto, Long profileId) {
        return postRepository.createPost(createDto.getDescription(),createDto.getIsPublic()==true?1l:0l, createDto.getQrId(), profileId);
    }

    @Override
    public Object deletePost(Long id, Long profileId) {
        return postRepository.deletePost(id,  profileId);
    }

    @Override
    public Object updateDescriptionPost(Long postId,PostUpdateDescriptionDto postUpdateDescriptionDto, Long profileId) {
        return postRepository.updateDescription(postId,postUpdateDescriptionDto.getDescription(), profileId);
    }

    @Override
    public Object updateAccessPost(Long postId, PostUpdateAccessDto postUpdateAccessDto, Long profileId) {
        return postRepository.updateAccess(postId,postUpdateAccessDto.getIsPublic()==true?1l:0l, profileId
        );
    }

    @Override
    public Object putReaction(Long postId, Long profileId) {

        return postRepository.putReaction(postId,profileId);
    }


}
