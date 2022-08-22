package com.pry20220103.backend.domain.service;

import com.pry20220103.backend.domain.model.entity.User;
import com.pry20220103.backend.domain.service.communication.AuthenticateRequest;
import com.pry20220103.backend.domain.service.communication.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    ResponseEntity<?> authenticate(AuthenticateRequest request);

    ResponseEntity<?> register(RegisterRequest request);

    List<User> getAll();

}
