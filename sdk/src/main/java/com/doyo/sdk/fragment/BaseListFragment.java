package com.doyo.sdk.fragment;

import android.view.View;

import com.doyo.sdk.R;
import com.doyo.sdk.mvp.BaseSimplePresenter;
import com.doyo.sdk.mvp.IBaseListView;


/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 永无bug集团
 *     time   : 2019/05/20
 *     desc   : 网络请求加载状态view的fragment，主要用于显示加载中、空界面、加载失败等状态界面显示
 *
 * </pre>
 */

public abstract class BaseListFragment<T extends BaseSimplePresenter> extends BaseNetFragment<T> implements IBaseListView {


    @Override
    protected void lazyFetchData() {
        super.lazyFetchData();
        View.inflate(_mActivity, R.layout.empty_view, parent);
        mEmptyiew = parent.findViewById(R.id.empty_group);
        mEmptyiew.setOnClickListener(v -> reload());
        mEmptyiew.setVisibility(View.GONE);
    }

    protected abstract void reload();


    @Override
    public void showEmpty() {

        if (currentState == EMPTY_STATE) {
            return;
        }

        hideCurrentView();
        currentState = EMPTY_STATE;
        mEmptyiew.setVisibility(View.VISIBLE);
    }


}
