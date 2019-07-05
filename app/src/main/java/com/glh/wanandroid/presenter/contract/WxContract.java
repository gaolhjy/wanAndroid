package com.glh.wanandroid.presenter.contract;

import com.doyo.sdk.mvp.AbstractPresenter;
import com.doyo.sdk.mvp.AbstractView;
import com.glh.wanandroid.bean.WxNameListData;

import java.util.List;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 磊华集团
 *     time   : 2019/05/24
 *     desc   : 公众号
 *
 * </pre>
 */

public interface WxContract {


    interface View extends AbstractView {

        /**
         * Show content
         */
        void showWx(List<WxNameListData> projectDataList);

    }

    interface Presenter extends AbstractPresenter<WxContract.View> {


        /**
         * Get feed article list
         *
         * @param isShowError If show error
         */
        void getWxData(boolean isShowError);


    }
}
