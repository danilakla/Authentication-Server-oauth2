package com.example.authserver.domain.service;

import com.example.authserver.domain.dto.QRcodeInsertDto;
import com.example.authserver.domain.dto.QRcodeUpdateDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface QRcodeService {

    Object saveQRcode(QRcodeInsertDto qRcodeInsertDto, Long id);
    Object deleteQRcodeById(Long id);
    Object updateQrCode(QRcodeUpdateDto qRcodeUpdateDto);
    Object addListContent(List<MultipartFile> contentInsertDtos, Long qrId);

}
