package com.jycz.common.model.entity;

import io.swagger.models.auth.In;

public class UserRelation {
    private Integer uid;

    private Integer idolId;

    private Integer status;

    private Integer ugid;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getIdolId() {
        return idolId;
    }

    public void setIdolId(Integer idolId) {
        this.idolId = idolId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUgid() {
        return ugid;
    }

    public void setUgid(Integer ugid) {
        this.ugid = ugid;
    }
}