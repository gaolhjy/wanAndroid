package com.glh.wanandroid.ui.fragment;

import android.os.Bundle;

import com.doyo.sdk.adapter.BaseCompatAdapter;
import com.doyo.sdk.fragment.BaseListFragment3;
import com.doyo.sdk.mvp.AbstractPresenter;
import com.glh.wanandroid.R;
import com.glh.wanandroid.constant.Constants;
import com.glh.wanandroid.core.DataManager;
import com.glh.wanandroid.core.http.ApiFactory;
import com.glh.wanandroid.core.http.HttpHelper;
import com.glh.wanandroid.core.http.HttpHelperImpl;
import com.glh.wanandroid.core.prefs.PreferenceHelper;
import com.glh.wanandroid.core.prefs.PreferenceHelperImpl;
import com.glh.wanandroid.presenter.KnowleDetailPresenter;
import com.glh.wanandroid.ui.adapter.ArticleListAdapter;

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

public class KnowleNetFragment extends BaseListFragment3<KnowleDetailPresenter, ArticleListAdapter> {

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
    protected void getData(int currentPage, boolean isShow, String id) {
        mPresenter.getData(currentPage, id, isShow);
    }


    @Override
    protected AbstractPresenter initPresenter() {
        PreferenceHelper mPreferenceHelper = new PreferenceHelperImpl();
        HttpHelper mHttpHelper = new HttpHelperImpl(ApiFactory.getApiService());
        DataManager dataManager = new DataManager(mHttpHelper, mPreferenceHelper);
        mPresenter = new KnowleDetailPresenter(dataManager, this);
        return mPresenter;
    }

}
