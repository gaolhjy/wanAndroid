package com.glh.wanandroid.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.doyo.sdk.fragment.BaseNetFragment;
import com.doyo.sdk.fragment.BaseMVPFragment;
import com.doyo.sdk.mvp.AbstractPresenter;
import com.doyo.sdk.utils.NetUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.glh.wanandroid.R;
import com.glh.wanandroid.bean.WxNameListData;
import com.glh.wanandroid.constant.Constants;
import com.glh.wanandroid.core.DataManager;
import com.glh.wanandroid.core.http.ApiFactory;
import com.glh.wanandroid.core.http.HttpHelper;
import com.glh.wanandroid.core.http.HttpHelperImpl;
import com.glh.wanandroid.core.prefs.PreferenceHelper;
import com.glh.wanandroid.core.prefs.PreferenceHelperImpl;
import com.glh.wanandroid.presenter.WxPresenter;
import com.glh.wanandroid.presenter.contract.WxContract;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 磊华集团
 *     time   : 2019/05/09
 *     desc   : 首页底部-----公众号fragment
 *
 * </pre>
 */

public class WxArticleFragment extends BaseNetFragment<WxPresenter> implements WxContract.View {

    @BindView(R.id.tab_layout)
    SlidingTabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager        mViewPager;

    private List<WxNameListData>  mData;
    private List<BaseMVPFragment> mFragments = new ArrayList<>();

    public static WxArticleFragment getInstance(String param1, String param2) {
        WxArticleFragment fragment = new WxArticleFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_PARAM1, param1);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wxarticle;
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void lazyFetchData() {
        super.lazyFetchData();
        mPresenter.getWxData(true);
        if (NetUtils.isNetworkConnected()) {
            showLoading();
        }
    }

    @Override
    protected void reload() {
        mPresenter.getWxData(true);
        if (NetUtils.isNetworkConnected()) {
            showLoading();
        }
    }


    @Override
    public void showWx(List<WxNameListData> dataList) {
        setChildViewVisibility(View.VISIBLE);
        mData = dataList;
        initViewPagerAndTabLayout();
        showNormal();
    }

    private void setChildViewVisibility(int visibility) {
        mTabLayout.setVisibility(visibility);
        mViewPager.setVisibility(visibility);
    }

    private void initViewPagerAndTabLayout() {
        for (WxNameListData data : mData) {
            WxArticleNetFragment fragment = WxArticleNetFragment.getInstance(data.id, null);
            mFragments.add(fragment);
        }

        mViewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mData == null ? 0 : mData.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mData.get(position).name;
            }
        });


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mTabLayout.setViewPager(mViewPager);
        mViewPager.setCurrentItem(Constants.TAB_ONE);
    }

    @Override
    protected AbstractPresenter initPresenter() {

        PreferenceHelper mPreferenceHelper = new PreferenceHelperImpl();
        HttpHelper mHttpHelper = new HttpHelperImpl(ApiFactory.getApiService());
        DataManager dataManager = new DataManager(mHttpHelper, mPreferenceHelper);
        mPresenter = new WxPresenter(dataManager, this);

        return mPresenter;
    }

}
