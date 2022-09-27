package com.pry20220103.backend.service;

import com.pry20220103.backend.domain.model.entity.Profile;
import com.pry20220103.backend.domain.model.entity.Role;
import com.pry20220103.backend.domain.model.entity.User;
import com.pry20220103.backend.domain.model.enumeration.Roles;
import com.pry20220103.backend.domain.persistence.RoleRepository;
import com.pry20220103.backend.domain.persistence.UserRepository;
import com.pry20220103.backend.domain.service.UserService;
import com.pry20220103.backend.domain.service.communication.AuthenticateRequest;
import com.pry20220103.backend.domain.service.communication.AuthenticateResponse;
import com.pry20220103.backend.domain.service.communication.RegisterRequest;
import com.pry20220103.backend.domain.service.communication.RegisterResponse;
import com.pry20220103.backend.middleware.JwtHandler;
import com.pry20220103.backend.middleware.UserDetailsImpl;
import com.pry20220103.backend.resource.AuthenticateResource;
import com.pry20220103.backend.resource.UserResource;
import com.pry20220103.backend.shared.exception.ResourceNotFoundException;
import com.pry20220103.backend.shared.mapping.EnhancedModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtHandler handler;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    EnhancedModelMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User not found with username: %s", username)));
        return UserDetailsImpl.build(user);
    }

    @Override
    public ResponseEntity<?> authenticate(AuthenticateRequest request) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = handler.generateToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            AuthenticateResource resource = mapper.map(userDetails, AuthenticateResource.class);
            resource.setRoles(roles);
            resource.setToken(token);

            AuthenticateResponse response = new AuthenticateResponse(resource);

            return ResponseEntity.ok(response);

        } catch(Exception e) {
            AuthenticateResponse response = new AuthenticateResponse(
                    String.format("An error occurred while authenticating: %s", e.getMessage()));
            return ResponseEntity.badRequest().body(response.getMessage());

        }
    }

    @Override
    public ResponseEntity<?> register(RegisterRequest request) {

        if(userRepository.existsByUsername(request.getUsername())) {
            AuthenticateResponse response = new AuthenticateResponse("Username is already taken.");
            return ResponseEntity.badRequest()
                    .body(response.getMessage());
        }

        try {
            Set<String> rolesStringSet = request.getRoles();
            Set<Role> roles = new HashSet<>();

            if (rolesStringSet == null) {
                roleRepository.findByName(Roles.ROLE_STUDENT)
                        .map(roles::add)
                        .orElseThrow(() -> new RuntimeException("Role not found."));
            } else if (rolesStringSet == null) {
                roleRepository.findByName(Roles.ROLE_PROFESSOR)
                        .map(roles::add)
                        .orElseThrow(() -> new RuntimeException("Role not found."));
            } else {
                rolesStringSet.forEach(roleString ->
                        roleRepository.findByName(Roles.valueOf(roleString))
                                .map(roles::add)
                                .orElseThrow(() -> new RuntimeException("Role not found.")));
            }

            logger.info("Roles: {}", roles);

            User user = new User()
                    .withUsername(request.getUsername())
                    .withPassword(encoder.encode(request.getPassword()))
                    .withRoles(roles);

            userRepository.save(user);
            UserResource resource = mapper.map(user, UserResource.class);
            RegisterResponse response = new RegisterResponse(resource);
            return ResponseEntity.ok(response.getResource());

        } catch (Exception e) {
            RegisterResponse response = new RegisterResponse(e.getMessage());
            return ResponseEntity.badRequest().body(response.getMessage());
        }

    }

    @Override
    public List<User> getAll() {

        return userRepository.findAll();

    }

    @Override
    public User setUserProfile(Long userId, Profile profile) {
        return userRepository.findById(userId).map(user -> {
            user.setProfile(profile);
            return userRepository.save(user);
        }).orElseThrow(() ->
                new ResourceNotFoundException("User not found with Id: ", userId));
    }
}
