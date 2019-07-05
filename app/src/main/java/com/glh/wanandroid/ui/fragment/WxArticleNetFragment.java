package com.glh.wanandroid.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.doyo.sdk.fragment.BaseListFragment;
import com.doyo.sdk.mvp.AbstractPresenter;
import com.doyo.sdk.utils.NetUtils;
import com.glh.wanandroid.R;
import com.glh.wanandroid.bean.FeedArticleData;
import com.glh.wanandroid.bean.WxArticleListData;
import com.glh.wanandroid.constant.Constants;
import com.glh.wanandroid.core.DataManager;
import com.glh.wanandroid.core.http.ApiFactory;
import com.glh.wanandroid.core.http.HttpHelper;
import com.glh.wanandroid.core.http.HttpHelperImpl;
import com.glh.wanandroid.core.prefs.PreferenceHelper;
import com.glh.wanandroid.core.prefs.PreferenceHelperImpl;
import com.glh.wanandroid.presenter.WxArticlePresenter;
import com.glh.wanandroid.presenter.contract.WxArticleContract;
import com.glh.wanandroid.ui.adapter.ArticleListAdapter;
import com.hjq.toast.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 永无bug集团
 *     time   : 2019/5/24 16:46
 *     desc   : 微信公众号二级栏目 fragment
 *
 * </pre>
 */
public class WxArticleNetFragment extends BaseListFragment<WxArticlePresenter> implements WxArticleContract.View, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recycler_view)
    RecyclerView       mRecyclerView;
    @BindView(R.id.normal_view)
    SmartRefreshLayout mRefreshLayout;


    private List<FeedArticleData> mFeedArticleDataList;
    private ArticleListAdapter    mAdapter;
    private int                   currentPage;
    private String                cid;
    private boolean               isRefresh = true;

    public static WxArticleNetFragment getInstance(String param1, String param2) {
        WxArticleNetFragment fragment = new WxArticleNetFragment();
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
        mAdapter = new ArticleListAdapter(R.layout.item_search_pager, mFeedArticleDataList);
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
            cid = bundle.getString(Constants.ARG_PARAM1, null);
        } else {
            cid = "1";
        }

        setRefresh();

        currentPage = 1;
        mPresenter.getWxArticleData(currentPage, cid, true);

        if (NetUtils.isNetworkConnected()) {
            showLoading();
        }

    }


    private void setRefresh() {

        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            currentPage = 1;
            isRefresh = true;
            mPresenter.getWxArticleData(currentPage, cid, false);
            refreshLayout.finishRefresh(1000);
        });
    }


    @Override
    public void showProject(WxArticleListData data) {

        if (mAdapter == null) {
            return;
        }

        mRecyclerView.setVisibility(View.VISIBLE);

        if (isRefresh) {
            mAdapter.replaceData(data.datas);
        } else {
            if (data.datas.size() > 0) {
                mAdapter.addData(data.datas);
            } else {
                ToastUtils.show(getString(R.string.load_more_no_data));
            }
        }
        showNormal();
    }

    @Override
    protected AbstractPresenter initPresenter() {
        PreferenceHelper mPreferenceHelper = new PreferenceHelperImpl();
        HttpHelper mHttpHelper = new HttpHelperImpl(ApiFactory.getApiService());
        DataManager dataManager = new DataManager(mHttpHelper, mPreferenceHelper);
        mPresenter = new WxArticlePresenter(dataManager, this);
        return mPresenter;
    }

    @Override
    public void reload() {
        currentPage = 1;
        isRefresh = true;
        mPresenter.getWxArticleData(currentPage, cid, false);
    }

    @Override
    public void onLoadMoreRequested() {
        currentPage++;
        isRefresh = false;
        mAdapter.loadMoreComplete();
        mPresenter.getWxArticleData(currentPage, cid, false);
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
