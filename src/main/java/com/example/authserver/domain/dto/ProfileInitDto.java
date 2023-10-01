package com.example.authserver.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileInitDto {

    private String name;

    private String lastName;

    private String about;
    private String email;

}
