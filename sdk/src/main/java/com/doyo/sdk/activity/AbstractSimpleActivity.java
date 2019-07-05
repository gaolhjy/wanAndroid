package com.doyo.sdk.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.doyo.sdk.global.AppManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * <pre>
 *     author   : 高磊华
 *     time     : 2019/05/17
 *     company  : 磊华集团
 *     desc     : activity最原始的基类
 *      在有butterknife的情况下,一般含有recyclerview的界面,才需要initView.
 * </pre>
 */

public abstract class AbstractSimpleActivity extends AppCompatActivity {

    protected Unbinder mUnbinder;
    protected Context  context;
    protected Activity _mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
        context = this;
        _mActivity = this;
        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);

        onViewCreated();
        initEventAndData();
    }

    
    /**
     * 一定要在初始化数据之前
     */
    protected abstract void onViewCreated();

    protected abstract void initEventAndData();

    protected abstract int getLayoutId();


    @Override
    protected void onDestroy() {
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
            mUnbinder = null;
        }

        // 结束Activity&从堆栈中移除
        AppManager.getAppManager().finishActivity(this);
        super.onDestroy();
    }

}
