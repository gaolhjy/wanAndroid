package com.glh.wanandroid.presenter;

import com.doyo.sdk.rx.BaseObserver;
import com.glh.wanandroid.BasePresenter;
import com.glh.wanandroid.R;
import com.glh.wanandroid.bean.WxArticleListData;
import com.glh.wanandroid.core.DataManager;
import com.glh.wanandroid.global.AppContext;
import com.glh.wanandroid.presenter.contract.WxArticleContract;
import com.glh.wanandroid.utils.RxUtils;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 永无bug集团
 *     time   : 2019/05/23
 *     desc   : 项目二级
 *
 * </pre>
 */

public class WxArticlePresenter extends BasePresenter<WxArticleContract.View>
        implements WxArticleContract.Presenter {

    private DataManager            mDataManager;
    private WxArticleContract.View view;
    private boolean                isRefresh = true;

    public WxArticlePresenter(DataManager dataManager, WxArticleContract.View view) {
        super(dataManager);
        this.mDataManager = dataManager;
        this.mView = view;
    }


    @Override
    public void getWxArticleData(int pager, String cid, boolean isShowError) {

        addSubscribe(mDataManager.getWxArticleListData(cid, pager)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<WxArticleListData>(mView,
                        AppContext.getInstance().getString(R.string.failed_to_obtain_wx_data),
                        isShowError) {
                    @Override
                    public void onNext(WxArticleListData wxArticleListData) {
                        if (wxArticleListData.datas.size() > 0) {
                            mView.showProject(wxArticleListData);
                        } else {
                            mView.showNoMoreData();
                        }
                    }
                }));
    }

}
