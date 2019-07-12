package com.glh.wanandroid.presenter.contract;

import com.doyo.sdk.mvp.AbstractPresenter;
import com.doyo.sdk.mvp.IBaseListView2;
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

    interface View extends IBaseListView2<ResBaseListBean>{

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

        /**
         * Show content
         *
         * @param feedArticleListData FeedArticleListData
         */
        void showArticleList(FeedArticleListData feedArticleListData);

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

        /**
         * Show banner data
         *
         * @param bannerDataList List<BannerData>
         */
        void showBannerData(List<BannerData> bannerDataList);


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
         * Get feed article list
         *
         * @param isShowError If show error
         */
        void getFeedArticleList(boolean isShowError);

        /**
         * Add collect article
         *
         * @param position        Position
         * @param feedArticleData FeedArticleData
         */
        void addCollectArticle(int position, FeedArticleData feedArticleData);

        /**
         * Cancel collect article
         *
         * @param position        Position
         * @param feedArticleData FeedArticleData
         */
        void cancelCollectArticle(int position, FeedArticleData feedArticleData);

        /**
         * Get banner data
         *
         * @param isShowError If show error
         */
        void getBannerData(boolean isShowError);


    }

}
