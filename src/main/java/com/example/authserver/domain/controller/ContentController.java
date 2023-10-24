package com.example.authserver.domain.controller;

import com.example.authserver.domain.dto.ContentsMapperDto;
import com.example.authserver.domain.entity.ContentEntity;
import com.example.authserver.domain.service.ContentService;
import com.google.zxing.WriterException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
public class ContentController {
    private final ContentService contentService;

    @DeleteMapping("/deleteContent")
    public Object deleteContent(@RequestParam("id") Long id) {

        return contentService.deleteContentById(id);
    }


    @GetMapping("/getContents")
    public List<ContentsMapperDto> getQrCodes(@RequestParam("qrId") Long qrId) throws IOException, WriterException {

        var contents = contentService.getContentsById(qrId);
        List<ContentsMapperDto> contentsMapperDtos = new ArrayList<>();
        for (var el :
                contents) {
            contentsMapperDtos.add(new ContentsMapperDto(el));
        }
        return contentsMapperDtos;

    }

    @GetMapping("/getContentById")
    public ContentEntity getContentById(@RequestParam("id") Long id) throws IOException, WriterException {

        var content = contentService.getContentById(id);

        return content;

    }


}
