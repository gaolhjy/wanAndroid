package com.glh.wanandroid;

import com.doyo.sdk.mvp.BaseSimplePresenter;
import com.doyo.sdk.mvp.AbstractView;
import com.glh.wanandroid.core.DataManager;


/**
 * Base Presenter
 * 管理事件流订阅的生命周期
 *
 * @author quchao
 * @date 2017/11/28
 */

public class BasePresenter<T extends AbstractView> extends BaseSimplePresenter<T> {

    protected T           mView;
    private   DataManager mDataManager;

    public BasePresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    public BasePresenter(DataManager dataManager, T view) {
        mView = view;
        mDataManager = dataManager;
    }


    @Override
    public boolean getNightModeState() {
        return mDataManager.getNightModeState();
    }

    @Override
    public void setLoginStatus(boolean loginStatus) {
        mDataManager.setLoginStatus(loginStatus);
    }

    @Override
    public boolean getLoginStatus() {
        return mDataManager.getLoginStatus();
    }

    @Override
    public String getLoginAccount() {
        return mDataManager.getLoginAccount();
    }

    @Override
    public void setLoginAccount(String account) {
        mDataManager.setLoginAccount(account);
    }

    @Override
    public void setLoginPassword(String password) {
        mDataManager.setLoginPassword(password);
    }

    @Override
    public int getCurrentPage() {
        return mDataManager.getCurrentPage();
    }


}
