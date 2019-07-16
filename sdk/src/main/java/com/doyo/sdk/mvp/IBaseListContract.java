package com.doyo.sdk.mvp;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 永无bug集团
 *     time   : 2019/05/23
 *     desc   : 项目
 *
 * </pre>
 */

public interface IBaseListContract {

    interface Presenter extends AbstractPresenter<IBaseListView> {


        /**
         * Get feed article list
         *
         * @param isShowError If show error
         */
        void getData(int pager, String cid, boolean isShowError);

    }
}
