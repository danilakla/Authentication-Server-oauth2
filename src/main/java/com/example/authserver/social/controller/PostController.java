package com.example.authserver.social.controller;

import com.example.authserver.social.dto.PostCreateDto;
import com.example.authserver.social.dto.PostUpdateAccessDto;
import com.example.authserver.social.dto.PostUpdateDescriptionDto;
import com.example.authserver.social.service.PostService;
import com.example.authserver.util.UtilService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin
@AllArgsConstructor
public class PostController {
    private final PostService postService;
    private final UtilService utilService;

    @PostMapping("/createPost")
    public Object createPost(Principal principal, PostCreateDto postCreateDto
    ) {
        Long profileId = utilService.retrieveFromClaimsProfileId(principal);
        return postService.createPost(postCreateDto, profileId);
    }

    @PutMapping("/putReaction")
    public Object putReaction(@RequestParam("postId") Long postId,Principal principal
    ) {

        Long profileId = utilService.retrieveFromClaimsProfileId(principal);
        return postService.putReaction(postId, profileId);
    }


    @DeleteMapping("/deletePost")
    public Object deletePost(@RequestParam("postId") Long postId,Principal principal
                             ) {
        Long profileId = utilService.retrieveFromClaimsProfileId(principal);
        return postService.deletePost(postId, profileId);
    }
    @GetMapping("/getPosts")
    public Object getPosts(Principal principal
    ) {
        Long profileId = utilService.retrieveFromClaimsProfileId(principal);
        return postService.getPostEntityByProfileId( profileId);
    }

    @GetMapping("/getPostsGeneral")
    public Object getPostsGeneral(
    ) {
        var data =postService.getPostEntityByPublic( );
        return  data;
    }



    @PutMapping("/updateDescriptionPost")
    public Object updateDesPost(Principal principal, @RequestParam("postId") Long postId,
                             PostUpdateDescriptionDto postUpdateDescriptionDto
    ) {
        Long profileId = utilService.retrieveFromClaimsProfileId(principal);

        return postService.updateDescriptionPost(postId, postUpdateDescriptionDto, profileId);
    }

    @PutMapping("/updateAccessPost")
    public Object createAccPost(Principal principal, @RequestParam("postId") Long postId,
                             PostUpdateAccessDto postUpdateAccessDto
    ) {
        Long profileId = utilService.retrieveFromClaimsProfileId(principal);

        return postService.updateAccessPost(postId, postUpdateAccessDto, profileId);
    }
}
