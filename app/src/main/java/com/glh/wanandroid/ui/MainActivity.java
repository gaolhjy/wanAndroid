package com.glh.wanandroid.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.doyo.sdk.activity.BaseMVPActivity;
import com.doyo.sdk.fragment.BaseMVPFragment;
import com.doyo.sdk.mvp.AbstractPresenter;
import com.doyo.sdk.utils.JumpUtils;
import com.doyo.sdk.widget.CommonAlertDialog;
import com.glh.wanandroid.R;
import com.glh.wanandroid.constant.Constants;
import com.glh.wanandroid.core.DataManager;
import com.glh.wanandroid.core.http.ApiFactory;
import com.glh.wanandroid.core.http.HttpHelper;
import com.glh.wanandroid.core.http.HttpHelperImpl;
import com.glh.wanandroid.core.prefs.PreferenceHelper;
import com.glh.wanandroid.core.prefs.PreferenceHelperImpl;
import com.glh.wanandroid.presenter.MainPresenter;
import com.glh.wanandroid.presenter.contract.MainContract;
import com.glh.wanandroid.ui.activity.LoginActivity;
import com.glh.wanandroid.ui.activity.MyCollectNetActivity;
import com.glh.wanandroid.ui.activity.MyTodoActivity;
import com.glh.wanandroid.ui.fragment.KnowleFragment;
import com.glh.wanandroid.ui.fragment.HomeFragment;
import com.glh.wanandroid.ui.fragment.NavigationFragment;
import com.glh.wanandroid.ui.fragment.ProjectFragment;
import com.glh.wanandroid.ui.fragment.WxArticleFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseMVPActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.fragment_group)
    FrameLayout          mFragmentGroup;
    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView mBottomNavigationView;
    @BindView(R.id.nav_view)
    NavigationView       mNavigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout         mDrawerLayout;

    private ArrayList<BaseMVPFragment> mFragments;
    private TextView                   mUsTv;
    private HomeFragment               mHomeFragment;
    private KnowleFragment             mKnowleFragment;
    private WxArticleFragment          mWxArticleFragment;
    private NavigationFragment         mNavigationFragment;
    private ProjectFragment            mProjectFragment;
    private int                        mLastFgIndex;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        mFragments = new ArrayList<>();
        initPager(true, Constants.TYPE_MAIN_PAGER);
    }


    private void initPager(boolean isRecreate, int position) {
        mHomeFragment = HomeFragment.getInstance(isRecreate, null);
        mFragments.add(mHomeFragment);
        initFragments();
        initView();
        switchFragment(position);
    }

    private void initView() {
        initNavigationView();
        initBottomNavigationView();
    }

    private void initNavigationView() {

        mUsTv = mNavigationView.getHeaderView(0).findViewById(R.id.nav_header_login_tv);
        if (mPresenter.getLoginStatus()) {
            showLoginView();
        } else {
            showLogoutView();
        }

        mNavigationView.getMenu().findItem(R.id.nav_item_logout)
                .setOnMenuItemClickListener(item -> {
                    logout();
                    return true;
                });


        mNavigationView.getMenu().findItem(R.id.nav_item_my_collect)
                .setOnMenuItemClickListener(item -> {
                    goMyCollect();
                    return true;
                });

        mNavigationView.getMenu().findItem(R.id.nav_item_todo)
                .setOnMenuItemClickListener(item -> {
                    goMyTodo();
                    return true;
                });
    }

    private void goMyTodo() {
        if (mPresenter.getLoginStatus()) {
            JumpUtils.JumpToActivity(context, MyTodoActivity.class);
        }else {
            JumpUtils.JumpToActivity(context, LoginActivity.class);
        }
    }

    private void goMyCollect() {
        if (mPresenter.getLoginStatus()) {
            JumpUtils.JumpToActivity(context, MyCollectNetActivity.class);
        } else {
            JumpUtils.JumpToActivity(context, LoginActivity.class);
        }
    }


    private void initBottomNavigationView() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.tab_main_pager:
                    loadPager(getString(R.string.home_pager), 0,
                            mHomeFragment, Constants.TYPE_MAIN_PAGER);
                    break;
                case R.id.tab_knowledge_hierarchy:
                    loadPager(getString(R.string.knowledge_hierarchy), 1,
                            mKnowleFragment, Constants.TYPE_KNOWLEDGE);
                    break;
                case R.id.tab_wx_article:
                    loadPager(getString(R.string.wx_article), 2,
                            mWxArticleFragment, Constants.TYPE_WX_ARTICLE);
                    break;
                case R.id.tab_navigation:
                    loadPager(getString(R.string.navigation), 3,
                            mNavigationFragment, Constants.TYPE_NAVIGATION);
                    break;
                case R.id.tab_project:
                    loadPager(getString(R.string.project), 4,
                            mProjectFragment, Constants.TYPE_PROJECT);
                    break;
                default:
                    break;
            }
            return true;
        });
    }

    private void loadPager(String title, int position, BaseMVPFragment mFragment, int pagerType) {
        switchFragment(position);
        mPresenter.setCurrentPage(pagerType);
    }


    private void initFragments() {

        mKnowleFragment = KnowleFragment.getInstance(null, null);
        mWxArticleFragment = WxArticleFragment.getInstance(null, null);
        mNavigationFragment = NavigationFragment.getInstance(null, null);
        mProjectFragment = ProjectFragment.getInstance(null, null);

        mFragments.add(mKnowleFragment);
        mFragments.add(mWxArticleFragment);
        mFragments.add(mNavigationFragment);
        mFragments.add(mProjectFragment);

    }

    /**
     * 切换fragment
     *
     * @param position 要显示的fragment的下标
     */
    private void switchFragment(int position) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment targetFg = mFragments.get(position);
        Fragment lastFg = mFragments.get(mLastFgIndex);
        mLastFgIndex = position;
        ft.hide(lastFg);
        if (!targetFg.isAdded()) {
            getSupportFragmentManager().beginTransaction().remove(targetFg).commitAllowingStateLoss();
            ft.add(R.id.fragment_group, targetFg);
        }
        ft.show(targetFg);
        ft.commitAllowingStateLoss();
    }


    @Override
    public void showSwitchProject() {

    }

    @Override
    public void showSwitchNavigation() {

    }

    @Override
    public void showAutoLoginView() {

    }

    @Override
    public void showLogoutSuccess() {
        CommonAlertDialog.newInstance().cancelDialog(true);
        mNavigationView.getMenu().findItem(R.id.nav_item_logout).setVisible(false);
    }


    @NonNull
    @Override
    public AbstractPresenter initPresenter() {
        HttpHelper mHttpHelper = new HttpHelperImpl(ApiFactory.getApiService());
        PreferenceHelper mPreferenceHelper = new PreferenceHelperImpl();
        DataManager manager = new DataManager(mHttpHelper, mPreferenceHelper);
        mPresenter = new MainPresenter(manager, this);
        return mPresenter;
    }

    @Override
    public void showLoading() {

    }


    @Override
    public void showLoginView() {
        if (mNavigationView == null) {
            return;
        }
        mUsTv = mNavigationView.getHeaderView(0).findViewById(R.id.nav_header_login_tv);
        mUsTv.setText(mPresenter.getLoginAccount());
        mUsTv.setOnClickListener(null);
        mNavigationView.getMenu().findItem(R.id.nav_item_logout).setVisible(true);
    }

    @Override
    public void showLogoutView() {
        mUsTv.setText(R.string.login_in);
        mUsTv.setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));
        if (mNavigationView == null) {
            return;
        }
        mNavigationView.getMenu().findItem(R.id.nav_item_logout).setVisible(false);
    }

    private void logout() {
        CommonAlertDialog.newInstance().showDialog(
                this, getString(R.string.logout_tint),
                getString(R.string.ok),
                getString(R.string.no),
                v -> mPresenter.logout(),
                v -> CommonAlertDialog.newInstance().cancelDialog(true));
    }
}
