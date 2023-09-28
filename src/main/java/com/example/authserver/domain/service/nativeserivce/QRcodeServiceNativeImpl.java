package com.example.authserver.domain.service.nativeserivce;

import com.example.authserver.domain.dto.QRcodeInsertDto;
import com.example.authserver.domain.dto.QRcodeUpdateDto;
import com.example.authserver.domain.entity.ContentEntity;
import com.example.authserver.domain.entity.QREntity;
import com.example.authserver.domain.repository.QRRepository;
import com.example.authserver.domain.service.ContentService;
import com.example.authserver.domain.service.QRcodeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class QRcodeServiceNativeImpl implements QRcodeService {

    public final QRRepository qrRepository;
    public final ContentService contentService;

    public Object saveQRcode(QRcodeInsertDto qrCodeInsertDto, Long profileId) {
        //TODO SERVICE WITH GENERATION QRIMAGE
        //TODO return id profile
        var id = (Long) qrRepository.initQrCode(LocalDateTime.now(), qrCodeInsertDto.getDescription(), new byte[]{}, qrCodeInsertDto.getName(), profileId);
        var entity = qrRepository.getQREntityById(id);

        var updatedEntity = saveContentsToQr(entity, qrCodeInsertDto.getContents());
        qrRepository.save(updatedEntity);
        return id;

    }

    public Object deleteQRcodeById(Long id) {
        var isSuccess = qrRepository.deleteQrCode(id);
        return isSuccess;
    }

    public Object updateQrCode(QRcodeUpdateDto qRcodeUpdateDto) {
        var isSuccess = qrRepository.updatetQrCode(qRcodeUpdateDto.getId(), qRcodeUpdateDto.getDescription(), qRcodeUpdateDto.getName());
        return isSuccess;
    }

    private QREntity saveContentsToQr(QREntity entity, List<MultipartFile> qrCodeInsertDto){
        if(entity.getContentEntities()!=null && (!entity.getContentEntities().isEmpty())){
            for (var item :
                    qrCodeInsertDto) {
                ContentEntity content = new ContentEntity();
                try {
                    content.setData(item.getBytes());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                content.setFileName(item.getName());
                content.setExtension("default");
                entity.getContentEntities().add(content);
            }
        }
        return  entity;
    }
    public Object addListContent(List<MultipartFile> contentInsertDtos, Long qrId) {
        var entity = qrRepository.getQREntityById(qrId);
        var updatedEntity = saveContentsToQr(entity, contentInsertDtos);
        qrRepository.save(updatedEntity);

        return  "good";
    }

}
