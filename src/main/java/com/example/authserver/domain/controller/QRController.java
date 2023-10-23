package com.example.authserver.domain.controller;

import com.example.authserver.domain.dto.ContentsDto;
import com.example.authserver.domain.dto.QRcodeInsertDto;
import com.example.authserver.domain.dto.QRcodeUpdateDto;
import com.example.authserver.domain.entity.QREntity;
import com.example.authserver.domain.repository.ContentRepository;
import com.example.authserver.domain.service.QRcodeService;
import com.example.authserver.util.UtilService;
import com.google.zxing.WriterException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.Base64;
import java.util.List;
//TODO BY SCANNING THE QR CODE REDIRECT  TO
// PAGE THAT REPROD THE CONTENT OF THE QR WHERE THE BASIC USER CAN SEE ALL
// INFO ABOUT CONTAING INFO AND DELETE CONTENT

//TODO DEADLINE
@RestController
@CrossOrigin

@AllArgsConstructor
public class QRController {

    private  final QRcodeService qRcodeService;
    private  final UtilService utilService;
    private  final ContentRepository contentRepository;
    @PostMapping("/createQRCode")
    public  Object createQrcode(Principal principal, QRcodeInsertDto qRcodeInsertDto) throws IOException, WriterException {

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
    public  Object addContentToQR(@RequestParam("qrId") Long qrId, ContentsDto mediaFiles){
        qRcodeService.addListContent(mediaFiles.getContents(), qrId);
        return  null;

    }

    @PutMapping("/updateQrCode")
    public  Object updateQrCode(@RequestParam("qrId") Long qrId, QRcodeUpdateDto qRcodeUpdateDto){
        qRcodeUpdateDto.setId(qrId);
        qRcodeService.updateQrCode(qRcodeUpdateDto);
        return  null;

    }

    @GetMapping("/getQrCodes")
    public List<QREntity> getQrCodes(Principal principal) throws IOException, WriterException {

        Long profileId= utilService.retrieveFromClaimsProfileId(principal);
        var response= qRcodeService.getQrCodesById(  profileId);

        return response;

    }
}

