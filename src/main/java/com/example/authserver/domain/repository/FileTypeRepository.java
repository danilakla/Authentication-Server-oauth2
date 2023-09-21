package com.example.authserver.domain.repository;

import com.example.authserver.domain.entity.FileTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileTypeRepository extends JpaRepository<FileTypeEntity, Long> {


}
