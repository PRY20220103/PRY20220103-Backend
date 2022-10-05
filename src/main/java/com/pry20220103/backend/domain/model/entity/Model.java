package com.pry20220103.backend.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

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
    String modelDescription;

    @NotBlank
    @NotNull
    String grade;

    @NotBlank
    @NotNull
    String color;

    String thumbnail;

    Boolean animated;

    Integer viewCount;

    Date createdAt;

    @NotBlank
    @NotNull
    String modelUrl;

    @ElementCollection
    List<String> questions;

    @ElementCollection
    List<String> answers;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "model_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Category category;
}
