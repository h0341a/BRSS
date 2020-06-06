package com.jycz.book.model.vo;

import lombok.Data;

import java.util.Date;

@Data
public class BookVo {
    private Integer id;
    private String coverUrl;
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
    /**
     * 收藏时间
     */
    private Date collectionDate;
}
