package com.pry20220103.backend.controller;

import com.pry20220103.backend.domain.model.entity.Model;
import com.pry20220103.backend.domain.service.ModelService;
import com.pry20220103.backend.resource.ModelResource;
import com.pry20220103.backend.resource.SaveModelResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class ModelsController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    ModelService modelService;

    @PostMapping("/categories/{categoryId}/models")
    public ModelResource createModel(@PathVariable(name = "categoryId") Long categoryId,
                                     @Valid @RequestBody SaveModelResource resource){
        return convertToResource(modelService.createModel(categoryId, convertToEntity(resource)));
    }

    @GetMapping("/model-search/{modelName}")
    public ModelResource getByName(@PathVariable(name = "modelName") String modelName){
        return convertToResource(modelService.getByModelName(modelName));
    }

    @GetMapping("/categories/{categoryId}/models/{modelId}")
    public ModelResource getByIdAndCategoryId(@PathVariable(name = "categoryId") Long categoryId,
                                              @PathVariable(name = "modelId") Long modelId){
        return convertToResource(modelService.getByIdAndCategoryId(modelId, categoryId));
    }

    @GetMapping("/categories/{categoryId}/models")
    public Page<ModelResource> getAllByCategory(@PathVariable(name = "categoryId") Long categoryId,
                                                  Pageable pageable){
        Page<Model> modelsPage = modelService.getAllByCategory(categoryId, pageable);
        List<ModelResource> resources = modelsPage.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @PutMapping("/models/{modelId}")
    public ModelResource updateModel(@PathVariable(name = "modelId") Long modelId,
                                     @Valid @RequestBody SaveModelResource resource){
        return convertToResource(modelService.updateModel(modelId, convertToEntity(resource)));
    }

    private Model convertToEntity(SaveModelResource resource){ return mapper.map(resource, Model.class); }
    private ModelResource convertToResource(Model entity){ return mapper.map(entity, ModelResource.class); }
}
