package com.glh.wanandroid.ui.activity;

import android.support.annotation.NonNull;
import android.view.View;

import com.doyo.sdk.activity.BaseListActivityEx;
import com.doyo.sdk.adapter.BaseCompatAdapter;
import com.doyo.sdk.mvp.AbstractPresenter;
import com.doyo.sdk.utils.UiUtils;
import com.glh.wanandroid.R;
import com.glh.wanandroid.bean.FeedArticleData;
import com.glh.wanandroid.bean.FeedArticleListData;
import com.glh.wanandroid.presenter.MyCollectPresenter;
import com.glh.wanandroid.presenter.contract.MyCollectContract;
import com.glh.wanandroid.ui.adapter.ArticleListAdapter;
import com.glh.wanandroid.utils.MvpUtils;

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

public class MyCollectActivity extends BaseListActivityEx<MyCollectPresenter, ArticleListAdapter> implements MyCollectContract.View {


    @Override
    protected BaseCompatAdapter getAbstractAdapter() {
        mAdapter = new ArticleListAdapter(R.layout.item_search_pager, null);
        mAdapter.isCollectPage();
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            clickChildEvent(view, position);
        });

        return mAdapter;
    }

    @Override
    protected void getInitData() {
        firstPager = 0;
        mHeaderBar.setTitle("我的收藏");
    }


    private void clickChildEvent(View view, int position) {
        switch (view.getId()) {
            case R.id.item_search_pager_chapterName:
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
    public void showCancelCollectArticleData(int position, FeedArticleData feedArticleData,
                                             FeedArticleListData feedArticleListData) {
        mAdapter.remove(position);
        UiUtils.showSnackMessage(this, getString(R.string.cancel_collect_success));

        if (mAdapter.getData().size() == 0) {
            showEmpty();
        }

    }


    @NonNull
    @Override
    public AbstractPresenter initPresenter() {
        mPresenter = new MyCollectPresenter(MvpUtils.initDataManager(), this);
        return mPresenter;
    }


    @Override
    protected void getData(int curPage, boolean isShow, String id) {
        mPresenter.getData(curPage, isShow);
    }
}
