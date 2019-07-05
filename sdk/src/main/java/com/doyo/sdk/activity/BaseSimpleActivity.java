package com.doyo.sdk.activity;

import android.support.v7.app.AppCompatDelegate;

import com.doyo.sdk.utils.UiUtils;
import com.doyo.sdk.mvp.AbstractView;


/**
 * <pre>
 *     author   : 高磊华
 *     time     : 2019/05/17
 *     company  : 永无bug集团
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
    public void showToast(String message) {
        UiUtils.showMessage(this, message);
    }

}
