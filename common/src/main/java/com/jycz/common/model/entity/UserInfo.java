package com.jycz.common.model.entity;

import java.util.Date;

public class UserInfo {
    private Integer uid;

    private String bio;

    private String avatarUrl;

    private Integer fansNumber;

    private Integer idolNumber;

    private Integer readBooks;

    private Integer unreadBooks;

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

    public Integer getFansNumber() {
        return fansNumber;
    }

    public void setFansNumber(Integer fansNumber) {
        this.fansNumber = fansNumber;
    }

    public Integer getIdolNumber() {
        return idolNumber;
    }

    public void setIdolNumber(Integer idolNumber) {
        this.idolNumber = idolNumber;
    }

    public Integer getReadBooks() {
        return readBooks;
    }

    public void setReadBooks(Integer readBooks) {
        this.readBooks = readBooks;
    }

    public Integer getUnreadBooks() {
        return unreadBooks;
    }

    public void setUnreadBooks(Integer unreadBooks) {
        this.unreadBooks = unreadBooks;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}