package com.glh.wanandroid.presenter.contract;

import com.doyo.sdk.mvp.AbstractPresenter;
import com.doyo.sdk.mvp.IBaseListView;
import com.glh.wanandroid.bean.ToDoListData;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 永无bug集团
 *     time   : 2019/07/01
 *     desc   : todo中未完成事项  已完成事项
 *
 * </pre>
 */

public interface ToDoContract {


    interface View extends IBaseListView {

        /**
         * Show content
         */
        void showData(ToDoListData data);

        void deleteData(int position);

        void changeData(int position);

    }

    interface Presenter extends AbstractPresenter<ToDoContract.View> {

        void getTodoData(int pager, int type, boolean isShowError);

        void getCompleData(int pager, int type, boolean isShowError);

        void deleteData(String id, int position);

        void changeData(String id, int status, int position);


    }
}
