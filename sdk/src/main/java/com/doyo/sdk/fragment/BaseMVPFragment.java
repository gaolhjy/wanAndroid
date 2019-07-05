package com.doyo.sdk.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.doyo.sdk.mvp.AbstractPresenter;
import com.doyo.sdk.mvp.BaseSimplePresenter;
import com.doyo.sdk.mvp.AbstractView;

import io.reactivex.disposables.Disposable;

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

public abstract class BaseMVPFragment<T extends BaseSimplePresenter> extends BaseSimpleFragment implements AbstractView {

    /**
     * presenter 具体的presenter由子类确定
     */
    protected T mPresenter;

    protected Disposable disposable;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mPresenter = (T) initPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        super.onViewCreated(view, savedInstanceState);
    }

    protected abstract AbstractPresenter initPresenter();

    @Override
    public void onDestroy() {

        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroy();
    }
}

