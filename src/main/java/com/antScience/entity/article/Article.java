package com.antScience.entity.article;

import lombok.Data;

import java.util.Date;

/**
 * Created by lirui on 2017/12/30.
 */
@Data
public class Article {
    private String id;
    private String userName;

    private String title;
    private String label;
    private String content;

    private String status;
    private Date creationTime;
    private Date updatedTime;
}
