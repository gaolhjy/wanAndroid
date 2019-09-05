package com.glh.wanandroid.ui.fragment;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.doyo.sdk.fragment.BaseNetFragment;
import com.doyo.sdk.mvp.AbstractPresenter;
import com.doyo.sdk.utils.NetUtils;
import com.glh.wanandroid.R;
import com.glh.wanandroid.bean.NaviData;
import com.glh.wanandroid.constant.Constants;
import com.glh.wanandroid.presenter.NavigationPresenter;
import com.glh.wanandroid.presenter.contract.NavigationContract;
import com.glh.wanandroid.ui.adapter.NavigationAdapter;
import com.glh.wanandroid.utils.MvpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 永无bug集团
 *     time   : 2019/05/09
 *     desc   :  首页底部-----导航fragment
 *
 * </pre>
 */

public class NavigationFragment extends BaseNetFragment<NavigationPresenter> implements NavigationContract.View {

    @BindView(R.id.navigation_tab_layout)
    VerticalTabLayout mTabLayout;
    @BindView(R.id.navigation_divider)
    View              mDivider;
    @BindView(R.id.recycler_view)
    RecyclerView      mRecyclerView;
    @BindView(R.id.normal_view)
    LinearLayout      mNavigationGroup;

    private NavigationAdapter   mAdapter;
    private List<NaviData>      mDataList;
    private LinearLayoutManager mManager;


    public static NavigationFragment getInstance(String param1, String param2) {
        NavigationFragment fragment = new NavigationFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_PARAM1, param1);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation;
    }

    @Override
    protected void initViews() {
        initRecyclerView();

    }

    private void initRecyclerView() {

        mDataList = new ArrayList<>();
        mAdapter = new NavigationAdapter(R.layout.item_navigation, mDataList);
        mManager = new LinearLayoutManager(_mActivity);
        mRecyclerView.setLayoutManager(mManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void lazyFetchData() {
        super.lazyFetchData();
        mPresenter.getNavigationList(true);
        if (NetUtils.isNetworkConnected()) {
            showLoading();
        }

    }


    @Override
    public void showNavigationList(List<NaviData> navigationDataList) {

        mTabLayout.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return navigationDataList == null ? 0 : navigationDataList.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int i) {
                return null;
            }

            @Override
            public ITabView.TabIcon getIcon(int i) {
                return null;
            }

            @Override
            public ITabView.TabTitle getTitle(int i) {
                return new TabView.TabTitle.Builder()
                        .setContent(navigationDataList.get(i).name)
                        .setTextColor(ContextCompat.getColor(_mActivity, R.color.shallow_green),
                                ContextCompat.getColor(_mActivity, R.color.shallow_grey))
                        .build();
            }

            @Override
            public int getBackground(int i) {
                return -1;
            }
        });


        setChildViewVisibility(View.VISIBLE);
        mAdapter.replaceData(navigationDataList);
        showNormal();
    }

    private void setChildViewVisibility(int visibility) {
        mNavigationGroup.setVisibility(visibility);
        mTabLayout.setVisibility(visibility);
        mDivider.setVisibility(visibility);
    }


    @Override
    protected AbstractPresenter initPresenter() {
        mPresenter = new NavigationPresenter(MvpUtils.initDataManager(), this);
        return mPresenter;
    }

    @Override
    public void reload() {
        mPresenter.getNavigationList(false);
    }
}
