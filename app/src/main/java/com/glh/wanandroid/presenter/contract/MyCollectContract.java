package com.glh.wanandroid.presenter.contract;

import com.doyo.sdk.mvp.AbstractPresenter;
import com.doyo.sdk.mvp.IBaseNetView;
import com.glh.wanandroid.bean.FeedArticleData;
import com.glh.wanandroid.bean.FeedArticleListData;


/**
 * @author quchao
 * @date 2017/12/7
 */

public interface MyCollectContract {

    interface View extends IBaseNetView {

        /**
         * Show content
         *
         * @param feedArticleListData FeedArticleListData
         */
        void showArticleList(FeedArticleListData feedArticleListData);


        /**
         * Show cancel collect article data
         *
         * @param position Position
         * @param feedArticleData FeedArticleData
         * @param feedArticleListData FeedArticleListData
         */
        void showCancelCollectArticleData(int position, FeedArticleData feedArticleData,
                                          FeedArticleListData feedArticleListData);


        /**
         * Show Refresh event
         */
        void showRefreshEvent();


    }

    interface Presenter extends AbstractPresenter<View> {


        /**
         * Get feed article list
         *
         * @param isShowError If show error
         */
        void getFeedArticleList(int page, boolean isShowError);


        /**
         * Cancel collect article
         *
         * @param position Position
         * @param feedArticleData FeedArticleData
         */
        void cancelCollectArticle(int position, FeedArticleData feedArticleData);


    }

}
