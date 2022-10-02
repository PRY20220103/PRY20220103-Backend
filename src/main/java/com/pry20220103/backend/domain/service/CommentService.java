package com.pry20220103.backend.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.pry20220103.backend.domain.model.entity.Comment;

public interface CommentService {
    Comment createComment(Long modelId, Comment comment, String userName);
    Comment updateComment(Long modelId, Long commentId, Comment comment);
    Comment getCommentByIdAndModelId(Long commentId, Long modelId);
    ResponseEntity<?> deleteComment(Long modelId, Long commentId);
    Page<Comment> getAllByModelId(Long modelId, Pageable  pageable);
    Page<Comment> getAllComments(Pageable pageable);
}
