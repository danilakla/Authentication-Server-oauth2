package com.example.authserver.domain.service;

import com.example.authserver.domain.dto.ContentInsertDto;
import com.example.authserver.domain.entity.ContentEntity;

import java.util.List;

public interface  ContentService {

    Object saveContent(ContentInsertDto contentInsertDto) throws Exception;
    List<ContentEntity> getContentsById(Long id);

    void hasUserContent(Long profileId, Long profileIdd) throws Exception;
    Object deleteContentById(Long id) throws Exception;
    ContentEntity getContentById(Long id);

}
