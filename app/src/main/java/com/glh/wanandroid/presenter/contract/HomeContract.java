package com.glh.wanandroid.presenter.contract;

import com.doyo.sdk.mvp.AbstractPresenter;
import com.doyo.sdk.mvp.IBaseListView;
import com.doyo.sdk.mvp.ResBaseListBean;
import com.glh.wanandroid.bean.BannerData;
import com.glh.wanandroid.bean.FeedArticleData;
import com.glh.wanandroid.bean.FeedArticleListData;

import java.util.List;


/**
 * @author quchao
 * @date 2017/12/7
 */

public interface HomeContract {

    interface View extends IBaseListView<ResBaseListBean> {

        /**
         * Show auto login success
         */
        void showAutoLoginSuccess();

        /**
         * Show auto login fail
         */
        void showAutoLoginFail();


        /**
         * Show login view
         */
        void showLoginView();

        /**
         * Show logout view
         */
        void showLogoutView();



        void showBannerData(List<BannerData> bannerDataList);

        /**
         * Show collect article data
         *
         * @param position            Position
         * @param feedArticleData     FeedArticleData
         * @param feedArticleListData FeedArticleListData
         */
        void showCollectArticleData(int position, FeedArticleData feedArticleData,
                                    FeedArticleListData feedArticleListData);

        /**
         * Show cancel collect article data
         *
         * @param position            Position
         * @param feedArticleData     FeedArticleData
         * @param feedArticleListData FeedArticleListData
         */
        void showCancelCollectArticleData(int position, FeedArticleData feedArticleData,
                                          FeedArticleListData feedArticleListData);


        void showCollectSuccess();


        void showCancelCollectSuccess();


    }

    interface Presenter extends AbstractPresenter<View> {

        /**
         * Get login password
         *
         * @return login password
         */
        String getLoginPassword();

        /**
         * Load main pager data
         */
        void loadMainPagerData();

        /**
         * 获取文章列表数据
         */
        void getFeedArticleList(int pager, boolean isShowError);


        /**
         * 获取轮播图数据
         */
        void getBannerData(boolean isShowError);


        /**
         * 收藏文章
         */
        void addCollectArticle(int position, FeedArticleData feedArticleData);


        /**
         * 取消收藏文章
         */
        void cancelCollectArticle(int position, FeedArticleData feedArticleData);


    }

}
