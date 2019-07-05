package com.glh.wanandroid.presenter.contract;


import com.doyo.sdk.mvp.AbstractPresenter;
import com.doyo.sdk.mvp.AbstractView;

/**
 * @author quchao
 * @date 2018/2/26
 */

public interface LoginContract {

    interface View extends AbstractView {

        /**
         * Show loading
         */
        void showLoading();

        /**
         * Show error
         */
        void showError();

        /**
         * Show login data
         */
        void showLoginSuccess();

        void showErrorMes(String msg);
    }

    interface Presenter extends AbstractPresenter<View> {

        /**
         * Get Login data
         *
         * @param username user name
         * @param password password
         */
        void getLoginData(String username, String password);
    }
}
