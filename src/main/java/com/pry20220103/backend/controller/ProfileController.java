package com.pry20220103.backend.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pry20220103.backend.domain.model.entity.Profile;
import com.pry20220103.backend.domain.service.ProfileService;
import com.pry20220103.backend.domain.service.UserService;
import com.pry20220103.backend.resource.ProfileResource;
import com.pry20220103.backend.resource.SaveProfileResource;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/users/{userId}")
@Tag(name = "Profiles", description = "User API")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserService userService;

    @Autowired
    ModelMapper mapper;


    @Operation(summary = "Create a profile by User Id", description = "Create profile from User by his Id", tags = {"profiles"})
    @PostMapping("/profile")
    public ProfileResource createProfile(@Valid @RequestBody SaveProfileResource resource,
                                         @PathVariable(name = "userId") Long id) {
        Profile profile = profileService.createProfile(id, convertToEntity(resource));
        return convertToResource(profileService.createProfile(id, profile));

        // return convertToResource(profileService.createProfile(id, convertToEntity(resource)));
    }

    @Operation(summary = "Get profile by User Id ", description = "Get a profile from User by his Id", tags = {"profiles"})
    @GetMapping("/profile")
    public ProfileResource getProfile(@PathVariable(name = "userId") Long userId) {
        return convertToResource(profileService.getProfileById(userId));
    }

    @Operation(summary = "Update profile by User Id ", description = "Update a profile from User by his Id", tags = { "profiles" })
    @PutMapping("/profile")
    public ProfileResource updateProfile(@PathVariable(name = "userId") Long userId,
                                         @Valid @RequestBody SaveProfileResource resource) {
        Profile profile = profileService.updateProfile(userId, convertToEntity(resource));
        return convertToResource(profileService.updateProfile(userId, profile));
    }
    

    private Profile convertToEntity(SaveProfileResource resource){ return mapper.map(resource, Profile.class);}
    private ProfileResource convertToResource(Profile entity){return mapper.map(entity, ProfileResource.class); }
}
