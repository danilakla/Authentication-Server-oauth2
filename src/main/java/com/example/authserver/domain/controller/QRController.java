package com.example.authserver.domain.controller;

import com.example.authserver.domain.dto.QRcodeInsertDto;
import com.example.authserver.domain.dto.QRcodeUpdateDto;
import com.example.authserver.domain.service.QRcodeService;
import com.example.authserver.domain.util.UtilService;
import com.google.zxing.WriterException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
public class QRController {

    private  final QRcodeService qRcodeService;
    private  final UtilService utilService;
    @PostMapping("/createQRCode")
    public  Object createQrcode(Principal principal, QRcodeInsertDto qRcodeInsertDto) throws IOException, WriterException {
            Long profileId= utilService.retrieveFromClaimsProfileId(principal);
            qRcodeService.saveQRcode(qRcodeInsertDto,  profileId);

        return 1;

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

