package com.glh.wanandroid.ui.activity;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.doyo.sdk.activity.BaseSimpleActivity;
import com.doyo.sdk.fragment.BaseMVPFragment;
import com.glh.wanandroid.R;
import com.glh.wanandroid.ui.fragment.TodoFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 永无bug集团
 *     time   : 2019/6/28 11:18
 *     desc   : 代办事项
 *
 * </pre>
 */
public class MyTodoActivity extends BaseSimpleActivity {
    @BindView(R.id.view_pager)
    ViewPager            mViewPager;
    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView mBottomNavigationView;

    private List<BaseMVPFragment> mFragments = new ArrayList<>(2);

    @Override
    protected void onViewCreated() {
    }

    @Override
    protected void initEventAndData() {
        initBottomNavigationView();
        initViewPager();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_todo;
    }

    @Override
    public void showLoading() {
    }

    private void initBottomNavigationView() {

        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_todo:
                    mViewPager.setCurrentItem(0);
                    break;
                case R.id.navigation_complete:
                    mViewPager.setCurrentItem(1);
                    break;
                default:
                    break;
            }
            return true;
        });
    }


    private void initViewPager() {

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        mFragments.add(TodoFragment.newInstance(false));
        mFragments.add(TodoFragment.newInstance(true));


        //设置适配器用于装载Fragment
        FragmentPagerAdapter mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);  //得到Fragment
            }

            @Override
            public int getCount() {
                return mFragments.size();  //得到数量
            }
        };

        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(2);
    }


}
