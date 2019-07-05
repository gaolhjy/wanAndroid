package com.doyo.sdk.fragment;

import com.doyo.sdk.mvp.AbstractView;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 永无bug集团
 *     time   : 2019/05/17
 *     desc   : fragmnet基类
 *
 * </pre>
 */

public abstract class BaseSimpleFragment extends AbstractSimpleFragment implements AbstractView {

    @Override
    public void useNightMode(boolean isNightMode) {
    }

    @Override
    public void showErrorMsg(String errorMsg) {
    }


    @Override
    public void showToast(String message) {

    }


}

