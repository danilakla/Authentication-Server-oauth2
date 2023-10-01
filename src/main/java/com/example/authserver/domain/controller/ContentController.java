package com.example.authserver.domain.controller;

import com.example.authserver.domain.service.ContentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ContentController {
private  final ContentService contentService;

@PostMapping("/deleteContent")
public  Object deleteContent(Long id){
        contentService.deleteContentById(id);
        return  null;
}


}
