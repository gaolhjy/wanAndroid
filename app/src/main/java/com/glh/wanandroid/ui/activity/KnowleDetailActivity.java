package com.glh.wanandroid.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.doyo.sdk.activity.BaseSimpleActivity;
import com.doyo.sdk.fragment.BaseMVPFragment;
import com.flyco.tablayout.SlidingTabLayout;
import com.glh.wanandroid.R;
import com.glh.wanandroid.bean.KnowleData;
import com.glh.wanandroid.constant.Constants;
import com.glh.wanandroid.ui.fragment.KnowleNetFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 永无bug集团
 *     time   : 2019/05/21
 *     desc   : 知识体系 详情
 *
 * </pre>
 */

public class KnowleDetailActivity extends BaseSimpleActivity {

    @BindView(R.id.knowledge_hierarchy_detail_tab_layout)
    SlidingTabLayout mTabLayout;
    @BindView(R.id.knowledge_hierarchy_detail_viewpager)
    ViewPager        mViewPager;
    @BindView(R.id.tv_title)
    TextView         mTitleTv;

    private List<BaseMVPFragment> mFragments = new ArrayList<>();
    private List<KnowleData>      knowledgeHierarchyDataList;
    private String                chapterName;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_knowledgehie_detail;
    }


    @Override
    protected void onViewCreated() {
        startNormalKnowledgeListPager();
    }

    @Override
    protected void initEventAndData() {
        initViewPagerAndTabLayout();
    }

    /**
     * 装载多个列表的知识体系页面（knowledge进入）
     */
    private void startNormalKnowledgeListPager() {

        KnowleData knowledgeHierarchyData =
                (KnowleData) getIntent().getSerializableExtra("data");
        if (knowledgeHierarchyData == null || knowledgeHierarchyData.name == null) {
            return;
        }
        mTitleTv.setText(knowledgeHierarchyData.name.trim());
        knowledgeHierarchyDataList = knowledgeHierarchyData.children;
        if (knowledgeHierarchyDataList == null) {
            return;
        }
        for (KnowleData data : knowledgeHierarchyDataList) {
            mFragments.add(KnowleNetFragment.getInstance(data.id, null));
        }
    }

    private void initViewPagerAndTabLayout() {

        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments == null ? 0 : mFragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if (getIntent().getBooleanExtra(Constants.IS_SINGLE_CHAPTER, false)) {
                    return chapterName;
                } else {
                    return knowledgeHierarchyDataList.get(position).name;
                }
            }
        });
        mTabLayout.setViewPager(mViewPager);
    }



}
