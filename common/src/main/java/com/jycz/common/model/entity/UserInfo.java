package com.jycz.common.model.entity;

import java.util.Date;

public class UserInfo {
    private Integer uid;

    private String bio;

    private String avatarUrl;

    private Integer fans;

    private Integer idols;

    private Integer recommends;

    private Integer collections;

    private Date registerDate;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio == null ? null : bio.trim();
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }

    public Integer getFans() {
        return fans;
    }

    public void setFans(Integer fans) {
        this.fans = fans;
    }

    public Integer getIdols() {
        return idols;
    }

    public void setIdols(Integer idols) {
        this.idols = idols;
    }

    public Integer getRecommends() {
        return recommends;
    }

    public void setRecommends(Integer recommends) {
        this.recommends = recommends;
    }

    public Integer getCollections() {
        return collections;
    }

    public void setCollections(Integer collections) {
        this.collections = collections;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}