package com.jycz.book.model.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CommentVo {
    private Integer id;
    private Integer uid;
    private String avatarUrl;
    private String content;
    private Date commentDate;
}
