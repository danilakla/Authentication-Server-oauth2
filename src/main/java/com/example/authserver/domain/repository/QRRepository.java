package com.example.authserver.domain.repository;

import com.example.authserver.domain.entity.QREntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface QRRepository extends JpaRepository<QREntity, Long> {

    @Procedure(name = "qrcode.insertQR" )
    Object initQrCode(LocalDateTime p_creation_date, String p_description, byte [] p_image,
                      String p_name, Long p_profile_id);

    @Procedure(name = "qrcode.deleteQR" )
    Object deleteQrCode(Long p_id);

    @Procedure(name = "qrcode.insertQR" )
    Object updatetQrCode( Long p_id, String p_description,
                      String p_name);

    QREntity getQREntityById(Long id);


}
