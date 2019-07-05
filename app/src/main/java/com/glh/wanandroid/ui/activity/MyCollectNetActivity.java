package com.glh.wanandroid.ui.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.doyo.sdk.activity.BaseListActivity;
import com.doyo.sdk.mvp.AbstractPresenter;
import com.doyo.sdk.utils.NetUtils;
import com.doyo.sdk.utils.UiUtils;
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
import com.glh.wanandroid.presenter.MyCollectPresenter;
import com.glh.wanandroid.presenter.contract.MyCollectContract;
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
 *     time   : 2019/05/20
 *     desc   : 收藏
 *
 * </pre>
 */

public class MyCollectNetActivity extends BaseListActivity<MyCollectPresenter> implements MyCollectContract.View {

    @BindView(R.id.collect_recycler_view)
    RecyclerView       mRecyclerView;
    @BindView(R.id.normal_view)
    SmartRefreshLayout mRefreshLayout;

    private boolean               isRefresh = true;
    private int                   mCurrentPage;
    private List<FeedArticleData> mArticles;
    private ArticleListAdapter    mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mycollect;
    }


    @Override
    protected void initEventAndData() {
        mPresenter.getFeedArticleList(mCurrentPage, true);
        initRecyclerView();
        setRefresh();

        if (NetUtils.isNetworkConnected()) {
            showLoading();
        }
    }


    private void setRefresh() {
        mRefreshLayout.setPrimaryColorsId(Constants.BLUE_THEME, R.color.white);
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            showRefreshEvent();
            refreshLayout.finishRefresh(1000);
        });

        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mCurrentPage++;
            isRefresh = false;
            mPresenter.getFeedArticleList(mCurrentPage, false);
            refreshLayout.finishLoadMore(1000);
        });
    }

    private void initRecyclerView() {
        mArticles = new ArrayList<>();
        mAdapter = new ArticleListAdapter(R.layout.item_search_pager, mArticles);
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            clickChildEvent(view, position);
        });
        mAdapter.isCollectPage();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void clickChildEvent(View view, int position) {
        switch (view.getId()) {
            case R.id.item_search_pager_chapterName:
                //                startSingleChapterKnowledgePager(position);
                break;
            case R.id.item_search_pager_like_iv:
                cancelCollect(position);
                break;
            default:
                break;
        }
    }

    private void cancelCollect(int position) {

        if (mAdapter.getData().size() <= 0 || mAdapter.getData().size() <= position) {
            return;
        }

        mPresenter.cancelCollectArticle(position, mAdapter.getData().get(position));
    }


    @Override
    public void showArticleList(FeedArticleListData feedArticleListData) {

        if (mAdapter == null) {
            return;
        }
        mArticles = feedArticleListData.datas;
        if (isRefresh) {
            mAdapter.replaceData(mArticles);
        } else {
            showLoadMore(feedArticleListData);
        }
        if (mAdapter.getData().size() == 0) {
            UiUtils.showSnackMessage(this, getString(R.string.no_collect));
        }

        showNormal();

    }

    private void showLoadMore(FeedArticleListData feedArticleListData) {

        if (mArticles.size() > 0) {
            mArticles.addAll(feedArticleListData.datas);
            mAdapter.addData(feedArticleListData.datas);
        } else {
            if (mAdapter.getData().size() != 0) {
                ToastUtils.show(getString(R.string.load_more_no_data));
            }
        }
    }

    @Override
    public void showCancelCollectArticleData(int position, FeedArticleData feedArticleData,
                                             FeedArticleListData feedArticleListData) {

        mAdapter.remove(position);
        UiUtils.showSnackMessage(this, getString(R.string.cancel_collect_success));

    }

    @Override
    public void showRefreshEvent() {
        mCurrentPage = 0;
        isRefresh = true;
        mPresenter.getFeedArticleList(mCurrentPage, false);
    }

    @NonNull
    @Override
    public AbstractPresenter initPresenter() {
        PreferenceHelper mPreferenceHelper = new PreferenceHelperImpl();
        HttpHelper mHttpHelper = new HttpHelperImpl(ApiFactory.getApiService());
        DataManager manager = new DataManager(mHttpHelper, mPreferenceHelper);
        mPresenter = new MyCollectPresenter(manager, this);
        return mPresenter;
    }


    @Override
    public void reload() {

    }

    @Override
    public void showLoadMoreError() {

    }

    @Override
    public void showNoMoreData() {

    }
}
