package com.pry20220103.backend.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "usage_log")
@Data
public class UsageLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Size(max = 20)
    private String roleName;

    private Date viewedAt;

    @NotBlank
    @NotNull
    private String modelGrade;

    //private Course modelCourse;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usage_log_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Model model;
}
