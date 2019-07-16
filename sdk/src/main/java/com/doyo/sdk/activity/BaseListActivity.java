package com.doyo.sdk.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.doyo.sdk.R;
import com.doyo.sdk.adapter.BaseCompatAdapter;
import com.doyo.sdk.mvp.BaseSimplePresenter;
import com.doyo.sdk.mvp.IBaseListView;
import com.doyo.sdk.utils.NetUtils;
import com.doyo.sdk.widget.HeaderBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;


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

public abstract class BaseListActivity<P extends BaseSimplePresenter,
        A extends BaseCompatAdapter, D> extends BaseNetActivity<P> implements
        BaseQuickAdapter.RequestLoadMoreListener, IBaseListView<D> {

    protected RecyclerView       mRecyclerView;
    protected SmartRefreshLayout mRefreshLayout;
    protected HeaderBar          mHeaderBar;

    protected A       mAdapter;
    protected P       mPresenter;
    protected int     curPage;
    protected String  id;
    protected boolean isRefresh  = true;
    /**
     * 是否有加载更多.默认是有的
     */
    protected boolean isHavaMore = true;

    /**
     * 分页,有的是从0开始,有的又是从1开始的.
     * 默认从1开始
     */
    protected int firstPager = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.base_activity_recyclerview;
    }

    @Override
    protected void initEventAndData() {

        super.initEventAndData();

        View.inflate(_mActivity, R.layout.empty_view, parent);
        mEmptyiew = parent.findViewById(R.id.empty_group);
        mEmptyiew.setOnClickListener(v -> reload());
        mEmptyiew.setVisibility(View.GONE);

        initViews();

        getInitData();
        setRefresh();

        curPage = firstPager;
        getData(curPage, true, id);

        if (NetUtils.isNetworkConnected()) {
            showLoading();
        }
    }

    private void initViews() {

        mHeaderBar = findViewById(R.id.header);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRefreshLayout = findViewById(R.id.normal_view);

        mAdapter = (A) getAbstractAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        if (isHavaMore) {
            mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        }

    }

    protected abstract BaseCompatAdapter getAbstractAdapter();

    protected abstract void getInitData();

    protected void setRefresh() {

        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            curPage = firstPager;
            isRefresh = true;
            getData(curPage, false, id);
            refreshLayout.finishRefresh(1000);
        });
    }

    @Override
    protected void reload() {
        curPage = firstPager;
        isRefresh = true;
        getData(curPage, false, id);
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

    @Override
    public void onLoadMoreRequested() {
        curPage++;
        isRefresh = false;
        getData(curPage, false, id);
    }

    protected abstract void getData(int curPage, boolean isShow, String id);

    @Override
    public void showLoadMoreError() {
        mAdapter.closeLoadAnimation();
        mAdapter.loadMoreFail();
    }


    @Override
    public void showNoMoreData() {
        if (isRefresh) {
            showEmpty();
        } else {
            mAdapter.loadMoreEnd();
        }
    }

}
