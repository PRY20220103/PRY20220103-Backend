package com.pry20220103.backend.resource;

import lombok.Data;

@Data
public class ProfileResource {
    private Long id;
    private String name;
    private String surname;
    private String profileImage;
}
