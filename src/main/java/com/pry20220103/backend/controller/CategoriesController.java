package com.pry20220103.backend.controller;

import com.pry20220103.backend.domain.model.entity.Category;
import com.pry20220103.backend.domain.service.CategoryService;
import com.pry20220103.backend.resource.CategoryResource;
import com.pry20220103.backend.resource.SaveCategoryResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Categorías", description = "Endpoints para gestión de categorías de modelos")
public class CategoriesController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    CategoryService categoryService;

    @Operation(summary = "Crear una categoría nueva.", description = "Agrega una nueva categoría de modelos 3D " +
            "al sistema.", tags = {"categorías"})
    @PostMapping()
    public CategoryResource createCategory(@Valid @RequestBody SaveCategoryResource resource){
        Category category = convertToEntity(resource);
        return convertToResource(categoryService.createCategory(category));
    }

    @Operation(summary = "Obtener una categoría.", description = "Obtiene una categoría dado su Id",
            tags = {"categorías"})
    @GetMapping("/{categoryId}")
    public CategoryResource getCategory(@PathVariable(name = "categoryId") Long categoryId){
        return convertToResource(categoryService.getById(categoryId));
    }

    @Operation(summary = "Obtener todas las categorías.", description = "Obtiene todas las categorías " +
            "del sistema" , tags = {"categorías"})
    @GetMapping()
    public Page<CategoryResource> getAllCategories(Pageable pageable){
        Page<Category> categoriesPage = categoryService.getAll(pageable);
        List<CategoryResource> resources = categoriesPage.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Eliminar una categoría.", description = "Elimina una categoría del sistema " +
            "dado su Id." , tags = {"categorías"})
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable(name = "categoryId") Long categoryId){
        return categoryService.deleteCategory(categoryId);
    }

    private Category convertToEntity(SaveCategoryResource resource){ return mapper.map(resource, Category.class); }
    private CategoryResource convertToResource(Category entity){ return mapper.map(entity, CategoryResource.class); }
}
