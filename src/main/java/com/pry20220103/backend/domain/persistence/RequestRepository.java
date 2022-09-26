package com.pry20220103.backend.domain.persistence;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pry20220103.backend.domain.model.entity.*;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    
    Optional<Request> findByIdAndUserId(Long requestId, Long userId);
    Page<Request> findAllByUserId(Long userId, Pageable pageable);
    
}
