package com.jycz.common.response;

import lombok.Getter;

/**
 * @author ling
 * @data 2020/4/7 17:24
 */
@Getter
public class BusinessException extends Exception implements ErrInfoInter {
    public ErrInfoInter errInfoInter;

    public BusinessException(ErrInfoInter errInfoInter) {
        this.errInfoInter = errInfoInter;
    }

    public BusinessException(ErrInfoInter errInfoInter, String customMsg){
        this.errInfoInter = errInfoInter.setErrMsg(customMsg);
    }

    @Override
    public int getErrCode() {
        return this.errInfoInter.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.errInfoInter.getErrMsg();
    }

    @Override
    public ErrInfoInter setErrMsg(String msg) {
        return this.errInfoInter.setErrMsg(msg);
    }
}
