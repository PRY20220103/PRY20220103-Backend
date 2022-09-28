package com.pry20220103.backend.controller;

import com.pry20220103.backend.domain.model.entity.Model;
import com.pry20220103.backend.domain.service.ModelService;
import com.pry20220103.backend.resource.ModelResource;
import com.pry20220103.backend.resource.SaveModelResource;
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
@RequestMapping("/api/v1")
@Tag(name = "modelos", description = "Endpoints para gestión de modelos 3D")
public class ModelsController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    ModelService modelService;

    @Operation(summary = "Crear un modelo 3D nuevo", description = "Permite crear un modelo dado el id de la categoría a la " +
            "cual será asociado.", tags = {"modelos", "categorías"})
    @PostMapping("/categories/{categoryId}/models")
    public ModelResource createModel(@PathVariable(name = "categoryId") Long categoryId,
                                     @Valid @RequestBody SaveModelResource resource){
        return convertToResource(modelService.createModel(categoryId, convertToEntity(resource)));
    }

    @Operation(summary = "Obtener modelo por nombre", description = "Retorna un modelo cuyo nombre coincida con el parámetro " +
            "enviado.", tags = {"modelos"})
    @GetMapping("/model-search/{modelName}")
    public ModelResource getByName(@PathVariable(name = "modelName") String modelName){
        return convertToResource(modelService.getByModelName(modelName));
    }

    @Operation(summary = "Obtener por id de modelo y de categoría", description = "Retorna un modelo tras buscarlo por el id " +
            "de su categoría asociada y su propio id", tags = {"modelos"})
    @GetMapping("/categories/{categoryId}/models/{modelId}")
    public ModelResource getByIdAndCategoryId(@PathVariable(name = "categoryId") Long categoryId,
                                              @PathVariable(name = "modelId") Long modelId){
        return convertToResource(modelService.getByIdAndCategoryId(modelId, categoryId));
    }

    @Operation(summary = "Obtener todos los modelos de una categoría", description = "Retorna todos los modelos asociados a " +
            "la categoría especificada por id", tags = {"modelos",  "categorías"})
    @GetMapping("/categories/{categoryId}/models")
    public Page<ModelResource> getAllByCategory(@PathVariable(name = "categoryId") Long categoryId,
                                                  Pageable pageable){
        Page<Model> modelsPage = modelService.getAllByCategory(categoryId, pageable);
        List<ModelResource> resources = modelsPage.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Actualizar modelo",  description = "Actualizar los datos del modelo especificado por su id",
            tags = {"modelos"})
    @PutMapping("/models/{modelId}")
    public ModelResource updateModel(@PathVariable(name = "modelId") Long modelId,
                                     @Valid @RequestBody SaveModelResource resource){
        return convertToResource(modelService.updateModel(modelId, convertToEntity(resource)));
    }

    @Operation(summary = "Remover modelo", description = "Eliminar del sistema el modelo especificado por su id",
            tags = {"modelos"})
    @DeleteMapping("/models/{modelId}")
    public ResponseEntity<?> deleteModel(@PathVariable(name = "modelId") Long modelId){
        return modelService.deleteModel(modelId);
    }
    //TODO: Documentar todas las entidades
    private Model convertToEntity(SaveModelResource resource){ return mapper.map(resource, Model.class); }
    private ModelResource convertToResource(Model entity){ return mapper.map(entity, ModelResource.class); }
}
