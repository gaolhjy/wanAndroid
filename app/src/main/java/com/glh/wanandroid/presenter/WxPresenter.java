package com.glh.wanandroid.presenter;

import com.doyo.sdk.rx.BaseObserver;
import com.glh.wanandroid.BasePresenter;
import com.glh.wanandroid.R;
import com.glh.wanandroid.bean.WxNameListData;
import com.glh.wanandroid.core.DataManager;
import com.glh.wanandroid.global.AppContext;
import com.glh.wanandroid.presenter.contract.WxContract;
import com.glh.wanandroid.utils.RxUtils;

import java.util.List;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 永无bug集团
 *     time   : 2019/05/24
 *     desc   : 公众号
 *
 * </pre>
 */

public class WxPresenter extends BasePresenter<WxContract.View>
        implements WxContract.Presenter {

    private DataManager     mDataManager;
    private WxContract.View view;
    private boolean         isRefresh = true;

    public WxPresenter(DataManager dataManager, WxContract.View view) {
        super(dataManager);
        this.mDataManager = dataManager;
        this.mView = view;
    }

    @Override
    public void getWxData(boolean isShowError) {

        addSubscribe(mDataManager.getWxArticleData()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<WxNameListData>>(mView,
                        AppContext.getInstance().getString(R.string.failed_to_obtain_wx_data),
                        isShowError) {
                    @Override
                    public void onNext(List<WxNameListData> wxNameListData) {
                        mView.showWx(wxNameListData);
                    }
                }));
    }
}
