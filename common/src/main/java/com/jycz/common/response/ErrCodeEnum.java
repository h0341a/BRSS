package com.jycz.common.response;

/**
 * @author ling
 * @data 2020/4/7 17:10
 */

public enum ErrCodeEnum implements ErrInfoInter {
    /**
     *
     */
    UNKNOWN_ERROR(1, "未考虑到的错误"),
    PARAMETERS_INVALID(101, "参数非法"),
    PARAMETERS_VALIDATION_FAIL(101, "参数验证失败"),
    NO_AUTHORITY(102, "权限不足"),
    DATA_ABORT(201, "数据出现异常"),
    USER_OPERATION_PUZZLE(301, "用户进行了迷惑的操作");
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
        this.errMsg = msg;
        return this;
    }

}
