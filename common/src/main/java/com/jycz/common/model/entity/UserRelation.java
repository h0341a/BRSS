package com.jycz.common.model.entity;

import lombok.ToString;

@ToString
public class UserRelation {
    private Integer sourceId;

    private Integer targetId;

    private Boolean status;


    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


    public UserRelation() {
    }

    public UserRelation(Integer sourceId, Integer targetId, Boolean status) {
        this.sourceId = sourceId;
        this.targetId = targetId;
        this.status = status;
    }
}