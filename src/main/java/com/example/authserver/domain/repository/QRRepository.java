package com.example.authserver.domain.repository;

import com.example.authserver.domain.entity.QREntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QRRepository extends JpaRepository<QREntity, Long> {


}
