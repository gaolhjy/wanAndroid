package com.glh.wanandroid.presenter;

import com.doyo.sdk.http.cookies.CookiesManager;
import com.doyo.sdk.rx.BaseObserver;
import com.doyo.sdk.rx.RxBus;
import com.glh.wanandroid.BasePresenter;
import com.glh.wanandroid.R;
import com.glh.wanandroid.bean.LoginData;
import com.glh.wanandroid.core.DataManager;
import com.glh.wanandroid.core.event.LoginEvent;
import com.glh.wanandroid.global.AppContext;
import com.glh.wanandroid.presenter.contract.MainContract;
import com.glh.wanandroid.utils.RxUtils;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 永无bug集团
 *     time   : 2019/05/14
 *     desc   : 主activity的presenter类
 *
 * </pre>
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    DataManager       mDataManager;
    MainContract.View mView;

    public MainPresenter(DataManager dataManager, MainContract.View mView) {
        super(dataManager);
        this.mDataManager = dataManager;
        this.mView=mView;
    }

    @Override
    public void attachView(MainContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

        addSubscribe(RxBus.getDefault().toFlowable(LoginEvent.class)
                .filter(LoginEvent::isLogin)
                .subscribe(loginEvent -> mView.showLoginView()));

        addSubscribe(RxBus.getDefault().toFlowable(LoginEvent.class)
                .filter(loginEvent -> !loginEvent.isLogin())
                .subscribe(logoutEvent -> mView.showLogoutView()));
    }

    @Override
    public void setCurrentPage(int page) {

    }

    @Override
    public void setNightModeState(boolean b) {

    }

    @Override
    public void logout() {

        addSubscribe(mDataManager.loginOut()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleLogoutResult())
                .subscribeWith(new BaseObserver<LoginData>(mView,
                        AppContext.getInstance().getString(R.string.logout_fail)) {

                    @Override
                    public void onNext(LoginData loginData) {
                        setLoginAccount("");
                        setLoginPassword("");
                        setLoginStatus(false);
                        CookiesManager.clearAllCookies();
                        RxBus.getDefault().post(new LoginEvent(false));
                        mView.showLogoutSuccess();
                    }
                })
        );
    }
}
