package com.doyo.sdk.activity;

import android.support.v7.app.AppCompatDelegate;

import com.doyo.sdk.utils.UiUtils;
import com.doyo.sdk.mvp.AbstractView;


/**
 * <pre>
 *     author   : 高磊华
 *     time     : 2019/05/17
 *     company  : 磊华集团
 *     desc     : 没有p层 activity基类
 * </pre>
 */

public abstract class BaseSimpleActivity extends AbstractSimpleActivity
        implements AbstractView {


    @Override
    public void useNightMode(boolean isNight) {
        if (isNight) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
        }
        recreate();
    }

    @Override
    public void showErrorMsg(String errorMsg) {
        UiUtils.showSnackMessage(this, errorMsg);
    }

    @Override
    public void showNormal() {

    }

    @Override
    public void showError() {

    }


    @Override
    public void showLoginView() {

    }

    @Override
    public void showLogoutView() {

    }

    @Override
    public void showToast(String message) {
        UiUtils.showMessage(this, message);
    }

    @Override
    public void showSnackBar(String message) {
        UiUtils.showSnackMessage(this, message);
    }

}
