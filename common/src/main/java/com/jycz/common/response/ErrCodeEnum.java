package com.jycz.common.response;

/**
 * @author ling
 * @data 2020/4/7 17:10
 */

public enum ErrCodeEnum implements ErrInfoInter {
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

    @Override
    public int getErrCode() {
        return errCode;
    }

    @Override
    public String getErrMsg() {
        return errMsg;
    }

    @Override
    public ErrInfoInter setErrMsg(String msg) {
        this.errMsg = errMsg;
        return this;
    }

}