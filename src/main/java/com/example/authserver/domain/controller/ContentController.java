package com.example.authserver.domain.controller;

import com.example.authserver.domain.entity.ContentEntity;
import com.example.authserver.domain.service.ContentService;
import com.google.zxing.WriterException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
public class ContentController {
private  final ContentService contentService;

@DeleteMapping("/deleteContent")
public  Object deleteContent(@RequestParam("id") Long id){

        return  contentService.deleteContentById(id);
}


        @GetMapping("/getContents")
        public List<ContentEntity> getQrCodes(@RequestParam("qrId") Long qrId) throws IOException, WriterException {

                var response = contentService.getContentsById( qrId);

                return response;

        }


}
