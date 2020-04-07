package jycz.brss.model.entity;

public class UserRelation {
    private Integer uid;

    private Integer idolId;

    private Boolean status;

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