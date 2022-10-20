package com.pry20220103.backend.resource;

import lombok.Data;

import java.util.Date;

@Data
public class UsageLogResource {
    private Long id;
    private String modelName;
    private String modelDescription;
    private String grade;
    private Boolean animated;
    private Integer viewCount;
    private Date createdAt;
    private String roleName;
}
