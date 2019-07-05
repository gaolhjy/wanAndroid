package com.doyo.sdk.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 永无bug集团
 *     time   : 2019/05/17
 *     desc   : fragmnet最原始的基类
 *
 * </pre>
 */

public abstract class AbstractSimpleFragment extends Fragment {


    /**
     * 表示是否初始化完毕
     */
    private boolean isViewPrepared;

    /**
     * 是否正在懒加载
     */
    private boolean hasFetchData;

    protected View     mView;
    private   Unbinder mUnbinder;
    protected Context  mContext;
    protected Activity _mActivity;

    /**
     * 加载布局
     *
     * @return 返回int类型的布局
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 初始化视图
     */
    protected abstract void initViews();

    /**
     * 懒加载数据
     */
    protected abstract void lazyFetchData();


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
        this._mActivity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), container, false);
        mUnbinder = ButterKnife.bind(this, mView);
        initViews();
        return mView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewPrepared = true;
        lazyFetchDataIfPrepared();
    }


    /**
     * 此方法仅仅在 Viewpager方法中才会调用！
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            lazyFetchDataIfPrepared();
        }
    }

    private void lazyFetchDataIfPrepared() {
        if (getUserVisibleHint() && !hasFetchData && isViewPrepared) {
            hasFetchData = true;
            lazyFetchData();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        hasFetchData = false;
        isViewPrepared = false;
    }

    @Override
    public void onDestroy() {

        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
            mUnbinder = null;
        }

        super.onDestroy();
    }

}

