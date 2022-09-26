package com.pry20220103.backend.resource;

import javax.validation.constraints.*;

import com.pry20220103.backend.domain.model.enumeration.Status;

public class SaveRequestResource {
    @NotBlank
    @NotNull
    private String grade;

    @NotBlank
    @NotNull
    private String course;

    @NotBlank
    @NotNull
    private String requestedModelName;

    @NotBlank
    @NotNull
    private String description;

    @NotBlank
    @NotNull
    private String requestedBy;

    @NotBlank
    @NotNull
    private Status status;
}
