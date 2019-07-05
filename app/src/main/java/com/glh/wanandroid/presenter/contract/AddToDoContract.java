package com.glh.wanandroid.presenter.contract;

import com.doyo.sdk.mvp.AbstractPresenter;
import com.doyo.sdk.mvp.AbstractView;
import com.glh.wanandroid.bean.TodoDesData;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 永无bug集团
 *     time   : 2019/07/02
 *     desc   : 新增TODO事项
 *
 * </pre>
 */

public interface AddToDoContract {


    interface View extends AbstractView {

        /**
         * Show content
         */
        void showData(TodoDesData data);

    }

    interface Presenter extends AbstractPresenter<AddToDoContract.View> {

        void addTodoData(String title, String content,
                         String date, int type, boolean isShowError);

    }
}
