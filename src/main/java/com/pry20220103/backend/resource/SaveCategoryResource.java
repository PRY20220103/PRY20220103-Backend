package com.pry20220103.backend.resource;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SaveCategoryResource {
    @NotBlank
    @NotNull
    private String name;
}
