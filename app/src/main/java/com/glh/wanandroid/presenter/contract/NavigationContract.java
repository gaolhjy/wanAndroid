package com.glh.wanandroid.presenter.contract;

import com.doyo.sdk.mvp.AbstractPresenter;
import com.doyo.sdk.mvp.AbstractView;
import com.glh.wanandroid.bean.NaviData;

import java.util.List;


/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 磊华集团
 *     time   : 2019/05/23
 *     desc   : 导航
 *
 * </pre>
 */

public interface NavigationContract {

    interface View extends AbstractView {

        /**
         * Show content
         */
        void showNavigationList(List<NaviData> naviData);


    }

    interface Presenter extends AbstractPresenter<View> {


        /**
         * Get feed article list
         *
         * @param isShowError If show error
         */
        void getNavigationList(boolean isShowError);


    }

}
