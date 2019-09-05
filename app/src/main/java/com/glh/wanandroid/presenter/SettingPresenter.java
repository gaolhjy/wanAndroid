package com.glh.wanandroid.presenter;

import com.glh.wanandroid.BasePresenter;
import com.glh.wanandroid.core.DataManager;
import com.glh.wanandroid.presenter.contract.SettingContract;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 银江集团
 *     time   : 2019/7/16 15:45
 *     desc   :
 *
 * </pre>
 */
public class SettingPresenter extends BasePresenter<SettingContract.View> implements SettingContract.Presenter {

    public SettingPresenter(DataManager dataManager, SettingContract.View view) {
        super(dataManager, view);
    }

    @Override
    public boolean getNoImageState() {
        return mDataManager.getNoImageState();
    }

    @Override
    public void setNoImageState(boolean b) {
        mDataManager.setNoImageState(b);
    }
}
