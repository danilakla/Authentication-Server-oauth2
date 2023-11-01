package com.example.authserver.social.repository;

import com.example.authserver.social.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {


    @Procedure(name = "post.insertPost" )

    Object createPost(String p_description, Long p_is_public, Long p_qr_id,Long p_profile_id);


    @Procedure(name = "post.deletePost" )

    Object deletePost(Long p_post_id, Long p_profile_id);

    @Procedure(name = "post.updateDescriptionPost" )

    Object updateDescription(Long p_post_id,String p_description, Long p_profile_id);


    @Procedure(name = "post.updateAccessPost" )

    Object updateAccess(Long p_post_id,Long p_is_public, Long p_profile_id);

    PostEntity getPostEntityById(Long id);

    @Procedure(name = "post.putReaction" )

    Object putReaction(Long p_post_id, Long p_profile_id);
    List<PostEntity> getPostEntityByProfileId(Long Id);
    @Query(
            value = "SELECT * FROM POSTS p WHERE p.IS_PUBLIC = ?1",
            nativeQuery = true)
    List<PostEntity> getPostEntityByIsPublic( Long p_is_public);

}
