package com.pry20220103.backend.resource;

import java.util.Date;

import lombok.Data;

@Data
public class CommentResource {
   
    private Long id;
    private String content;
    private String type;
    private String nameCommenter;
    private Date postDate;
}
