package com.jycz.common.response;

import lombok.Getter;

/**
 * @author ling
 * @data 2020/4/7 17:10
 */
@Getter
public enum ErrCodeEnum {
    /**
     *
     */
    UNKNOWN_ERROR(1, "未考虑到的错误");
    private int errCode;
    private String errMsg;

    ErrCodeEnum(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
