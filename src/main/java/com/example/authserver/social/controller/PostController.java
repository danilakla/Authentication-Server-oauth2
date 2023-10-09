package com.example.authserver.social.controller;

import com.example.authserver.social.dto.PostCreateDto;
import com.example.authserver.social.dto.PostUpdateAccessDto;
import com.example.authserver.social.dto.PostUpdateDescriptionDto;
import com.example.authserver.social.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@AllArgsConstructor
public class PostController {
    private  final PostService postService;

    @PostMapping("/createPost")
    public Object createPost(PostCreateDto postCreateDto
    ){
        return  postService.createPost(postCreateDto);
    }

    @PostMapping("/putReaction")
    public Object putReaction(@RequestParam("postId") Long postId
    ){
        return  postService.putReaction(postId);
    }


    @DeleteMapping("/deletePost")
    public Object createPost(@RequestParam("postId") Long postId
    ){
        return  postService.deletePost(postId);
    }


    @PutMapping("/updateDescriptionPost")
    public Object createPost(@RequestParam("postId") Long postId,
                             PostUpdateDescriptionDto postUpdateDescriptionDto
    ){
        return  postService.updateDescriptionPost(postId, postUpdateDescriptionDto);
    }

    @PutMapping("/updateAccessPost")
    public Object createPost(@RequestParam("postId") Long postId,
                             PostUpdateAccessDto postUpdateAccessDto
    ){
        return  postService.updateAccessPost(postId, postUpdateAccessDto);
    }
}
