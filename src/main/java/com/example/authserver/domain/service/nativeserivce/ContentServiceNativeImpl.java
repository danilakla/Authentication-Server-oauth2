package com.example.authserver.domain.service.nativeserivce;

import com.example.authserver.domain.dto.ContentInsertDto;
import com.example.authserver.domain.entity.ContentEntity;
import com.example.authserver.domain.repository.ContentRepository;
import com.example.authserver.domain.service.ContentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContentServiceNativeImpl implements ContentService {

    public  final ContentRepository contentRepository;

    public Object saveContent(ContentInsertDto contentInsertDto) throws Exception {


            var isSuccess= contentRepository.addContent(contentInsertDto.getData(),contentInsertDto.getFilename(),contentInsertDto.getQrId(),
                    contentInsertDto.getExtension(),21L);
        if((Integer)isSuccess!=1){
            throw new Exception("400");
        }
        return  1;
    }

    @Override
    public List<ContentEntity> getContentsById(Long id) {
        return contentRepository.getAllByQrCodeId(id);
    }


    public Object deleteContentById(Long id) throws Exception {


            var isSuccess= contentRepository.deleteContent(id);
        if((Integer)isSuccess!=1){
            throw new Exception("400");
        }
        return  "good";

    }

    @Override
    public ContentEntity getContentById(Long id) {
        return contentRepository.getContentEntityById(id);
    }

}
