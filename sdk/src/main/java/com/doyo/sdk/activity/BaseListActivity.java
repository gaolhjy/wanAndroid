package com.doyo.sdk.activity;

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
 *     desc   : 列表类型的activity. 除了有父类的加载中  加载错误  正常外,还有空数据情况时的情况
 *
 * </pre>
 */

public abstract class BaseListActivity<T extends BaseSimplePresenter> extends BaseNetActivity<T> implements IBaseListView {

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
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
