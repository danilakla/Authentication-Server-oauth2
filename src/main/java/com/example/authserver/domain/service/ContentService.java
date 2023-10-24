package com.example.authserver.domain.service;

import com.example.authserver.domain.dto.ContentInsertDto;
import com.example.authserver.domain.entity.ContentEntity;

import java.util.List;

public interface  ContentService {

    Object saveContent(ContentInsertDto contentInsertDto);
    List<ContentEntity> getContentsById(Long id);
    Object deleteContentById(Long id);
    ContentEntity getContentById(Long id);

}
