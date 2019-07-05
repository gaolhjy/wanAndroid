package com.doyo.sdk.mvp;


/**
 * <pre>
 *     author   : 高磊华
 *     time     : 2019/05/17
 *     company  : 磊华集团
 *     desc     : 返回值的基类
 * </pre>
 */

public class ResBaseBean<T> {

    public static final int SUCCESS     = 0;
    public static final int FAIL        = 1;
    public static final int ERROR_TOKEN = 2;

    /**
     * 0：成功，1：失败
     */
    private int errorCode;

    private String errorMsg;

    private T data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
