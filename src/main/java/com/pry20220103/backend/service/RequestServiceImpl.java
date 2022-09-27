package com.pry20220103.backend.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pry20220103.backend.domain.model.entity.Request;
import com.pry20220103.backend.domain.model.enumeration.Status;
import com.pry20220103.backend.domain.persistence.*;
import com.pry20220103.backend.domain.service.RequestService;
import com.pry20220103.backend.shared.exception.ResourceNotFoundException;

@Service
public class RequestServiceImpl implements RequestService{

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Request createRequest(Long userId, Request request) {

      
        return userRepository.findById(userId).map(user -> {
            request.setUser(user);
            request.setStatus(Status.STATUS_REQUESTED);
            return requestRepository.save(request);
        }).orElseThrow(() -> new ResourceNotFoundException("User", userId));
    }

    @Override
    public Request updateRequest(Long userId, Long requestId, Request request) {


        return requestRepository.findByIdAndUserId(requestId, userId).map(req -> {
            req.setUser(request.getUser());
            req.setGrade(request.getGrade());
            req.setCourse(request.getCourse());
            req.setRequestModelName(request.getRequestModelName());
            req.setDescription(request.getDescription());
            req.setRequestedBy(request.getRequestedBy());
            req.setStatus(request.getStatus());
            return requestRepository.save(req);
        }).orElseThrow(() -> new ResourceNotFoundException("Request", requestId));
    }

    @Override
    public Request getRequestByIdAndUserId(Long requestId, Long userId) {
        return requestRepository.findByIdAndUserId(requestId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found with id: " +
                        requestId + " and user id: " + userId));
    }

    @Override
    public Page<Request> getAllByUserId(Long userId, Pageable pageable) {
        return requestRepository.findAllByUserId(userId, pageable);
    }

    @Override
    public Page<Request> getAllRequest(Pageable pageable) {
        return requestRepository.findAll(pageable);
    }

    @Override
    public ResponseEntity<?> deleteRequest(Long userId, Long requestId) {
        return requestRepository.findByIdAndUserId(requestId, userId).map(req ->{
            requestRepository.delete(req);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(" Course not found with Id " + requestId + " and UserId " + userId));
    }

    
    
}
