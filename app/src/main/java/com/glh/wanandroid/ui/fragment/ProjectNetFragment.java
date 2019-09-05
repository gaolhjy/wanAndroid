package com.glh.wanandroid.ui.fragment;

import android.os.Bundle;

import com.doyo.sdk.adapter.BaseCompatAdapter;
import com.doyo.sdk.fragment.BaseListFragmentEx;
import com.doyo.sdk.mvp.AbstractPresenter;
import com.glh.wanandroid.R;
import com.glh.wanandroid.constant.Constants;
import com.glh.wanandroid.presenter.ProjectListPresenter;
import com.glh.wanandroid.ui.adapter.ProjectListAdapter;
import com.glh.wanandroid.utils.MvpUtils;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 永无bug集团
 *     time   : 2019/5/23 16:46
 *     desc   : 项目二级子fragment.   当前页从第1页开始
 *
 * </pre>
 */
public class ProjectNetFragment extends BaseListFragmentEx<ProjectListPresenter,
        ProjectListAdapter> {

    public static ProjectNetFragment getInstance(String params1, String params2) {

        ProjectNetFragment fragment = new ProjectNetFragment();

        Bundle args = new Bundle();
        args.putString(Constants.ARG_PARAM1, params1);
        args.putString(Constants.ARG_PARAM2, params2);
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    protected BaseCompatAdapter getAbstractAdapter() {
        return new ProjectListAdapter(R.layout.item_project_list, null);
    }


    @Override
    protected void getInitData() {

        Bundle bundle = getArguments();
        if (bundle != null) {
            id = bundle.getString(Constants.ARG_PARAM1, null);
        } else {
            id = "1";
        }
    }


    @Override
    protected AbstractPresenter initPresenter() {
        mPresenter = new ProjectListPresenter(MvpUtils.initDataManager(), this);
        return mPresenter;
    }


    @Override
    protected void getData(int curPage, boolean isShow, String id) {
        mPresenter.getData(curPage, id, isShow);
    }
}
