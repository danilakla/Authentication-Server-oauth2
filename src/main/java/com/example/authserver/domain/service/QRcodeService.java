package com.example.authserver.domain.service;

import com.example.authserver.domain.dto.QRcodeInsertDto;
import com.example.authserver.domain.dto.QRcodeUpdateDto;
import com.example.authserver.domain.entity.QREntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface QRcodeService {

    Object saveQRcode(QRcodeInsertDto qRcodeInsertDto, Long id) throws Exception;
    Object deleteQRcodeById(Long id) throws Exception;
    Object updateQrCode(QRcodeUpdateDto qRcodeUpdateDto) throws Exception;
    Object addListContent(List<MultipartFile> contentInsertDtos, Long qrId) throws Exception;

    List<QREntity> getQrCodesById(Long id);

}
