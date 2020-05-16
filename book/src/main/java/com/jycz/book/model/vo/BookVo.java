package com.jycz.book.model.vo;

import lombok.Data;

@Data
public class BookVo {
    private Integer id;
    /**
     * 书籍名称
     */
    private String name;
    /**
     * 作者名
     */
    private String author;
    /**
     * 评分
     */
    private Double rank;
    /**
     * 简述
     */
    private String introduction;
}
