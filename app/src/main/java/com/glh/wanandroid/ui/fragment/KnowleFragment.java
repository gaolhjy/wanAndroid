package com.glh.wanandroid.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.doyo.sdk.adapter.BaseCompatAdapter;
import com.doyo.sdk.fragment.BaseListFragment;
import com.doyo.sdk.mvp.AbstractPresenter;
import com.doyo.sdk.mvp.IBaseListView2;
import com.doyo.sdk.utils.JumpUtils;
import com.glh.wanandroid.R;
import com.glh.wanandroid.bean.KnowleData;
import com.glh.wanandroid.constant.Constants;
import com.glh.wanandroid.core.DataManager;
import com.glh.wanandroid.core.http.ApiFactory;
import com.glh.wanandroid.core.http.HttpHelper;
import com.glh.wanandroid.core.http.HttpHelperImpl;
import com.glh.wanandroid.core.prefs.PreferenceHelper;
import com.glh.wanandroid.core.prefs.PreferenceHelperImpl;
import com.glh.wanandroid.presenter.KnowlePresenter;
import com.glh.wanandroid.ui.activity.KnowleDetailActivity;
import com.glh.wanandroid.ui.adapter.KnowledgeHierarchyListAdapter;

import java.util.List;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 永无bug集团
 *     time   : 2019/05/09
 *     desc   :  首页底部-----知识体系fragment
 *
 * </pre>
 */

public class KnowleFragment extends BaseListFragment<KnowlePresenter,
        KnowledgeHierarchyListAdapter,List<KnowleData>> implements IBaseListView2<List<KnowleData>> {

    public static KnowleFragment getInstance(String param1, String param2) {
        KnowleFragment fragment = new KnowleFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_PARAM1, param1);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected BaseCompatAdapter getAbstractAdapter() {
        isHavaMore = false;
        mAdapter = new KnowledgeHierarchyListAdapter(R.layout.item_knowledge_hierarchy, null);
        mAdapter.setOnItemClickListener((adapter, view, position) -> startDetailPager(view,
                position));
        return mAdapter;
    }


    @Override
    protected void getInitData() {
    }


    private void startDetailPager(View view, int position) {
        if (mAdapter.getData().size() <= 0 || mAdapter.getData().size() <= position) {
            return;
        }

        KnowleData data = mAdapter.getData().get(position);
        JumpUtils.JumpToActivity(_mActivity, KnowleDetailActivity.class, data);

    }


    @Override
    protected AbstractPresenter initPresenter() {
        PreferenceHelper mPreferenceHelper = new PreferenceHelperImpl();
        HttpHelper mHttpHelper = new HttpHelperImpl(ApiFactory.getApiService());
        DataManager dataManager = new DataManager(mHttpHelper, mPreferenceHelper);
        mPresenter = new KnowlePresenter(dataManager, this);
        return mPresenter;
    }


    @Override
    protected void getData(int currentPage, boolean isShow, String id) {
        mPresenter.getData(currentPage, id, isShow);
    }

    @Override
    public void showData(List<KnowleData> data) {

        if (mAdapter == null) {
            return;
        }

        mRecyclerView.setVisibility(View.VISIBLE);

        if (isRefresh) {
            mAdapter.setEnableLoadMore(true);
            mAdapter.setNewData(data);
        } else {
            mAdapter.loadMoreComplete();
            mAdapter.addData(data);
        }

        showNormal();

    }
}
