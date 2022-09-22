package com.pry20220103.backend.resource;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class SaveUsageLogResource {

    @NotBlank
    @NotNull
    private String modelName;

    @NotBlank
    @NotNull
    private String modelDescription;

    @NotBlank
    @NotNull
    private String grade;

    private Boolean animated;

    private Integer viewCount;

    private Date createdAt;
}
