package com.glh.wanandroid.presenter;

import com.doyo.sdk.rx.BaseObserver;
import com.glh.wanandroid.BasePresenter;
import com.glh.wanandroid.R;
import com.glh.wanandroid.bean.FeedArticleData;
import com.glh.wanandroid.bean.FeedArticleListData;
import com.glh.wanandroid.core.DataManager;
import com.glh.wanandroid.global.AppContext;
import com.glh.wanandroid.presenter.contract.MyCollectContract;
import com.glh.wanandroid.utils.RxUtils;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 永无bug集团
 *     time   : 2019/05/20
 *     desc   : 收藏
 *
 * </pre>
 */

public class MyCollectPresenter extends BasePresenter<MyCollectContract.View> implements MyCollectContract.Presenter {

    DataManager            mDataManager;
    MyCollectContract.View mView;
    private boolean isRefresh = true;

    public MyCollectPresenter(DataManager dataManager, MyCollectContract.View view) {
        super(dataManager);
        this.mDataManager = dataManager;
        this.mView = view;
    }


    @Override
    public void getFeedArticleList(int page, boolean isShowError) {

        addSubscribe(mDataManager.getMyCollectList(page)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .filter(feedArticleListResponse -> mView != null)
                .subscribeWith(new BaseObserver<FeedArticleListData>(mView,
                        AppContext.getInstance().getString(R.string.failed_to_obtain_article_list),
                        isShowError) {
                    @Override
                    public void onNext(FeedArticleListData feedArticleListData) {
                        mView.showArticleList(feedArticleListData);
                    }
                }));
    }

    @Override
    public void cancelCollectArticle(int position, FeedArticleData feedArticleData) {

        addSubscribe(mDataManager.cancelCollectPageArticle(feedArticleData.id)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleCollectResult())
                .filter(feedArticleListResponse -> mView != null)
                .subscribeWith(new BaseObserver<FeedArticleListData>(mView,
                        AppContext.getInstance().getString(R.string.cancel_collect_fail)) {
                    @Override
                    public void onNext(FeedArticleListData feedArticleListData) {
                        feedArticleData.collect=false;
                        mView.showCancelCollectArticleData(position, feedArticleData,
                                feedArticleListData);
                    }
                }));
    }

}
