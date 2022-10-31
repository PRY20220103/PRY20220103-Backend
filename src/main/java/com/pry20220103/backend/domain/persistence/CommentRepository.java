package com.pry20220103.backend.domain.persistence;

import java.util.Optional;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pry20220103.backend.domain.model.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

    Optional<Comment> findByIdAndModelId(Long commentId, Long modelId);
    Page<Comment> findAllByModelId(Long modelId, Pageable pageable);
}
