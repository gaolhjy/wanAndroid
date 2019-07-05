package com.glh.wanandroid.presenter.contract;

import com.doyo.sdk.mvp.AbstractPresenter;
import com.doyo.sdk.mvp.AbstractView;
import com.glh.wanandroid.bean.ProjectData;

import java.util.List;

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

public interface ProjectContract {


    interface View extends AbstractView {

        /**
         * Show content
         */
        void showProject(List<ProjectData> projectDataList);

    }

    interface Presenter extends AbstractPresenter<ProjectContract.View> {


        /**
         * Get feed article list
         *
         * @param isShowError If show error
         */
        void getProjectData(boolean isShowError);


    }
}
