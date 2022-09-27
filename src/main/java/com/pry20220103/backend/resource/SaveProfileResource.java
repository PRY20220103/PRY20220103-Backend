package com.pry20220103.backend.resource;

import javax.validation.constraints.*;

import lombok.Data;

@Data
public class SaveProfileResource {
    
   // @NotBlank(message = "email cannot be blank")
    //@NotNull(message = "email cannot be null")
    @Size(max = 100)
    private String name;

    //@NotBlank(message = "email cannot be blank")
    //@NotNull(message = "email cannot be null")
    @Size(max = 100)
    private String surname;


    private String profileImage;
}
