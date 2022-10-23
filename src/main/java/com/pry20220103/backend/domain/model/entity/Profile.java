package com.pry20220103.backend.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;



import lombok.Data;

@Entity
@Table(name = "profiles")
@Data
public class Profile {

    @Id
    @Column(name = "user_id")
    Long id;

    //@NotBlank
    //@NotNull
    @Size(max = 100)
    private String name;

    //@NotBlank
    //@NotNull
    @Size(max = 100)
    private String surname;

    private String profileImage;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}
