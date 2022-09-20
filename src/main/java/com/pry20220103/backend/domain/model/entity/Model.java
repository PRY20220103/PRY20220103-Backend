package com.pry20220103.backend.domain.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "models")
@Data
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank
    @NotNull
    String modelName;

    @NotBlank
    @NotNull
    String  modelDescription;

    @NotBlank
    @NotNull
    String grade;

    Boolean animated;

    Integer viewCount;

    Date createdAt;
}
