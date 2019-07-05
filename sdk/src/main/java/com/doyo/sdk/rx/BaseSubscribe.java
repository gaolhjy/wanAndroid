package com.doyo.sdk.rx;

import android.text.TextUtils;

import com.doyo.sdk.R;
import com.doyo.sdk.global.GlobalApplication;
import com.doyo.sdk.http.exception.ServerException;
import com.doyo.sdk.mvp.IBaseNetView;

import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.HttpException;

/**
 * @author quchao
 * @date 2018/4/2
 */

public abstract class BaseSubscribe <T> extends ResourceSubscriber<T> {

    private IBaseNetView mView;
    private String       mErrorMsg;
    private boolean      isShowError = true;

    protected BaseSubscribe(IBaseNetView view){
        this.mView = view;
    }

    protected BaseSubscribe(IBaseNetView view, String errorMsg){
        this.mView = view;
        this.mErrorMsg = errorMsg;
    }

    protected BaseSubscribe(IBaseNetView view, boolean isShowError){
        this.mView = view;
        this.isShowError = isShowError;
    }

    protected BaseSubscribe(IBaseNetView view, String errorMsg, boolean isShowError){
        this.mView = view;
        this.mErrorMsg = errorMsg;
        this.isShowError = isShowError;
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        if (mView == null) {
            return;
        }
        if (mErrorMsg != null && !TextUtils.isEmpty(mErrorMsg)) {
            mView.showErrorMsg(mErrorMsg);
        } else if (e instanceof ServerException) {
            mView.showErrorMsg(e.toString());
        } else if (e instanceof HttpException) {
            mView.showErrorMsg(GlobalApplication.getInstance().getString(R.string.http_error));
        } else {
            mView.showErrorMsg(GlobalApplication.getInstance().getString(R.string.unKnown_error));
        }

        if (isShowError) {
            mView.showError();
        }

    }
}
