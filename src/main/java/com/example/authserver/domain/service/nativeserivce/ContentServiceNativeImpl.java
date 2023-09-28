package com.example.authserver.domain.service.nativeserivce;

import com.example.authserver.domain.dto.ContentInsertDto;
import com.example.authserver.domain.repository.ContentRepository;
import com.example.authserver.domain.service.ContentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContentServiceNativeImpl implements ContentService {

    public  final ContentRepository contentRepository;

    public Object saveContent(ContentInsertDto contentInsertDto){

        try{
            var isSuccess= contentRepository.addContent(contentInsertDto.getData(),contentInsertDto.getFilename(),contentInsertDto.getQrId(),
                    contentInsertDto.getExtension(),21L);
        }
        catch (Exception e){

        }
        return  "good";
    }



    public Object deleteContentById(Long id){

        try{
            var isSuccess= contentRepository.deleteContent(id);
        }
        catch (Exception e){

        }
        return  "good";

    }

}
