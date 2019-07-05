package com.glh.wanandroid.presenter.contract;

import com.doyo.sdk.mvp.AbstractPresenter;
import com.doyo.sdk.mvp.IBaseListView;
import com.glh.wanandroid.bean.KnowleData;

import java.util.List;

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

public interface KnowleContract {


    interface View extends IBaseListView {

        /**
         * Show content
         */
        void showKnowHierarchyList(List<KnowleData> knowledgeHierarchyData,
                                   boolean isRefresh);

    }

    interface Presenter extends AbstractPresenter<KnowleContract.View> {


        /**
         * Get feed article list
         *
         * @param isShowError If show error
         */
        void getKnowHierarchyList(boolean isShowError);


    }
}
