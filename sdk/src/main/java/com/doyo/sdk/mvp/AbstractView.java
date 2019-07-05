package com.doyo.sdk.mvp;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 永无bug集团
 *     time   : 2019/05/17
 *     desc   : view基类
 *
 * </pre>
 */

public interface AbstractView {

    /**
     * Use night mode
     *
     * @param isNightMode if is night mode
     */
    void useNightMode(boolean isNightMode);


    /**
     * Show error message
     *
     * @param errorMsg error message
     */
    void showErrorMsg(String errorMsg);


    /**
     * Show toast
     *
     * @param message Message
     */
    void showToast(String message);


}
