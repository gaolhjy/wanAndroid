package com.glh.wanandroid.presenter;

import com.doyo.sdk.rx.BaseObserver;
import com.glh.wanandroid.BasePresenter;
import com.glh.wanandroid.R;
import com.glh.wanandroid.bean.NaviData;
import com.glh.wanandroid.core.DataManager;
import com.glh.wanandroid.global.AppContext;
import com.glh.wanandroid.presenter.contract.NavigationContract;
import com.glh.wanandroid.utils.RxUtils;

import java.util.List;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 永无bug集团
 *     time   : 2019/05/20
 *     desc   : 收藏
 *
 * </pre>
 */

public class NavigationPresenter extends BasePresenter<NavigationContract.View> implements NavigationContract.Presenter {


    private NavigationContract.View mView;


    public NavigationPresenter(DataManager dataManager, NavigationContract.View view) {
        super(dataManager);
        this.mDataManager = dataManager;
        this.mView = view;
    }


    @Override
    public void getNavigationList(boolean isShowError) {

        addSubscribe(mDataManager.getNaviData()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<NaviData>>(mView,
                        AppContext.getInstance().getString(R.string.failed_to_obtain_navigation_list),
                        isShowError) {
                    @Override
                    public void onNext(List<NaviData> navigationDataList) {
                        mView.showNavigationList(navigationDataList);
                    }
                }));

    }
}
