package com.jycz.consumer.model.vo;

import lombok.Data;

import java.util.Date;

@Data
public class MsgVo {
    private Integer sendUid;
    private Boolean myself;
    private Integer acceptUid;

    private String content;

    private Date sendDate;

}
