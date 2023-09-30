package com.example.authserver.domain.service.common;

import com.example.authserver.domain.service.GenerationQRImageService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class GenerationQRImageServiceImpl  implements GenerationQRImageService {


    @Value("${qr.client-url}")
    private  String clientUrl;
    @Override
    public byte[] generateQRImage(Long qrId) throws WriterException, IOException {
        String qrCodeValue= "https://www.google.com/?id=10"; //clientUrl+"?qrid="+qrId;
        var qrCodeWriter=new QRCodeWriter();
        BitMatrix bitMatrix= qrCodeWriter.encode(qrCodeValue, BarcodeFormat.QR_CODE, 400, 400);
     return    convertBufferImageToByte(MatrixToImageWriter.toBufferedImage(bitMatrix));

    }
    private byte[] convertBufferImageToByte(BufferedImage bufferedImage) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        byte[] imageBytes = baos.toByteArray();

        return imageBytes;
    }
}
