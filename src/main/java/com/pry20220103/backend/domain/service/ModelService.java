package com.pry20220103.backend.domain.service;

import com.pry20220103.backend.domain.model.entity.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ModelService {
    Model createModel(Long categoryId, Model model);
    Model getByModelName(String name);
    Model getByIdAndCategoryId(Long modelId, Long categoryId);
    Page<Model> getAllByCategory(Long categoryId, Pageable pageable);
    Model updateModel(Long modelId, Model model);
    ResponseEntity<?> deleteModel(Long modelId);
}
