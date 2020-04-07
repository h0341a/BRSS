package com.jycz.common.response;

/**
 * @author ling
 * @data 2020/4/7 17:53
 */
public interface ErrInfoInter {
    /**
     * 获取错误码
     * @return code
     */
    public int getErrCode();

    /**
     * 获取错误信息
     * @return 消息内容
     */
    public String getErrMsg();
    /**
     * 设置错误消息
     * @param msg 消息内容
     * @return 枚举类本身
     */
    public ErrInfoInter setErrMsg(String msg);
}
