package com.doyo.sdk.activity;

import android.view.View;

import com.doyo.sdk.adapter.BaseCompatAdapter;
import com.doyo.sdk.mvp.BaseSimplePresenter;
import com.doyo.sdk.mvp.ResBaseListBean;


/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 永无bug集团
 *     time   : 2019/05/20
 *     desc   : data.datas 数据的列表类
 *
 * </pre>
 */

public abstract class BaseListActivityEx<P extends BaseSimplePresenter,
        A extends BaseCompatAdapter> extends BaseListActivity<P, A, ResBaseListBean> {

    @Override
    public void showData(ResBaseListBean data) {

        if (mAdapter == null) {
            return;
        }

        mRecyclerView.setVisibility(View.VISIBLE);

        if (isRefresh) {
            mAdapter.setEnableLoadMore(true);
            mAdapter.setNewData(data.datas);
        } else {
            mAdapter.loadMoreComplete();
            mAdapter.addData(data.datas);
        }

        showNormal();

    }

}
