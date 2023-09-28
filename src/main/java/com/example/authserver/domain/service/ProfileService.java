package com.example.authserver.domain.service;

import com.example.authserver.domain.dto.ProfileInitDto;

public interface ProfileService {

    Object saveProfile(ProfileInitDto profileInitDto);
}
