package com.glh.wanandroid.ui.activity;

import com.doyo.sdk.activity.BaseNetActivity;
import com.doyo.sdk.mvp.AbstractPresenter;
import com.glh.wanandroid.R;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 磊华集团
 *     time   : 2019/6/18 16:19
 *     desc   : 测试界面
 *
 * </pre>
 */
public class TestActivity extends BaseNetActivity {

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        showNormal();
    }

    @Override
    protected void reload() {

    }

    @Override
    protected AbstractPresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }
}
