package com.pry20220103.backend.resource;

import com.pry20220103.backend.domain.model.enumeration.Status;

import lombok.Data;

@Data
public class RequestResource {
    private Long id;
    private String grade;
    private String course;
    private String requestedModelName;
    private String description;
    private String requestedBy;
    private Status status;

}
