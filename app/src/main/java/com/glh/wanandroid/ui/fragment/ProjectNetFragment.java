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
import com.glh.wanandroid.bean.FeedArticleData;
import com.glh.wanandroid.bean.ProjectListData;
import com.glh.wanandroid.constant.Constants;
import com.glh.wanandroid.core.DataManager;
import com.glh.wanandroid.core.http.ApiFactory;
import com.glh.wanandroid.core.http.HttpHelper;
import com.glh.wanandroid.core.http.HttpHelperImpl;
import com.glh.wanandroid.core.prefs.PreferenceHelper;
import com.glh.wanandroid.core.prefs.PreferenceHelperImpl;
import com.glh.wanandroid.presenter.ProjectListPresenter;
import com.glh.wanandroid.presenter.contract.ProjectListContract;
import com.glh.wanandroid.ui.activity.ArticleDetailActivity;
import com.glh.wanandroid.ui.adapter.ProjectListAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 磊华集团
 *     time   : 2019/5/23 16:46
 *     desc   : 项目二级子fragment.   当前页从第1页开始
 *
 * </pre>
 */
public class ProjectNetFragment extends BaseNetFragment<ProjectListPresenter> implements ProjectListContract.View, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recycler_view)
    RecyclerView       mRecyclerView;
    @BindView(R.id.normal_view)
    SmartRefreshLayout mRefreshLayout;


    private List<FeedArticleData> mFeedArticleDataList;
    private ProjectListAdapter    mAdapter;
    private int                   currentPage;
    private String                id;
    private boolean               isRefresh = true;

    public static ProjectNetFragment getInstance(String param1, String param2) {
        ProjectNetFragment fragment = new ProjectNetFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_PARAM1, param1);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project_list;
    }

    @Override
    protected void initViews() {
        initRecyclerView();
    }

    private void initRecyclerView() {

        mFeedArticleDataList = new ArrayList<>();
        mAdapter = new ProjectListAdapter(R.layout.item_project_list, mFeedArticleDataList);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            FeedArticleData data = (FeedArticleData) adapter.getData().get(position);
            JumpUtils.JumpToActivity(_mActivity, ArticleDetailActivity.class, data);
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
    }

    @Override
    protected void lazyFetchData() {
        super.lazyFetchData();

        Bundle bundle = getArguments();
        if (bundle != null) {
            id = bundle.getString(Constants.ARG_PARAM1, null);
        } else {
            id = "1";
        }

        setRefresh();

        currentPage = 1;
        mPresenter.getProjectData(currentPage, id, true);

        if (NetUtils.isNetworkConnected()) {
            showLoading();
        }

    }


    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            currentPage = 1;
            isRefresh = true;
            mPresenter.getProjectData(currentPage, id, false);
            refreshLayout.finishRefresh(1000);
        });

    }


    @Override
    public void showProject(ProjectListData projectListData) {

        if (mAdapter == null) {
            return;
        }

        mRecyclerView.setVisibility(View.VISIBLE);

        if (isRefresh) {
            mAdapter.setEnableLoadMore(true);
            mAdapter.setNewData(projectListData.datas);
        } else {
            mAdapter.loadMoreComplete();
            mAdapter.addData(projectListData.datas);
        }
        showNormal();
    }

    @Override
    protected AbstractPresenter initPresenter() {
        PreferenceHelper mPreferenceHelper = new PreferenceHelperImpl();
        HttpHelper mHttpHelper = new HttpHelperImpl(ApiFactory.getApiService());
        DataManager dataManager = new DataManager(mHttpHelper, mPreferenceHelper);
        mPresenter = new ProjectListPresenter(dataManager, this);
        return mPresenter;
    }

    @Override
    public void reload() {
        currentPage = 1;
        isRefresh = true;
        mPresenter.getProjectData(currentPage, id, false);
    }

    @Override
    public void onLoadMoreRequested() {
        currentPage++;
        isRefresh = false;
        mPresenter.getProjectData(currentPage, id, false);
    }

    @Override
    public void showLoadMoreError() {
        mAdapter.closeLoadAnimation();
        mAdapter.loadMoreFail();
    }


    @Override
    public void showNoMoreData() {
        mAdapter.loadMoreEnd();
    }
}
