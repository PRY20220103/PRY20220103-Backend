package com.pry20220103.backend.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pry20220103.backend.domain.model.entity.Comment;
import com.pry20220103.backend.domain.persistence.CommentRepository;
import com.pry20220103.backend.domain.persistence.ModelRepository;
import com.pry20220103.backend.domain.service.CommentService;
import com.pry20220103.backend.shared.exception.ResourceNotFoundException;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Override
    public Comment createComment(Long modelId, Comment comment, String userName) {

        Calendar aux = Calendar.getInstance();

        return modelRepository.findById(modelId).map(model -> {
            comment.setModel(model);
            comment.setPostDate(aux.getTime());
            comment.setNameCommenter(userName);
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("Model", modelId));
    }

    @Override
    public Comment updateComment(Long modelId, Long commentId, Comment comment) {


        return commentRepository.findByIdAndModelId(commentId, modelId).map(com -> {
            com.setContent(comment.getContent());
            com.setType(comment.getType());
            return commentRepository.save(com);
        }).orElseThrow(() -> new ResourceNotFoundException("comment", commentId));
    }

    @Override
    public Comment getCommentByIdAndModelId(Long commentId, Long modelId) {
        return commentRepository.findByIdAndModelId(commentId, modelId)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found with id: " +
                        commentId + " and model id: " + modelId));
    }

    @Override
    public ResponseEntity<?> deleteComment(Long modelId, Long commentId) {
        return commentRepository.findByIdAndModelId(commentId, modelId).map(comment ->{
            commentRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(" Comment not found with Id " + commentId + " and Model Id " + modelId));
    }

    @Override
    public Page<Comment> getAllByModelId(Long modelId, Pageable pageable) {
        return commentRepository.findAllByModelId(modelId, pageable);
    }

    @Override
    public Page<Comment> getAllComments(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }
    
}
