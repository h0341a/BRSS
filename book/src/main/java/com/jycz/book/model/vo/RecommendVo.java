package com.jycz.book.model.vo;

import lombok.Data;

@Data
public class RecommendVo {
    private Integer uid;
    private Integer bid;
    private Integer rid;
    private String nickname;
    private String content;
    private String coverUrl;
    private String bookName;

}
