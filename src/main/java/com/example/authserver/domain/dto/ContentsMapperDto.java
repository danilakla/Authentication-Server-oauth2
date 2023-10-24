package com.example.authserver.domain.dto;

import com.example.authserver.domain.entity.ContentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContentsMapperDto {
    public ContentsMapperDto(ContentEntity contentEntity){
        this.id=contentEntity.getId();
        this.extension=contentEntity.getExtension();
        this.fileName=contentEntity.getFileName();
        this.data= (long) contentEntity.getData().length;
        this.id=contentEntity.getId();

    }
    private Long id;

    private String fileName;

    private String extension;


    private Long data;
}
