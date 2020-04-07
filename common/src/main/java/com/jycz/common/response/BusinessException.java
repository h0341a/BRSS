package com.jycz.common.response;

import lombok.Getter;

/**
 * @author ling
 * @data 2020/4/7 17:24
 */
@Getter
public class BusinessException extends Exception {
    public ErrCodeEnum errCodeEnum;

    public BusinessException(ErrCodeEnum codeEnum) {
        this.errCodeEnum = codeEnum;
    }

    public BusinessException(ErrCodeEnum errCodeEnum, String customMsg){
        errCodeEnum.setErrMsg(customMsg);
        this.errCodeEnum = errCodeEnum;
    }
}
