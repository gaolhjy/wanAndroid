package com.glh.wanandroid.ui.activity;

import android.support.v7.widget.AppCompatCheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.doyo.sdk.activity.BaseMVPActivity;
import com.doyo.sdk.mvp.AbstractPresenter;
import com.glh.wanandroid.R;
import com.glh.wanandroid.presenter.SettingPresenter;
import com.glh.wanandroid.presenter.contract.SettingContract;
import com.glh.wanandroid.utils.MvpUtils;

import butterknife.BindView;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 银江集团
 *     time   : 2019/7/16 15:18
 *     desc   : 设置
 *
 * </pre>
 */
public class SettingActivity extends BaseMVPActivity<SettingPresenter> implements SettingContract.View, CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.cb_setting_cache)
    AppCompatCheckBox mCbSettingCache;
    @BindView(R.id.cb_setting_image)
    AppCompatCheckBox mCbSettingImage;
    @BindView(R.id.cb_setting_night)
    AppCompatCheckBox mCbSettingNight;
    @BindView(R.id.ll_setting_feedback)
    TextView          mLlSettingFeedback;
    @BindView(R.id.ll_setting_clear)
    LinearLayout      mLlSettingClear;
    @BindView(R.id.tv_setting_clear)
    TextView          mTvSettingClear;

    @Override
    protected AbstractPresenter initPresenter() {
        mPresenter = new SettingPresenter(MvpUtils.initDataManager(), this);
        return mPresenter;
    }

    @Override
    protected void initEventAndData() {
        mCbSettingImage.setChecked(mPresenter.getNoImageState());
        mCbSettingImage.setOnCheckedChangeListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cb_setting_night:
            case R.id.cb_setting_image:
                mPresenter.setNoImageState(isChecked);
                break;
            default:
                break;
        }
    }
}
