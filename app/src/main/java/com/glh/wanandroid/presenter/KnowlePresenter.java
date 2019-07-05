package com.glh.wanandroid.presenter;

import com.doyo.sdk.rx.BaseObserver;
import com.glh.wanandroid.BasePresenter;
import com.glh.wanandroid.R;
import com.glh.wanandroid.bean.KnowleData;
import com.glh.wanandroid.core.DataManager;
import com.glh.wanandroid.global.AppContext;
import com.glh.wanandroid.presenter.contract.KnowleContract;
import com.glh.wanandroid.utils.RxUtils;

import java.util.List;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 磊华集团
 *     time   : 2019/05/21
 *     desc   :
 *
 * </pre>
 */

public class KnowlePresenter extends BasePresenter<KnowleContract.View>
        implements KnowleContract.Presenter {

    private DataManager         mDataManager;
    private KnowleContract.View view;
    private boolean             isRefresh = true;

    public KnowlePresenter(DataManager dataManager, KnowleContract.View view) {
        super(dataManager);
        this.mDataManager = dataManager;
        this.mView = view;
    }

    @Override
    public void getKnowHierarchyList(boolean isShowError) {

        addSubscribe(mDataManager.getKnowledgeHierarchyData()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .filter(KnowledgeHierarchyData -> mView != null)
                .subscribeWith(new BaseObserver<List<KnowleData>>(mView,
                        AppContext.getInstance().getString(R.string.failed_to_obtain_knowledge_data),
                        isShowError) {
                    @Override
                    public void onNext(List<KnowleData> data) {
                        mView.showKnowHierarchyList(data, isRefresh);
                    }
                }));
    }

    public void autoRefresh(boolean isShowError) {
        isRefresh = true;
        getKnowHierarchyList(isShowError);
    }
}
