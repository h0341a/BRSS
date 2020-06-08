package com.jycz.common.model.entity;

import java.util.Date;

public class UserMessage {
    private Integer sendUid;

    private Integer acceptUid;

    private String content;

    private Date sendDate;

    private Byte sendStatus;

    private Byte readStatus;

    public UserMessage() {
    }

    public UserMessage(Integer sendUid, Integer acceptUid, String content) {
        this.sendUid = sendUid;
        this.acceptUid = acceptUid;
        this.content = content;
    }

    public Integer getSendUid() {
        return sendUid;
    }

    public void setSendUid(Integer sendUid) {
        this.sendUid = sendUid;
    }

    public Integer getAcceptUid() {
        return acceptUid;
    }

    public void setAcceptUid(Integer acceptUid) {
        this.acceptUid = acceptUid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Byte getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Byte sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Byte getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(Byte readStatus) {
        this.readStatus = readStatus;
    }
}