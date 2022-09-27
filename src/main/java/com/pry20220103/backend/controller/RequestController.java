package com.pry20220103.backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

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
public class RequestController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RequestService requestService;


    @PostMapping("/api/v1/users/{userId}/request")
    public RequestResource createRequest(@PathVariable(name = "userId") Long userId,
                          @Valid @RequestBody SaveRequestResource resource){
        Request request = convertToEntity(resource);
        return convertToResource(requestService.createRequest(userId, request));
    }

    @GetMapping("/api/v1/users/{userId}/request/{requestId}")
    public RequestResource getRequest(@PathVariable(name = "userId") Long userId,
                                        @PathVariable(name = "requestId") Long requestId){
        return convertToResource(requestService.getRequestByIdAndUserId(requestId,  userId));
    }

    @PutMapping("/api/v1/users/{userId}/request/{requestId}")
    public RequestResource updateRequest(@PathVariable(name = "userId") Long userId,
                                           @PathVariable(name = "requestId") Long requestId,
                                           @Valid @RequestBody SaveRequestResource resource){
        Request request = convertToEntity(resource);
        return convertToResource(requestService.updateRequest(userId, requestId, request));
    }

    @GetMapping("/api/v1/users/{userId}/request")
    public Page<RequestResource> getAllRequestsByUserId(@PathVariable(name = "userId") Long userId,
                                                           Pageable pageable){
        Page<Request> requestPage = requestService.getAllByUserId(userId, pageable);
        List<RequestResource> resources = requestPage.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/api/v1/request")
    public Page<RequestResource> getAllRequests(Pageable pageable){
        Page<Request> requestPage = requestService.getAllRequest(pageable);
        List<RequestResource> resources = requestPage.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @DeleteMapping("/api/v1/users/{userId}/request/{requestId}")
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
