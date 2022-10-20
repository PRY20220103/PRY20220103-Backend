package com.pry20220103.backend.resource;

import lombok.Data;

import java.util.Date;

@Data
public class UsageLogResource {
    private Long id;
    private String roleName;
    private Date viewedAt;
    private String modelGrade;
}
