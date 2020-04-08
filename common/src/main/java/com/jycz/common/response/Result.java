package com.jycz.common.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ling
 * @data 2020/4/7 17:01
 */
@Data
public class Result implements Serializable {
    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 服务器当前timestamp
     */
    private Long timestamp = System.currentTimeMillis();
    /**
     * 成功时返回的数据
     */
    private Object data;
    /**
     *失败时返回的错误码
     */
    private int errCode;
    /**
     *失败时返回的错误码
     */
    private String errMsg;
    public static Result ofSuccess(Object data){
        Result result = new Result();
        result.success = true;
        result.data = data;
        return result;
    }

    public static Result ofFail(ErrInfoInter errInfoInter){
        Result result = new Result();
        result.success=false;
        result.setErrCode(errInfoInter.getErrCode());
        result.setErrMsg(errInfoInter.getErrMsg());
        return result;
    }

    /**
     * 符合该错误码的错误提供可自定义的提示信息
     * @param errInfoInter 错误码
     * @param errorMsg 自定义的错误信息
     * @return result
     */
    public static Result ofFail(ErrInfoInter errInfoInter, String errorMsg){
        Result result = new Result();
        result.success=false;
        result.setErrCode(errInfoInter.getErrCode());
        result.setErrMsg(errorMsg);
        return result;
    }
}
