package com.jycz.book.model.vo;

import lombok.Data;

import java.util.Date;

@Data
public class RecommendItemVo {
    private Integer rid;
    private Date recommendDate;
    private Integer uid;
    private String title;
    private String content;
    private String avatarUrl;
}
