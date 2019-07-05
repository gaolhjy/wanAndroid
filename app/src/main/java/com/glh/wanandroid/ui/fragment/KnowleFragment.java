package com.glh.wanandroid.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.doyo.sdk.fragment.BaseNetFragment;
import com.doyo.sdk.mvp.AbstractPresenter;
import com.doyo.sdk.utils.JumpUtils;
import com.doyo.sdk.utils.NetUtils;
import com.glh.wanandroid.R;
import com.glh.wanandroid.bean.KnowleData;
import com.glh.wanandroid.constant.Constants;
import com.glh.wanandroid.core.DataManager;
import com.glh.wanandroid.core.http.ApiFactory;
import com.glh.wanandroid.core.http.HttpHelper;
import com.glh.wanandroid.core.http.HttpHelperImpl;
import com.glh.wanandroid.core.prefs.PreferenceHelper;
import com.glh.wanandroid.core.prefs.PreferenceHelperImpl;
import com.glh.wanandroid.presenter.KnowlePresenter;
import com.glh.wanandroid.presenter.contract.KnowleContract;
import com.glh.wanandroid.ui.activity.KnowleDetailActivity;
import com.glh.wanandroid.ui.adapter.KnowledgeHierarchyListAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 永无bug集团
 *     time   : 2019/05/09
 *     desc   :  首页底部-----知识体系fragment
 *
 * </pre>
 */

public class KnowleFragment extends BaseNetFragment<KnowlePresenter>
        implements KnowleContract.View, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recycler_view)
    RecyclerView       mRecyclerView;
    @BindView(R.id.normal_view)
    SmartRefreshLayout mRefreshLayout;

    private List<KnowleData>              mKnowledgeHierarchyDataList;
    private KnowledgeHierarchyListAdapter mAdapter;


    public static KnowleFragment getInstance(String param1, String param2) {
        KnowleFragment fragment = new KnowleFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_PARAM1, param1);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledgehierarchy;
    }

    @Override
    protected void initViews() {
        initRecyclerView();
    }

    @Override
    protected void lazyFetchData() {
        super.lazyFetchData();
        setRefresh();
        mPresenter.autoRefresh(true);
        if (NetUtils.isNetworkConnected()) {
            showLoading();
        }
    }

    private void setRefresh() {

        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPresenter.autoRefresh(false);
            refreshLayout.finishRefresh(1000);
        });
    }


    private void initRecyclerView() {
        mKnowledgeHierarchyDataList = new ArrayList<>();
        mAdapter = new KnowledgeHierarchyListAdapter(R.layout.item_knowledge_hierarchy,
                mKnowledgeHierarchyDataList);
        mAdapter.setOnItemClickListener((adapter, view, position) -> startDetailPager(view,
                position));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
    }

    private void startDetailPager(View view, int position) {
        if (mAdapter.getData().size() <= 0 || mAdapter.getData().size() <= position) {
            return;
        }

        KnowleData data = mAdapter.getData().get(position);
        JumpUtils.JumpToActivity(_mActivity, KnowleDetailActivity.class, data);

    }


    @Override
    public void showKnowHierarchyList(List<KnowleData> dataList, boolean isRefresh) {

        mRecyclerView.setVisibility(View.VISIBLE);

        if (mAdapter == null) {
            return;
        }

        if (isRefresh) {
            mAdapter.replaceData(dataList);
        } else {
            mAdapter.addData(dataList);
        }

        showNormal();

    }


    @Override
    protected AbstractPresenter initPresenter() {
        PreferenceHelper mPreferenceHelper = new PreferenceHelperImpl();
        HttpHelper mHttpHelper = new HttpHelperImpl(ApiFactory.getApiService());
        DataManager dataManager = new DataManager(mHttpHelper, mPreferenceHelper);
        mPresenter = new KnowlePresenter(dataManager, this);
        return mPresenter;
    }

    @Override
    public void reload() {
        mPresenter.autoRefresh(false);
    }

    @Override
    public void onLoadMoreRequested() {
        mAdapter.loadMoreEnd();
    }
}
