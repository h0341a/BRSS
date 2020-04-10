package com.jycz.common.model.entity;

public class UserRelation {
    private Integer sourceId;

    private Integer targetId;

    private Boolean status;

    private Integer ugid;

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

    public Integer getUgid() {
        return ugid;
    }

    public void setUgid(Integer ugid) {
        this.ugid = ugid;
    }
}