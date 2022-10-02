package com.pry20220103.backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.pry20220103.backend.domain.model.entity.Request;
import com.pry20220103.backend.domain.service.RequestService;
import com.pry20220103.backend.resource.*;

@RestController
@CrossOrigin
@Tag(name = "Solicitudes", description = "Endpoints para gestión de solicitudes de modelos 3D")
public class RequestController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RequestService requestService;

    @Operation(summary = "Crear una solicitud de modelo 3D.", description = "Permite crear la solicitud " +
            "de un recurso dado el Id del usuario solicitante.", tags = {"solicitudes"})
    @PostMapping("/api/v1/users/{userId}/requests")
    public RequestResource createRequest(@PathVariable(name = "userId") Long userId,
                          @Valid @RequestBody SaveRequestResource resource){
        Request request = convertToEntity(resource);
        return convertToResource(requestService.createRequest(userId, request));
    }

    @Operation(summary = "Obtener solicitud por Id de usuario y Id", description = "Permite obtener la" +
            "información de una solicitud, dado su Id y el del usuario solicitante.", tags = {"solicitudes"})
    @GetMapping("/api/v1/users/{userId}/requests/{requestId}")
    public RequestResource getRequest(@PathVariable(name = "userId") Long userId,
                                        @PathVariable(name = "requestId") Long requestId){
        return convertToResource(requestService.getRequestByIdAndUserId(requestId,  userId));
    }

    @Operation(summary = "Aceptar una solicitud.", description = "" +
            "Acepta una solicitud dado su Id y el del usuario solicitante. Cambia su estado a aceptado.",
            tags = {"solicitudes"})
    @PatchMapping("/api/v1/users/{userId}/requests/{requestId}/accept")
    public RequestResource acceptRequest(@PathVariable(name = "userId") Long userId,
                                           @PathVariable(name = "requestId") Long requestId,
                                           @Valid @RequestBody SaveRequestResource resource){
        Request request = convertToEntity(resource);
        return convertToResource(requestService.acceptRequest(userId, requestId, request));
    }

    @Operation(summary = "Rechazar una solicitud", description = "Rechaza una solicitud dado su Id y el del " +
            "usuario solicitante. Cambia su estado a rechazada.", tags = {"solicitudes"})
    @PatchMapping("/api/v1/users/{userId}/requests/{requestId}/deny")
    public RequestResource denyRequest(@PathVariable(name = "userId") Long userId,
                                           @PathVariable(name = "requestId") Long requestId,
                                           @Valid @RequestBody SaveRequestResource resource){
        Request request = convertToEntity(resource);
        return convertToResource(requestService.denyRequest(userId, requestId, request));
    }

    @Operation(summary = "Obtener todas las solicitudes de un usuario.", description = "Obtiene todas las solicitudes " +
            "realizadas por un usuario, dado su Id", tags = {"solicitudes"})
    @GetMapping("/api/v1/users/{userId}/requests")
    public Page<RequestResource> getAllRequestsByUserId(@PathVariable(name = "userId") Long userId,
                                                           Pageable pageable){
        Page<Request> requestPage = requestService.getAllByUserId(userId, pageable);
        List<RequestResource> resources = requestPage.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Obtener todas las solicitudes.", description = "Obtiene todas las solicitudes del sistema.",
            tags = {"solicitudes"})
    @GetMapping("/api/v1/requests")
    public Page<RequestResource> getAllRequests(Pageable pageable){
        Page<Request> requestPage = requestService.getAllRequest(pageable);
        List<RequestResource> resources = requestPage.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Eliminar una solicitud particular.", description = "Permite eliminar una solicitud dado " +
            "su Id y el del usuario solicitante.", tags = {"solicitudes"})
    @DeleteMapping("/api/v1/users/{userId}/requests/{requestId}")
    public ResponseEntity<?> deleteRequest(@PathVariable(name = "userId") Long userId, @PathVariable(name = "requestId") Long requestId) {
        return requestService.deleteRequest(userId, requestId);
    }

    private Request convertToEntity(SaveRequestResource resource){
        return mapper.map(resource, Request.class);
    }

    private RequestResource convertToResource(Request entity){
        return mapper.map(entity, RequestResource.class);
    }
}
