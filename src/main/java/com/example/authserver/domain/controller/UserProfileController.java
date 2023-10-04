package com.example.authserver.domain.controller;

import com.example.authserver.domain.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor

public class UserProfileController {

    private  final ProfileService profileService;

//    @PutMapping("/updateProfile")
//
//    public Object updateProfile(Dto){
//
//    }

}
