package com.pry20220103.backend.domain.service;

import com.pry20220103.backend.domain.model.entity.Profile;

public interface ProfileService {
    Profile createProfile(Long userId, Profile profile);
    Profile getProfileById(Long id);
    Profile updateProfile(Long userId, Profile profileRequest);
}
