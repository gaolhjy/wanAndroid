package com.glh.wanandroid.presenter.contract;

import com.doyo.sdk.mvp.AbstractPresenter;
import com.doyo.sdk.mvp.IBaseListView;
import com.doyo.sdk.mvp.ResBaseListBean;
import com.glh.wanandroid.bean.FeedArticleData;
import com.glh.wanandroid.bean.FeedArticleListData;


/**
 * @author quchao
 * @date 2017/12/7
 */

public interface MyCollectContract {

    interface View extends IBaseListView<ResBaseListBean> {

        /**
         * Show cancel collect article data
         *
         * @param position Position
         * @param feedArticleData FeedArticleData
         * @param feedArticleListData FeedArticleListData
         */
        void showCancelCollectArticleData(int position, FeedArticleData feedArticleData,
                                          FeedArticleListData feedArticleListData);


    }

    interface Presenter extends AbstractPresenter<View> {


        /**
         * Get feed article list
         *
         * @param isShowError If show error
         */
        void getData(int page, boolean isShowError);


        /**
         * Cancel collect article
         *
         * @param position Position
         * @param feedArticleData FeedArticleData
         */
        void cancelCollectArticle(int position, FeedArticleData feedArticleData);


    }

}
