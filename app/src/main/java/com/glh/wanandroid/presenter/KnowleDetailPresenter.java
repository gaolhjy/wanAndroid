package com.glh.wanandroid.presenter;

import com.doyo.sdk.rx.BaseObserver;
import com.glh.wanandroid.BasePresenter;
import com.glh.wanandroid.R;
import com.glh.wanandroid.bean.FeedArticleListData;
import com.glh.wanandroid.core.DataManager;
import com.glh.wanandroid.global.AppContext;
import com.glh.wanandroid.presenter.contract.KnowleDetailContract;
import com.glh.wanandroid.utils.RxUtils;

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

public class KnowleDetailPresenter extends BasePresenter<KnowleDetailContract.View>
        implements KnowleDetailContract.Presenter {

    private DataManager               mDataManager;
    private KnowleDetailContract.View view;
    private boolean                   isRefresh = true;

    public KnowleDetailPresenter(DataManager dataManager, KnowleDetailContract.View view) {
        super(dataManager);
        this.mDataManager = dataManager;
        this.mView = view;
    }


    @Override
    public void getFeedArticleList(int page, String id, boolean isShowError) {

        addSubscribe(mDataManager.getKnowledgeHierarchyDetail(page, id)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<FeedArticleListData>(mView,
                        AppContext.getInstance().getString(R.string.failed_to_obtain_knowledge_data),
                        isShowError) {
                    @Override
                    public void onNext(FeedArticleListData feedArticleListData) {
                        mView.showFeedArticleList(feedArticleListData);
                    }
                }));
    }

}
