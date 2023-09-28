package com.example.authserver.domain.controller;

import com.example.authserver.domain.repository.ProfileRepository;
import com.example.authserver.domain.repository.QRRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
public class MyControllerxx {

    private  final QRRepository qrRepository;
    private  final ProfileRepository profileRepository;
    @PostMapping("/image")
    public void testFileds(@RequestParam("mediaData") MultipartFile mediaData) throws IOException {

        var type=mediaData.getBytes();
      var v=new byte[]{1,2,3,};

//        profileRepository.initProfile("das", "danila, " ,"kdartuzov");
        qrRepository.initQrCode(LocalDateTime.now(), "some ",
                v, "das", 1L);
    }

}