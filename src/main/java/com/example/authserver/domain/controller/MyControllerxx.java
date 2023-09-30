package com.example.authserver.domain.controller;

import com.example.authserver.authentication.entity.UserEntity;
import com.example.authserver.authentication.security.CustomUsrDetails;
import com.example.authserver.authentication.security.JwtService;
import com.example.authserver.domain.repository.ProfileRepository;
import com.example.authserver.domain.repository.QRRepository;
import com.example.authserver.domain.service.GenerationQRImageService;
import com.google.zxing.WriterException;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;

@RestController
@AllArgsConstructor
public class MyControllerxx {

    private  final QRRepository qrRepository;
    private  final ProfileRepository profileRepository;
    private  final GenerationQRImageService test;
    private final JwtService tokenService;

    @GetMapping("/test12")
    public String tet(Authorization ddas){
        var cd=new UserEntity();
        cd.setRoles(new HashSet<>());
        cd.setEmail("dasdas");
        return    tokenService.generateAccessToken(new CustomUsrDetails(cd),1L);

    }

    @PostMapping(value = "/image", produces = { MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE } )
    public ResponseEntity<byte[]> testFileds(@RequestParam("mediaData") MultipartFile mediaData) throws IOException, WriterException {

        var type=mediaData.getBytes();
      var v=new byte[]{1,2,3,};
        var imageBytes= test.generateQRImage(1L);

        // Set the appropriate headers and return the byte array in the ResponseEntity
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG); // or MediaType.IMAGE_JPEG
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
//        profileRepository.initProfile("das", "danila, " ,"kdartuzov");
//        qrRepository.initQrCode(LocalDateTime.now(), "some ",
//                v, "das", 1L);
    }

}