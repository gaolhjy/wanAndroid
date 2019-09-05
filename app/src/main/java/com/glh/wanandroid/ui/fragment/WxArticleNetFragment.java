package com.glh.wanandroid.ui.fragment;

import android.os.Bundle;

import com.doyo.sdk.adapter.BaseCompatAdapter;
import com.doyo.sdk.fragment.BaseListFragmentEx;
import com.doyo.sdk.mvp.AbstractPresenter;
import com.glh.wanandroid.R;
import com.glh.wanandroid.constant.Constants;
import com.glh.wanandroid.presenter.WxArticlePresenter;
import com.glh.wanandroid.ui.adapter.ArticleListAdapter;
import com.glh.wanandroid.utils.MvpUtils;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 永无bug集团
 *     time   : 2019/5/24 16:46
 *     desc   : 微信公众号二级栏目 fragment
 *
 * </pre>
 */
public class WxArticleNetFragment extends BaseListFragmentEx<WxArticlePresenter,ArticleListAdapter> {

    public static WxArticleNetFragment getInstance(String param1, String param2) {
        WxArticleNetFragment fragment = new WxArticleNetFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_PARAM1, param1);
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
    protected AbstractPresenter initPresenter() {
        mPresenter = new WxArticlePresenter(MvpUtils.initDataManager(), this);
        return mPresenter;
    }


    @Override
    protected void getData(int curPage, boolean isShow, String id) {
       mPresenter.getData(curPage, id, false);
    }

}
