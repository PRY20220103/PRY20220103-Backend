package com.pry20220103.backend.service;

import com.pry20220103.backend.domain.model.entity.Model;
import com.pry20220103.backend.domain.persistence.CategoryRepository;
import com.pry20220103.backend.domain.persistence.ModelRepository;
import com.pry20220103.backend.domain.service.ModelService;
import com.pry20220103.backend.shared.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ModelServiceImpl implements ModelService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Override
    public Model createModel(Long categoryId, Model model) {
        return categoryRepository.findById(categoryId).map(category -> {
            model.setCategory(category);
            model.setCreatedAt(new Date());
            model.setViewCount(0);
            return modelRepository.save(model);
        }).orElseThrow(() -> new ResourceNotFoundException("Category", categoryId));
    }

    @Override
    public Model getByModelName(String name) {
        return modelRepository.findByModelName(name).orElseThrow(
                ()-> new ResourceNotFoundException("Model not found with name " + name));
    }

    @Override
    public Model getByIdAndCategoryId(Long modelId, Long categoryId) {
        return modelRepository.findByIdAndCategoryId(modelId, categoryId).orElseThrow(
                ()-> new ResourceNotFoundException("Model", modelId));
    }

    @Override
    public Page<Model> getAllByCategory(Long categoryId, Pageable pageable) {
        return modelRepository.findAllByCategoryId(categoryId, pageable);
    }

    @Override
    public Model updateModel(Long modelId, Model modelRequest) {
        return modelRepository.findById(modelId).map(model -> {
            model.setModelName(modelRequest.getModelName());
            model.setModelDescription(modelRequest.getModelDescription());
            model.setGrade(modelRequest.getGrade());
            model.setColor(modelRequest.getColor());
            model.setAnimated(modelRequest.getAnimated());
            model.setQuestions(modelRequest.getQuestions());
            model.setAnswers(modelRequest.getAnswers());
            model.setModelUrl(modelRequest.getModelUrl());
            return modelRepository.save(model);
        }).orElseThrow(() -> new ResourceNotFoundException("Model", modelId));
    }

    @Override
    public ResponseEntity<?> deleteModel(Long modelId) {
        return modelRepository.findById(modelId).map(model ->{
            modelRepository.delete(model);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException("Model", modelId));
    }
}
