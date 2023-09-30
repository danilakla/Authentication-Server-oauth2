package com.example.authserver.domain.service;

import com.google.zxing.WriterException;

import java.io.IOException;

public interface GenerationQRImageService {

    byte[] generateQRImage(Long qrId) throws WriterException, IOException;
}
