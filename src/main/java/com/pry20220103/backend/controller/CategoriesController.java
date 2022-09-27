package com.pry20220103.backend.controller;

import com.pry20220103.backend.domain.model.entity.Category;
import com.pry20220103.backend.domain.service.CategoryService;
import com.pry20220103.backend.resource.CategoryResource;
import com.pry20220103.backend.resource.SaveCategoryResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/categories")
public class CategoriesController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    CategoryService categoryService;

    @PostMapping()
    public CategoryResource createCategory(@Valid @RequestBody SaveCategoryResource resource){
        Category category = convertToEntity(resource);
        return convertToResource(categoryService.createCategory(category));
    }

    @GetMapping("/{categoryId}")
    public CategoryResource getCategory(@PathVariable(name = "categoryId") Long categoryId){
        return convertToResource(categoryService.getById(categoryId));
    }

    @GetMapping()
    public Page<CategoryResource> getAllCategories(Pageable pageable){
        Page<Category> categoriesPage = categoryService.getAll(pageable);
        List<CategoryResource> resources = categoriesPage.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable(name = "categoryId") Long categoryId){
        return categoryService.deleteCategory(categoryId);
    }

    private Category convertToEntity(SaveCategoryResource resource){ return mapper.map(resource, Category.class); }
    private CategoryResource convertToResource(Category entity){ return mapper.map(entity, CategoryResource.class); }
}
