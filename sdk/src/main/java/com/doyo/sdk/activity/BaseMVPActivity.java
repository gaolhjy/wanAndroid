package com.doyo.sdk.activity;

import com.doyo.sdk.mvp.AbstractPresenter;
import com.doyo.sdk.mvp.AbstractView;

import io.reactivex.disposables.Disposable;


/**
 * <pre>
 *     author   : 高磊华
 *     time     : 2019/05/17
 *     company  : 磊华集团
 *     desc     : activity基类
 * </pre>
 */

public abstract class BaseMVPActivity<T extends AbstractPresenter> extends BaseSimpleActivity
        implements AbstractView {

    /**
     * presenter 具体的presenter由子类确定
     */
    protected T mPresenter;

    protected Disposable disposable;

    @Override
    protected void onViewCreated() {
        mPresenter = (T) initPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    protected abstract AbstractPresenter initPresenter();


    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        unsubscribe();
        super.onDestroy();
    }


    protected void unsubscribe() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
