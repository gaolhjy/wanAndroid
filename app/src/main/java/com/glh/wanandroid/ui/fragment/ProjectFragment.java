package com.glh.wanandroid.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.doyo.sdk.fragment.BaseMVPFragment;
import com.doyo.sdk.fragment.BaseNetFragment;
import com.doyo.sdk.mvp.AbstractPresenter;
import com.doyo.sdk.utils.NetUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.glh.wanandroid.R;
import com.glh.wanandroid.bean.ProjectData;
import com.glh.wanandroid.constant.Constants;
import com.glh.wanandroid.presenter.ProjectPresenter;
import com.glh.wanandroid.presenter.contract.ProjectContract;
import com.glh.wanandroid.utils.MvpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 永无bug集团
 *     time   : 2019/05/09
 *     desc   :  首页底部-----项目fragment
 *
 * </pre>
 */

public class ProjectFragment extends BaseNetFragment<ProjectPresenter> implements ProjectContract.View {

    @BindView(R.id.project_tab_layout)
    SlidingTabLayout mTabLayout;
    @BindView(R.id.project_divider)
    View             mDivider;
    @BindView(R.id.project_viewpager)
    ViewPager        mViewPager;

    private List<ProjectData>     mData;
    private List<BaseMVPFragment> mFragments = new ArrayList<>();


    public static ProjectFragment getInstance(String param1, String param2) {
        ProjectFragment fragment = new ProjectFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_PARAM1, param1);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void lazyFetchData() {
        super.lazyFetchData();
        mPresenter.getProjectData(true);
        if (NetUtils.isNetworkConnected()) {
            showLoading();
        }
    }

    @Override
    protected void reload() {
        mPresenter.getProjectData(true);
    }

    @Override
    public void showProject(List<ProjectData> projectDataList) {
        setChildViewVisibility(View.VISIBLE);
        mData = projectDataList;
        initViewPagerAndTabLayout();
        showNormal();
    }

    private void setChildViewVisibility(int visibility) {
        mTabLayout.setVisibility(visibility);
        mDivider.setVisibility(visibility);
        mViewPager.setVisibility(visibility);
    }

    private void initViewPagerAndTabLayout() {
        for (ProjectData data : mData) {
            ProjectNetFragment projectListFragment = ProjectNetFragment.getInstance(data.id,
                    null);
            mFragments.add(projectListFragment);
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
        mPresenter = new ProjectPresenter(MvpUtils.initDataManager(), this);
        return mPresenter;
    }

}
