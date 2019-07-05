package com.glh.wanandroid.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.doyo.sdk.fragment.BaseListFragment;
import com.doyo.sdk.mvp.AbstractPresenter;
import com.doyo.sdk.utils.NetUtils;
import com.glh.wanandroid.R;
import com.glh.wanandroid.bean.FeedArticleData;
import com.glh.wanandroid.bean.FeedArticleListData;
import com.glh.wanandroid.constant.Constants;
import com.glh.wanandroid.core.DataManager;
import com.glh.wanandroid.core.http.ApiFactory;
import com.glh.wanandroid.core.http.HttpHelper;
import com.glh.wanandroid.core.http.HttpHelperImpl;
import com.glh.wanandroid.core.prefs.PreferenceHelper;
import com.glh.wanandroid.core.prefs.PreferenceHelperImpl;
import com.glh.wanandroid.presenter.KnowleDetailPresenter;
import com.glh.wanandroid.presenter.contract.KnowleDetailContract;
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
 *     time   : 2019/05/22
 *     desc   : 知识体系 二级页面fragment
 *
 * </pre>
 */

public class KnowleNetFragment extends BaseListFragment<KnowleDetailPresenter>
        implements KnowleDetailContract.View {


    @BindView(R.id.recycler_view)
    RecyclerView       mRecyclerView;
    @BindView(R.id.normal_view)
    SmartRefreshLayout mRefreshLayout;


    private List<FeedArticleData> mFeedArticleDataList;
    private ArticleListAdapter    mAdapter;
    private int                   currentPage;
    private String                id;
    private boolean               isRefresh = true;

    public static KnowleNetFragment getInstance(String id, String param2) {
        KnowleNetFragment fragment = new KnowleNetFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_PARAM1, id);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowle_detail;
    }

    @Override
    protected void initViews() {
        initRecyclerView();
    }

    private void initRecyclerView() {

        mFeedArticleDataList = new ArrayList<>();
        mAdapter = new ArticleListAdapter(R.layout.item_search_pager, mFeedArticleDataList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
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

        currentPage = 0;
        mPresenter.getFeedArticleList(currentPage, id, true);

        if (NetUtils.isNetworkConnected()) {
            showLoading();
        }
    }


    @Override
    public void reload() {
        currentPage = 0;
        isRefresh = true;
        mPresenter.getFeedArticleList(currentPage, id, false);
    }

    private void setRefresh() {

        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            currentPage = 0;
            isRefresh = true;
            mPresenter.getFeedArticleList(currentPage, id, false);
            refreshLayout.finishRefresh(1000);
        });

        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            currentPage++;
            isRefresh = false;
            mPresenter.getFeedArticleList(currentPage, id, false);
            refreshLayout.finishLoadMore(1000);
        });
    }




    @Override
    public void showFeedArticleList(FeedArticleListData feedArticleListData) {

        mRecyclerView.setVisibility(View.VISIBLE);

        if (isRefresh) {
            mAdapter.replaceData(feedArticleListData.datas);
        } else {
            if (feedArticleListData.datas.size() > 0) {
                mAdapter.addData(feedArticleListData.datas);
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
        mPresenter = new KnowleDetailPresenter(dataManager, this);
        return mPresenter;
    }

    @Override
    public void showLoadMoreError() {

    }

    @Override
    public void showNoMoreData() {

    }
}
