package com.pry20220103.backend.resource;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class SaveUsageLogResource {

    @NotBlank
    @NotNull
    private String roleName;

    private Date viewedAt;

    @NotBlank
    @NotNull
    private String modelGrade;
}
