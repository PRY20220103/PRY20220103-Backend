package com.pry20220103.backend.controller;

import com.pry20220103.backend.domain.service.UserService;
import com.pry20220103.backend.domain.service.communication.AuthenticateRequest;
import com.pry20220103.backend.domain.service.communication.RegisterRequest;
import com.pry20220103.backend.mapping.UserMapper;
import com.pry20220103.backend.resource.UserResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Usuarios", description = "Endpoints para gestión de usuarios")
public class UsersController {

    private final UserService userService;

    private final UserMapper mapper;


    public UsersController(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @Operation(summary = "Autenticar usuario", description = "Permite el inicio de sesión de un usuario " +
            "cual será asociado.", tags = {"usuarios"})
    @PostMapping("/auth/sign-in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthenticateRequest request) {
        return userService.authenticate(request);
    }

    @Operation(summary = "Registrar usuario", description = "Permite que un nuevo usuario se registre en el sistema",
            tags = {"usuarios"})
    @PostMapping("/auth/sign-up")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @Operation(summary = "Obtener todos los usuarios", description = "Devuelve una lista  de todos los usuarios registrados.",
            tags = {"usuarios"})
    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllUsers(Pageable pageable) {
        Page<UserResource> resources = mapper.modelListToPage(userService.getAll(), pageable);
        return ResponseEntity.ok(resources);
    }

}
