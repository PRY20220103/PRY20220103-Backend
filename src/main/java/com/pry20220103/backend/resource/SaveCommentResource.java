package com.pry20220103.backend.resource;

import java.util.Date;

import javax.validation.constraints.*;

import lombok.Data;

@Data
public class SaveCommentResource {



    @NotBlank
    @NotNull
    private String content;

    @NotBlank
    @NotNull
    private String type;

    
    
}
