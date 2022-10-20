package com.pry20220103.backend.resource;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class SaveUsageLogResource {

    @NotBlank
    @NotNull
    private String modelName;

    @NotBlank
    @NotNull
    @Size(max = 20)
    private String roleName;

    @NotBlank
    @NotNull
    private String modelDescription;

    @NotBlank
    @NotNull
    private String modelGrade;

    private Boolean animated;

    private Integer viewCount;

    private Date createdAt;
}
