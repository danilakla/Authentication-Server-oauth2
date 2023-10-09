package com.example.authserver.social.repository;

import com.example.authserver.social.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {


    @Procedure(name = "post.insertPost" )

    Object addComment(String p_text, Long p_post_id, Long p_profile_id);


}
