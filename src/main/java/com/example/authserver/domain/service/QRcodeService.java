package com.example.authserver.domain.service;

import com.example.authserver.domain.dto.QRcodeInsertDto;
import com.example.authserver.domain.dto.QRcodeUpdateDto;
import com.example.authserver.domain.entity.QREntity;
import com.google.zxing.WriterException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface QRcodeService {

    Object saveQRcode(QRcodeInsertDto qRcodeInsertDto, Long id) throws IOException, WriterException;
    Object deleteQRcodeById(Long id);
    Object updateQrCode(QRcodeUpdateDto qRcodeUpdateDto);
    Object addListContent(List<MultipartFile> contentInsertDtos, Long qrId);

    List<QREntity> getQrCodesById(Long id);

}
