package com.example.authserver.domain.controller;

import com.example.authserver.domain.dto.QRcodeInsertDto;
import com.example.authserver.domain.dto.QRcodeUpdateDto;
import com.example.authserver.domain.repository.ContentRepository;
import com.example.authserver.domain.service.QRcodeService;
import com.example.authserver.util.UtilService;
import com.google.zxing.WriterException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Base64;
import java.util.List;

@RestController
@CrossOrigin

@AllArgsConstructor
public class QRController {

    private  final QRcodeService qRcodeService;
    private  final UtilService utilService;
    private  final ContentRepository contentRepository;
    @PostMapping("/createQRCode")
    public  Object createQrcode(Principal principal, QRcodeInsertDto qRcodeInsertDto) throws IOException, WriterException {

      var d=  contentRepository.getContentEntityById(1l);
            Long profileId= utilService.retrieveFromClaimsProfileId(principal);
           var response= qRcodeService.saveQRcode(qRcodeInsertDto,  profileId);

        return Base64.getEncoder().encodeToString((byte[]) response);

    }
    @DeleteMapping("/deleteQRCode")
    public  Object deleteQRCode(@RequestParam("qrId") Long qrid){
        qRcodeService.deleteQRcodeById(qrid);

        return  null;
    }

    @PostMapping("/addContentToQR")
    public  Object addContentToQR(@RequestParam("qrId") Long qrId, List<MultipartFile> mediaFiles){
        qRcodeService.addListContent(mediaFiles, qrId);
        return  null;

    }

    @PostMapping("/updateQrCode")
    public  Object updateQrCode( QRcodeUpdateDto qRcodeUpdateDto){
        qRcodeService.updateQrCode(qRcodeUpdateDto);
        return  null;

    }
}

