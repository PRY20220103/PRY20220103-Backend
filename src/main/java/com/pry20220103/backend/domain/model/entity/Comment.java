package com.pry20220103.backend.domain.model.entity;

import java.util.Date;

import javax.persistence.*;
import javax.persistence.Table;
import javax.validation.constraints.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "comments")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank
    @NotNull
    String content;

    @NotBlank
    @NotNull
    String type;


    @NotBlank
    @NotNull
    String nameCommenter;

    @NotNull
    Date postDate;



    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "comment_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Model model;
    

}
