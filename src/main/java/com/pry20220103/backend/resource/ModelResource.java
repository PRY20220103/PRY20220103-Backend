package com.pry20220103.backend.resource;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ModelResource {
    private String modelName;
    private String modelDescription;
    private String grade;
    private String color;
    private Boolean animated;
    private Integer viewCount;
    private Date createdAt;
    private String modelUrl;
    private String thumbnail;
    private List<String> questions;
    private List<String> answers;
}
