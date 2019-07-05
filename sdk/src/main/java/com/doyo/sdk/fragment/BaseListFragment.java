package com.doyo.sdk.fragment;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.doyo.sdk.mvp.BaseSimplePresenter;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 磊华集团
 *     time   : 2019/6/19 17:18
 *     desc   :
 *
 * </pre>
 */
public abstract class BaseListFragment<T extends BaseSimplePresenter> extends BaseNetFragment implements BaseQuickAdapter.RequestLoadMoreListener{

    @Override
    protected void initViews() {

    }
}
