package com.glh.wanandroid.presenter.contract;

import com.doyo.sdk.mvp.AbstractPresenter;
import com.doyo.sdk.mvp.IBaseListView;
import com.glh.wanandroid.bean.WxArticleListData;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 磊华集团
 *     time   : 2019/05/23
 *     desc   : 微信公众号文章 二级栏目
 *
 * </pre>
 */

public interface WxArticleContract {


    interface View extends IBaseListView {

        /**
         * Show content
         */
        void showProject(WxArticleListData data);

    }

    interface Presenter extends AbstractPresenter<WxArticleContract.View> {


        /**
         * Get feed article list
         *
         * @param isShowError If show error
         */
        void getWxArticleData(int pager, String cid, boolean isShowError);


    }
}
