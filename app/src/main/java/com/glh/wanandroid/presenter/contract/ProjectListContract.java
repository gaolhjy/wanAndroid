package com.glh.wanandroid.presenter.contract;

import com.doyo.sdk.mvp.AbstractPresenter;
import com.doyo.sdk.mvp.IBaseListView;
import com.glh.wanandroid.bean.ProjectListData;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 磊华集团
 *     time   : 2019/05/23
 *     desc   : 项目
 *
 * </pre>
 */

public interface ProjectListContract {


    interface View extends IBaseListView {

        /**
         * Show content
         */
        void showProject(ProjectListData projectListData);

    }

    interface Presenter extends AbstractPresenter<ProjectListContract.View> {


        /**
         * Get feed article list
         *
         * @param isShowError If show error
         */
        void getProjectData(int pager, String cid, boolean isShowError);


    }
}
