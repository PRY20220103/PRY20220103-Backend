package com.pry20220103.backend.domain.service;

import com.pry20220103.backend.domain.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    Category createCategory(Category category);
    Category getById(Long id);
    Page<Category> getAll(Pageable pageable);
    ResponseEntity<?> deleteCategory(Long categoryId);
}
