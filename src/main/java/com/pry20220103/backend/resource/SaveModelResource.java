package com.pry20220103.backend.resource;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SaveModelResource {
    @NotNull
    @NotBlank
    private String modelName;

    @NotNull
    @NotBlank
    @Size(max = 5000)
    private String modelDescription;

    @NotNull
    @NotBlank
    private String grade;

    @NotNull
    @NotBlank
    private String color;

    private Boolean animated;

    private String modelUrl;

    private String thumbnail;

}
