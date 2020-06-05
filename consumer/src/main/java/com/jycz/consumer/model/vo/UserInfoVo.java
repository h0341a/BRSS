package com.jycz.consumer.model.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfoVo {
    private String bio;
    private String email;
    private String nickname;
    private String avatarUrl;
    private Integer fans;
    private Integer idols;
    private Integer recommends;
    private Integer collections;
    private Date registerDate;
}
