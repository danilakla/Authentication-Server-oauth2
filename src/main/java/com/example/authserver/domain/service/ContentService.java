package com.example.authserver.domain.service;

import com.example.authserver.domain.dto.ContentInsertDto;

public interface ContentService {

    Object saveContent(ContentInsertDto contentInsertDto);
    Object deleteContentById(Long id);

}
