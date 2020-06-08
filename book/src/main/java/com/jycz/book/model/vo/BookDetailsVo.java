package com.jycz.book.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class BookDetailsVo {
    private Integer id;
    private String coverUrl;
    private String name;
    private String author;
    private List<RecommendItemVo> recommendVoList;
}

