package com.doyo.sdk.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.doyo.sdk.R;
import com.doyo.sdk.adapter.BaseCompatAdapter;
import com.doyo.sdk.mvp.BaseSimplePresenter;
import com.doyo.sdk.utils.NetUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;


/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 永无bug集团
 *     time   : 2019/05/20
 *     desc   :
 *
 * </pre>
 */

public abstract class BaseListFragment3<P extends BaseSimplePresenter,
        A extends BaseCompatAdapter> extends BaseListFragment<P> implements
        BaseQuickAdapter.RequestLoadMoreListener {

    protected RecyclerView       mRecyclerView;
    protected SmartRefreshLayout mRefreshLayout;

    protected A       mAdapter;
    protected P       mPresenter;
    protected int     currentPage;
    protected String  id;
    protected boolean isRefresh = true;


    @Override
    protected int getLayoutId() {
        return R.layout.base_recyclerview;
    }


    @Override
    protected void initViews() {
        mRecyclerView = mView.findViewById(R.id.recycler_view);
        mRefreshLayout = mView.findViewById(R.id.normal_view);
        mAdapter = (A) getAbstractAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
    }

    protected abstract BaseCompatAdapter getAbstractAdapter();


    @Override
    protected void lazyFetchData() {
        super.lazyFetchData();

        getInitData();
        setRefresh();

        currentPage = 1;
        getData(currentPage, true, id);

        if (NetUtils.isNetworkConnected()) {
            showLoading();
        }

    }

    protected abstract void getInitData();


    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            currentPage = 1;
            isRefresh = true;
            getData(currentPage, false, id);
            refreshLayout.finishRefresh(1000);
        });
    }

    @Override
    public void reload() {
        currentPage = 1;
        isRefresh = true;
        getData(currentPage, false, id);
    }

    @Override
    public void onLoadMoreRequested() {
        currentPage++;
        isRefresh = false;
        getData(currentPage, false, id);
    }

    protected abstract void getData(int currentPage, boolean isShow, String id);

    @Override
    public void showLoadMoreError() {
        mAdapter.closeLoadAnimation();
        mAdapter.loadMoreFail();
    }


    @Override
    public void showNoMoreData() {
        mAdapter.loadMoreEnd();
    }

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
