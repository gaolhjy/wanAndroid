package com.glh.wanandroid.presenter;

import android.text.TextUtils;

import com.doyo.sdk.rx.BaseObserver;
import com.doyo.sdk.rx.RxBus;
import com.glh.wanandroid.BasePresenter;
import com.glh.wanandroid.R;
import com.glh.wanandroid.bean.LoginData;
import com.glh.wanandroid.core.DataManager;
import com.glh.wanandroid.core.event.LoginEvent;
import com.glh.wanandroid.global.AppContext;
import com.glh.wanandroid.presenter.contract.LoginContract;
import com.glh.wanandroid.utils.RxUtils;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 永无bug集团
 *     time   : 2019/05/14
 *     desc   : 登录
 *
 * </pre>
 */

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    DataManager        dataManager;
    LoginContract.View mView;

    public LoginPresenter(DataManager dataManager, LoginContract.View view) {
        super(dataManager);
        this.dataManager = dataManager;
        this.mView = view;
    }

    @Override
    public void getLoginData(String username, String password) {

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            mView.showErrorMes(AppContext.getInstance().getString(R.string.account_password_null_tint));
            return;
        }

        addSubscribe(dataManager.login(username, password)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<LoginData>(mView,
                        AppContext.getInstance().getString(R.string.login_fail)) {
                    @Override
                    public void onNext(LoginData loginData) {
                        setLoginAccount(loginData.username);
                        setLoginPassword(loginData.password);
                        setLoginStatus(true);
                        RxBus.getDefault().post(new LoginEvent(true));
                        mView.showLoginSuccess();
                    }
                }));
    }
}
