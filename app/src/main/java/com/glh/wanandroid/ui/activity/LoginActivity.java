package com.glh.wanandroid.ui.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.doyo.sdk.activity.BaseMVPActivity;
import com.doyo.sdk.mvp.AbstractPresenter;
import com.glh.wanandroid.R;
import com.glh.wanandroid.presenter.LoginPresenter;
import com.glh.wanandroid.presenter.contract.LoginContract;
import com.glh.wanandroid.utils.MvpUtils;
import com.hjq.toast.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

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

public class LoginActivity extends BaseMVPActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.login_toolbar)
    Toolbar        mLoginToolbar;
    @BindView(R.id.login_tv)
    TextView       mLoginTv;
    @BindView(R.id.login_account_edit)
    EditText       mLoginAccountEdit;
    @BindView(R.id.login_account_group)
    LinearLayout   mLoginAccountGroup;
    @BindView(R.id.login_divider)
    View           mLoginDivider;
    @BindView(R.id.login_password_edit)
    EditText       mLoginPasswordEdit;
    @BindView(R.id.login_password_group)
    LinearLayout   mLoginPasswordGroup;
    @BindView(R.id.register_divider)
    View           mRegisterDivider;
    @BindView(R.id.login_btn)
    Button         mLoginBtn;
    @BindView(R.id.login_or_tv)
    TextView       mLoginOrTv;
    @BindView(R.id.login_register_btn)
    Button         mLoginRegisterBtn;
    @BindView(R.id.login_group)
    RelativeLayout mLoginGroup;


    @Override
    protected void initEventAndData() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }


    @Override
    public void showLoginSuccess() {
        ToastUtils.show(getString(R.string.login_success));
        finish();
    }

    @Override
    public void showErrorMes(String msg) {
        showToast(msg);
    }

    @OnClick(R.id.login_btn)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                String name = mLoginAccountEdit.getText().toString().trim();
                String pwd = mLoginPasswordEdit.getText().toString().trim();
                mPresenter.getLoginData(name, pwd);
                break;
        }
    }

    @NonNull
    @Override
    public AbstractPresenter initPresenter() {
        mPresenter = new LoginPresenter(MvpUtils.initDataManager(), this);
        return mPresenter;
    }

    @Override
    public void showLoading() {

    }


    @Override
    public void showError() {

    }
}
