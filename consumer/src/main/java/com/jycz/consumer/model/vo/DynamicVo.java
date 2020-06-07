package com.jycz.consumer.model.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class DynamicVo {
    /**
     * 动态类型，大概有发起的和取消的点赞,发起的和取消的关注，发起的评论，
     */
    private String type;
    /**
     * 这只是一个描述,举例：取消了对用户B的点赞
     */
    private String describe;
    /**
     * 一些动态可能需要uid,rid，比如指向某个用户的或者指向某个推荐的
     */
    private String necessaryData;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date showDate;

    public DynamicVo(String type, String describe, String necessaryData, Date showDate) {
        this.type = type;
        this.describe = describe;
        this.necessaryData = necessaryData;
        this.showDate = showDate;
    }
}
