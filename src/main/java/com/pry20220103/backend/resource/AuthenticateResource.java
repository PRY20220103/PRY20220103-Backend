package com.pry20220103.backend.resource;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class AuthenticateResource {
    private Long id;
    private String username;
    private List<String> roles;
    private String token;
}
