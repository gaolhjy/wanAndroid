package com.doyo.sdk.http.exception;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 永无bug集团
 *     time   : 2019/6/20 09:09
 *     desc   : 其他异常信息
 *
 * </pre>
 */

public class OtherException extends Exception {

    private int code;

    public OtherException(int errorCode) {
        this.code=errorCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
