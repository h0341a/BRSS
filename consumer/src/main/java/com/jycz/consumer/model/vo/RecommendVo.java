package com.jycz.consumer.model.vo;

import lombok.Data;

import java.util.Date;

@Data
public class RecommendVo {
    private String coverUrl;
    private String bookName;
    private String author;
    private String title;
    private String content;
    private Boolean status;
    private int comments;
    private int stars;
    private int collects;
    private Date recommendDate;
}
