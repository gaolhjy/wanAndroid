package com.doyo.sdk.fragment;

import android.view.View;

import com.doyo.sdk.adapter.BaseCompatAdapter;
import com.doyo.sdk.mvp.BaseSimplePresenter;
import com.doyo.sdk.mvp.IBaseListView2;
import com.doyo.sdk.mvp.ResBaseListBean;


/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 永无bug集团
 *     time   : 2019/05/20
 *     desc   : 后台数据如果遵循 data  datas  就可以直接用这个
 *
 * </pre>
 */

public abstract class BaseListFragment3<P extends BaseSimplePresenter,
        A extends BaseCompatAdapter> extends BaseListFragment2<P, A> implements
        IBaseListView2<ResBaseListBean> {

    @Override
    public void showData(ResBaseListBean datas) {

        if (mAdapter == null) {
            return;
        }

        mRecyclerView.setVisibility(View.VISIBLE);

        if (isRefresh) {
            mAdapter.setEnableLoadMore(true);
            mAdapter.setNewData(datas.datas);
        } else {
            mAdapter.loadMoreComplete();
            mAdapter.addData(datas.datas);
        }

        showNormal();

    }

}
