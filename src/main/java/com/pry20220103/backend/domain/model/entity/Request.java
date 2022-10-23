package com.pry20220103.backend.domain.model.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pry20220103.backend.domain.model.enumeration.Status;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;

@Entity
@Table(name = "request")
@Data
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank
    @NotNull
    String  grade;

    @NotBlank
    @NotNull
    String  course;

    @NotBlank
    @NotNull
    String  requestModelName;

    @NotBlank
    @NotNull
    String  description;

    @NotBlank
    @NotNull
    String  requestedBy;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;
}
