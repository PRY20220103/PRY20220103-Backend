package com.pry20220103.backend.domain.service;

import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;

import com.pry20220103.backend.domain.model.entity.Request;

public interface RequestService {
    Request createRequest(Long userId, Request usageLog);
    Request updateRequest(Long userId, Long requestId, Request request);
    Request getRequestByIdAndUserId(Long requestId, Long userId);
    ResponseEntity<?> deleteRequest(Long userId, Long courseId);
    Page<Request> getAllByUserId(Long userId, Pageable  pageable);
    Page<Request> getAllRequest(Pageable pageable);
}
