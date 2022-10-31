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

import com.pry20220103.backend.domain.model.entity.Comment;
import com.pry20220103.backend.domain.service.CommentService;
import com.pry20220103.backend.resource.CommentResource;
import com.pry20220103.backend.resource.SaveCommentResource;

@RestController
@CrossOrigin
@Tag(name = "Comentarios", description = "Endpoints para gestión de comentarios")
public class CommentController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CommentService commentService;

    @Operation(summary = "Crear comentario", description = "Permite crear un comentario dado el id del modelo " +
            "correspondiente y el nombre de usuario del autor.", tags = {"comentarios", "modelos", "usuarios"})
    @PostMapping("/api/v1/models/{modelId}/comments/{username}")
    public CommentResource createComment(@PathVariable(name = "modelId") Long modelId, @PathVariable(name = "username") String userName,
                          @Valid @RequestBody SaveCommentResource resource){
        Comment comment = convertToEntity(resource);
        return convertToResource(commentService.createComment(modelId, comment, userName));
    }

    @Operation(summary = "Obtener comentario", description = "Retorna un comentario dado el id del modelo correspondiente y del comentario. ",
            tags = {"comentarios", "modelos"})
    @GetMapping("/api/v1/models/{modelId}/comments/{commentId}")
    public CommentResource getComment(@PathVariable(name = "modelId") Long modelId,
                                        @PathVariable(name = "commentId") Long commentId){
        return convertToResource(commentService.getCommentByIdAndModelId(commentId,  modelId));
    }

    @Operation(summary = "Actualizar comentario", description = "Permite actualizar un comentario dado el id del modelo correspondiente y del comentario. ",
            tags = {"comentarios", "modelos"})
    @PatchMapping("/api/v1/models/{modelId}/comments/{commentId}")
    public CommentResource updateComment(@PathVariable(name = "modelId") Long modelId,
                                           @PathVariable(name = "commentId") Long commentId,
                                           @Valid @RequestBody SaveCommentResource resource){
        Comment comment = convertToEntity(resource);
        return convertToResource(commentService.updateComment(modelId, commentId, comment));
    }

    @Operation(summary = "Obtener todos los comentarios de un modelo.", description = "Retorna todos los comentarios asociados a un modelo, dado su id.",
            tags = {"comentarios", "modelos"})
    @GetMapping("/api/v1/models/{modelId}/comments")
    public Page<CommentResource> getAllCommentsByModelId(@PathVariable(name = "modelId") Long modelId,
                                                           Pageable pageable){
        Page<Comment> commentPage = commentService.getAllByModelId(modelId, pageable);
        List<CommentResource> resources = commentPage.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Obtener todos los comentarios.", description = "Retorna todos los comentarios registrados en el sistema.",
            tags = {"comentarios"})
    @GetMapping("/api/v1/comments")
    public Page<CommentResource> getAllComments(Pageable pageable){
        Page<Comment> commentPage = commentService.getAllComments(pageable);
        List<CommentResource> resources = commentPage.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Eliminar comentario.", description = "Permite borrar un comentario del sistema, dado su id y el del modelo " +
            "asociado a él.",
            tags = {"comentarios", "modelos"})
    @DeleteMapping("/api/v1/models/{modelId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable(name = "modelId") Long modelId, @PathVariable(name = "commentId") Long requestId) {
        return commentService.deleteComment(modelId, requestId);
    }

    private Comment convertToEntity(SaveCommentResource resource){
        return mapper.map(resource, Comment.class);
    }

    private CommentResource convertToResource(Comment entity){
        return mapper.map(entity, CommentResource.class);
    }
}
