package com.example.authserver.social.service.nativecall;

import com.example.authserver.social.dto.CommentAddDto;
import com.example.authserver.social.repository.CommentRepository;
import com.example.authserver.social.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentServiceNativeImpl implements CommentService {

    private  final CommentRepository commentRepository;

    @Override
    public Object addComment(CommentAddDto commentAddDto,Long profileId) {
        return commentRepository.addComment(commentAddDto.getText(), commentAddDto.getPostId(),profileId );
    }
}
