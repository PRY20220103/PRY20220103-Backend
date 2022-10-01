package com.pry20220103.backend.resource;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class SaveModelResource {
    @NotNull
    @NotBlank
    private String modelName;

    @NotNull
    @NotBlank
    private String modelDescription;

    @NotNull
    @NotBlank
    private String grade;

    @NotNull
    @NotBlank
    private String color;

    private Boolean animated;

    private String modelUrl;

    private List<String> questions;

    private List<String> answers;
}