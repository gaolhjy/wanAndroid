package com.glh.wanandroid.ui.fragment;

import android.os.Bundle;

import com.doyo.sdk.adapter.BaseCompatAdapter;
import com.doyo.sdk.fragment.BaseListFragmentEx;
import com.doyo.sdk.mvp.AbstractPresenter;
import com.glh.wanandroid.R;
import com.glh.wanandroid.constant.Constants;
import com.glh.wanandroid.presenter.KnowleDetailPresenter;
import com.glh.wanandroid.ui.adapter.ArticleListAdapter;
import com.glh.wanandroid.utils.MvpUtils;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 永无bug集团
 *     time   : 2019/05/22
 *     desc   : 知识体系 二级页面fragment
 *
 * </pre>
 */

public class KnowleNetFragment extends BaseListFragmentEx<KnowleDetailPresenter,
        ArticleListAdapter> {

    public static KnowleNetFragment getInstance(String id, String param2) {
        KnowleNetFragment fragment = new KnowleNetFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_PARAM1, id);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected BaseCompatAdapter getAbstractAdapter() {
        return new ArticleListAdapter(R.layout.item_search_pager, null);
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
    protected void getData(int curPage, boolean isShow, String id) {
        curPage = 0;
        mPresenter.getData(curPage, id, isShow);
    }


    @Override
    protected AbstractPresenter initPresenter() {
        mPresenter = new KnowleDetailPresenter(MvpUtils.initDataManager(), this);
        return mPresenter;
    }

}
