package com.doyo.sdk.activity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.doyo.sdk.R;
import com.doyo.sdk.mvp.BaseSimplePresenter;
import com.doyo.sdk.mvp.IBaseNetView;


/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 永无bug集团
 *     time   : 2019/05/20
 *     desc   : 网络请求加载状态view的activity，主要用于显示加载中、正常、加载失败等状态界面显示
 *
 * </pre>
 */

public abstract class BaseNetActivity<T extends BaseSimplePresenter> extends BaseMVPActivity<T> implements IBaseNetView {

    private static final int NORMAL_STATE  = 0;
    private static final int LOADING_STATE = 1;
    private static final int ERROR_STATE   = 2;
    public static final  int EMPTY_STATE   = 3;


    protected ViewGroup           parent;
    private   LottieAnimationView mLoadingAnimation;
    private   View                mErrorView;
    private   View                mLoadingView;
    private   ViewGroup           mNormalView;
    protected View                mEmptyiew;

    protected int currentState = NORMAL_STATE;


    @Override
    protected void initEventAndData() {
        mNormalView = findViewById(R.id.normal_view);
        if (mNormalView == null) {
            throw new IllegalStateException(
                    "The subclass of RootActivity must contain a View named 'mNormalView'.");
        }
        if (!(mNormalView.getParent() instanceof ViewGroup)) {
            throw new IllegalStateException(
                    "mNormalView's ParentView should be a ViewGroup.");
        }

        parent = (ViewGroup) mNormalView.getParent();
        View.inflate(context, R.layout.loading_view, parent);
        View.inflate(context, R.layout.error_view, parent);
        mLoadingView = parent.findViewById(R.id.loading_group);
        mErrorView = parent.findViewById(R.id.error_group);
        TextView reloadTv = mErrorView.findViewById(R.id.error_reload_tv);
        reloadTv.setOnClickListener(v -> reload());
        mLoadingAnimation = mLoadingView.findViewById(R.id.loading_animation);
        mErrorView.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.GONE);
        mNormalView.setVisibility(View.VISIBLE);
    }

    protected abstract void reload();


    @Override
    protected void onDestroy() {
        if (mLoadingAnimation != null) {
            mLoadingAnimation.cancelAnimation();
        }
        super.onDestroy();
    }


    @Override
    public void showLoading() {
        if (currentState == LOADING_STATE || mLoadingView == null) {
            return;
        }
        hideCurrentView();
        currentState = LOADING_STATE;
        mLoadingView.setVisibility(View.VISIBLE);
        mLoadingAnimation.setAnimation("loading_bus.json");
        mLoadingAnimation.loop(true);
        mLoadingAnimation.playAnimation();
    }


    @Override
    public void showError() {
        if (currentState == ERROR_STATE) {
            return;
        }
        hideCurrentView();
        currentState = ERROR_STATE;
        mErrorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNormal() {
        if (currentState == NORMAL_STATE) {
            return;
        }
        hideCurrentView();
        currentState = NORMAL_STATE;
        mNormalView.setVisibility(View.VISIBLE);
    }

    protected void hideCurrentView() {
        switch (currentState) {
            case NORMAL_STATE:
                if (mNormalView == null) {
                    return;
                }
                mNormalView.setVisibility(View.INVISIBLE);
                break;
            case LOADING_STATE:
                mLoadingAnimation.cancelAnimation();
                mLoadingView.setVisibility(View.GONE);
                break;
            case ERROR_STATE:
                mErrorView.setVisibility(View.GONE);
            case EMPTY_STATE:
                mEmptyiew.setVisibility(View.GONE);
            default:
                break;
        }
    }

}
