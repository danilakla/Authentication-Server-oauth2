package com.example.authserver.domain.controller;

import com.example.authserver.domain.dto.ContentsMapperDto;
import com.example.authserver.domain.entity.ContentEntity;
import com.example.authserver.domain.service.ContentService;
import com.example.authserver.util.UtilService;
import com.google.zxing.WriterException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
public class ContentController {
    private final ContentService contentService;
    private final UtilService utilService;

    @DeleteMapping("/deleteContent")
    public Object deleteContent(@RequestParam("id") Long id) {
        try {

            return contentService.deleteContentById(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }


    @GetMapping("/getContents")
    public List<ContentsMapperDto> getQrCodes(@RequestParam("qrId") Long qrId , Principal principal) throws Exception {
        Long profileId = utilService.retrieveFromClaimsProfileId(principal);

        contentService.hasUserContent(qrId, profileId);
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
