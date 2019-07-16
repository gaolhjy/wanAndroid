package com.glh.wanandroid.presenter.contract;

import com.doyo.sdk.mvp.AbstractPresenter;
import com.doyo.sdk.mvp.AbstractView;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 银江集团
 *     time   : 2019/7/16 15:46
 *     desc   :
 *
 * </pre>
 */
public interface SettingContract {

    interface View extends AbstractView {

    }

    interface Presenter extends AbstractPresenter<View> {

        boolean getNoImageState();

        void setNoImageState(boolean b);
    }
}
