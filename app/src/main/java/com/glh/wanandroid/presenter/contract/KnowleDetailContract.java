package com.glh.wanandroid.presenter.contract;

import com.doyo.sdk.mvp.AbstractPresenter;
import com.doyo.sdk.mvp.IBaseNetView;
import com.glh.wanandroid.bean.FeedArticleListData;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 永无bug集团
 *     time   : 2019/05/21
 *     desc   :
 *
 * </pre>
 */

public interface KnowleDetailContract {


    interface View extends IBaseNetView {

        /**
         * Show content
         */
        void showFeedArticleList(FeedArticleListData feedArticleListData);

    }

    interface Presenter extends AbstractPresenter<KnowleDetailContract.View> {


        /**
         * Get feed article list
         *
         * @param isShowError If show error
         */
        void getFeedArticleList(int pager, String cid, boolean isShowError);


    }
}
