package com.glh.wanandroid.presenter.contract;

import com.doyo.sdk.mvp.AbstractPresenter;
import com.doyo.sdk.mvp.IBaseListView;
import com.glh.wanandroid.bean.BannerData;
import com.glh.wanandroid.bean.FeedArticleData;
import com.glh.wanandroid.bean.FeedArticleListData;

import java.util.List;


/**
 * @author quchao
 * @date 2017/12/7
 */

public interface HomeContract {

    interface View extends IBaseListView {

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
         * @param isRefresh           If refresh
         */
        void showArticleList(FeedArticleListData feedArticleListData, boolean isRefresh);

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
         * Load more data
         */
        void loadMoreData();

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

        /**
         * Auto refresh
         *
         * @param isShowError If show error
         */
        void autoRefresh(boolean isShowError);

        /**
         * Load more
         */
        void loadMore();

    }

}
