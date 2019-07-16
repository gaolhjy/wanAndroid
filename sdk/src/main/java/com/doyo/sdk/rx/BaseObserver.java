package com.doyo.sdk.rx;

import android.text.TextUtils;

import com.doyo.sdk.R;
import com.doyo.sdk.global.GlobalApplication;
import com.doyo.sdk.http.exception.OtherException;
import com.doyo.sdk.http.exception.ServerException;
import com.doyo.sdk.mvp.AbstractView;
import com.doyo.sdk.mvp.IBaseListView;
import com.doyo.sdk.mvp.IBaseNetView;

import io.reactivex.observers.ResourceObserver;
import retrofit2.HttpException;

/**
 * @param <T>
 * @author quchao
 * @date 2017/11/27
 */

public abstract class BaseObserver<T> extends ResourceObserver<T> {

    private AbstractView mView;
    private String       mErrorMsg;
    private boolean      isShowError = true;

    protected BaseObserver(AbstractView view) {
        this(view, null, true);
    }

    protected BaseObserver(AbstractView view, String errorMsg) {
        this(view, errorMsg, true);
    }

    protected BaseObserver(AbstractView view, boolean isShowError) {
        this(view, null, isShowError);
    }

    protected BaseObserver(AbstractView view, String errorMsg, boolean isShowError) {
        this.mView = view;
        this.mErrorMsg = errorMsg;
        this.isShowError = isShowError;
    }


    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {

        e.printStackTrace();

        if (mView == null) {
            return;
        }


        if (mErrorMsg != null && !TextUtils.isEmpty(mErrorMsg)) {
            mView.showErrorMsg(mErrorMsg);
        } else if (e instanceof OtherException) {
            if (((OtherException) e).getCode() == 500) {
                //异地登录
            }
        } else if (e instanceof ServerException) {
            mView.showErrorMsg(e.toString());
        } else if (e instanceof HttpException) {
            mView.showErrorMsg(GlobalApplication.getInstance().getString(R.string.http_error));
        } else {
            mView.showErrorMsg(GlobalApplication.getInstance().getString(R.string.unKnown_error));
        }


        if (isShowError) {
            if (mView instanceof IBaseNetView) {
                ((IBaseNetView) mView).showError();
            }
        }

        if (mView instanceof IBaseListView) {
            ((IBaseListView) mView).showLoadMoreError();
        }
    }

}
