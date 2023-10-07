package com.example.authserver.domain.repository;

import com.example.authserver.domain.entity.ContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<ContentEntity, Long> {


    @Procedure(name = "contents.insertContent" )
    Object addContent(byte[] p_data, String p_filename, Long p_qrcode_id, String p_extension,
                       Long p_filetype_id );


    @Procedure(name = "contents.deleteContent" )
    Object deleteContent( Long p_id);

    Object getContentEntityById( Long p_id);

}
