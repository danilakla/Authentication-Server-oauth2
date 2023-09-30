package com.example.authserver.domain.service.nativeserivce;

import com.example.authserver.domain.dto.ProfileInitDto;
import com.example.authserver.domain.repository.ProfileRepository;
import com.example.authserver.domain.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfileServiceNativeImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    public Object saveProfile(ProfileInitDto profileInitDto) {
        try {
            //TODO RETURN PROFILE ID AND SET THE VALUE TO JWT TOKEN
            var isSuccess = (Integer)profileRepository.initProfile(profileInitDto.getAbout(), profileInitDto.getLastName(), profileInitDto.getName());
        } catch (Exception e) {
            System.out.println(e);

        }
        return "good";
    }
}
