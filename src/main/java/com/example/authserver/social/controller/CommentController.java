
package com.example.authserver.social.controller;

import com.example.authserver.social.dto.CommentAddDto;
import com.example.authserver.social.service.CommentService;
import com.example.authserver.util.UtilService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@CrossOrigin
@AllArgsConstructor
public class CommentController {
    private  final CommentService commentService;
    private  final UtilService utilService;

    @PostMapping("/addComment")
    public Object createPost(Principal principal, CommentAddDto commentAddDto
    ){
        Long profileId=utilService.retrieveFromClaimsProfileId(principal);

        return  commentService.addComment(commentAddDto,profileId);
    }


}
