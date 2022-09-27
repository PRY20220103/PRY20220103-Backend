package com.pry20220103.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pry20220103.backend.domain.model.entity.Profile;
import com.pry20220103.backend.domain.model.entity.User;
import com.pry20220103.backend.domain.persistence.ProfileRepository;
import com.pry20220103.backend.domain.persistence.UserRepository;
import com.pry20220103.backend.domain.service.ProfileService;
import com.pry20220103.backend.shared.exception.ResourceNotFoundException;

@Service
public class ProfileServiceImpl implements ProfileService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Profile createProfile(Long userId, Profile profile) {
        return userRepository.findById(userId).map(user -> {
            profile.setUser(user);
            return profileRepository.save(profile);
        }).orElseThrow(() -> new ResourceNotFoundException("User not found with Id: "+userId));
    }

    @Override
    public Profile getProfileById(Long id) {
        return profileRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", id));
    }

    @Override
    public Profile updateProfile(Long userId, Profile profileRequest) {
        if(!userRepository.existsById(userId))
            throw new ResourceNotFoundException("UserId", userId);

        Profile profile = profileRepository.findById(userId).orElseThrow(() ->
        new ResourceNotFoundException("Profile not found for user with Id: "+userId));
        profile.setName(profileRequest.getName());
        profile.setSurname(profileRequest.getSurname());
        profile.setProfileImage(profileRequest.getProfileImage());
        return profileRepository.save(profile);
    }
    
}
