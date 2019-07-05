package com.doyo.sdk.mvp;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 银江集团
 *     time   : 2019/7/5 17:54
 *     desc   : 涉及网络请求的baseView
 *
 * </pre>
 */
public interface IBaseNetView extends AbstractView {

    /**
     * Show loading
     */
    void showLoading();

    /**
     * showNormal
     */
    void showNormal();


    /**
     * Show error
     */
    void showError();


}
