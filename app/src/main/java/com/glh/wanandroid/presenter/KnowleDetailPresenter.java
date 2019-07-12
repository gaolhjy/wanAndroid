package com.glh.wanandroid.presenter;

import com.doyo.sdk.mvp.IBaseListContract;
import com.doyo.sdk.mvp.IBaseListView2;
import com.doyo.sdk.mvp.ResBaseListBean;
import com.doyo.sdk.rx.BaseObserver;
import com.glh.wanandroid.BasePresenter;
import com.glh.wanandroid.R;
import com.glh.wanandroid.bean.FeedArticleData;
import com.glh.wanandroid.core.DataManager;
import com.glh.wanandroid.global.AppContext;
import com.glh.wanandroid.utils.RxUtils;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 永无bug集团
 *     time   : 2019/05/21
 *     desc   :
 *
 * </pre>
 */

public class KnowleDetailPresenter extends BasePresenter<IBaseListView2>
        implements IBaseListContract.Presenter {

    private DataManager    mDataManager;
    private IBaseListView2 mView;


    public KnowleDetailPresenter(DataManager dataManager, IBaseListView2 view) {
        super(dataManager);
        this.mDataManager = dataManager;
        this.mView = view;
    }


    @Override
    public void getData(int page, String id, boolean isShowError) {

        addSubscribe(mDataManager.getKnowledgeHierarchyDetail(page, id)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<ResBaseListBean<FeedArticleData>>(mView,
                        AppContext.getInstance().getString(R.string.failed_to_obtain_knowledge_data),
                        isShowError) {
                    @Override
                    public void onNext(ResBaseListBean<FeedArticleData> data) {
                        if (data.datas.size() > 0) {
                            mView.showData(data);
                        } else {
                            mView.showNoMoreData();
                        }
                    }
                }));
    }

}
