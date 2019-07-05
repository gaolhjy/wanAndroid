package com.glh.wanandroid.presenter;

import android.support.annotation.NonNull;

import com.doyo.sdk.mvp.ResBaseBean;
import com.doyo.sdk.rx.BaseObserver;
import com.doyo.sdk.rx.RxBus;
import com.doyo.sdk.utils.CommonUtils;
import com.glh.wanandroid.BasePresenter;
import com.glh.wanandroid.R;
import com.glh.wanandroid.bean.BannerData;
import com.glh.wanandroid.bean.FeedArticleData;
import com.glh.wanandroid.bean.FeedArticleListData;
import com.glh.wanandroid.bean.LoginData;
import com.glh.wanandroid.constant.Constants;
import com.glh.wanandroid.core.DataManager;
import com.glh.wanandroid.core.event.LoginEvent;
import com.glh.wanandroid.global.AppContext;
import com.glh.wanandroid.presenter.contract.HomeContract;
import com.glh.wanandroid.utils.RxUtils;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 磊华集团
 *     time   : 2019/05/13
 *     desc   : 首页fragment的presenter
 *
 * </pre>
 */

public class HomePresenter extends BasePresenter<HomeContract.View>
        implements HomeContract.Presenter {

    private DataManager       mDataManager;
    private HomeContract.View mView;
    private boolean           isRefresh = true;
    private int               mCurrentPage;

    public HomePresenter(DataManager dataManager, HomeContract.View view) {
        super(dataManager);
        this.mDataManager = dataManager;
        this.mView = view;
    }

    @Override
    public String getLoginPassword() {
        return mDataManager.getLoginPassword();
    }

    @Override
    public String getLoginAccount() {
        return mDataManager.getLoginAccount();
    }

    @Override
    public void attachView(HomeContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

        addSubscribe(RxBus.getDefault().toFlowable(LoginEvent.class)
                .filter(LoginEvent::isLogin)
                .subscribe(loginEvent -> mView.showLoginView()));

        addSubscribe(RxBus.getDefault().toFlowable(LoginEvent.class)
                .filter(loginEvent -> !loginEvent.isLogin())
                .subscribe(loginEvent -> mView.showLogoutView()));
    }

    @Override
    public void loadMainPagerData() {

        Observable<ResBaseBean<LoginData>> mLoginObservable = mDataManager.login(getLoginAccount(),
                getLoginPassword());
        Observable<ResBaseBean<List<BannerData>>> mBannerObservable = mDataManager.getBannerData();
        Observable<ResBaseBean<FeedArticleListData>> mArticleObservable =
                mDataManager.getFeedArticleList(0);

        addSubscribe(Observable.zip(mLoginObservable, mBannerObservable, mArticleObservable,
                this::createResponseMap)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseObserver<HashMap<String, Object>>(mView) {
                    @Override
                    public void onNext(HashMap<String, Object> map) {

                        ResBaseBean<LoginData> loginResponse =
                                CommonUtils.cast(map.get(Constants.LOGIN_DATA));
                        if (loginResponse.getErrorCode() == ResBaseBean.SUCCESS) {
                            loginSuccess(loginResponse);
                        } else {
                            mView.showAutoLoginFail();
                        }

                        ResBaseBean<List<BannerData>> bannerResponse =
                                CommonUtils.cast(map.get(Constants.BANNER_DATA));
                        if (bannerResponse != null) {
                            mView.showBannerData(bannerResponse.getData());
                        }
                        ResBaseBean<FeedArticleListData> feedArticleListResponse =
                                CommonUtils.cast(map.get(Constants.ARTICLE_DATA));
                        if (feedArticleListResponse != null) {
                            mView.showArticleList(feedArticleListResponse.getData(), isRefresh);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.showAutoLoginFail();
                    }
                }));
    }

    private void loginSuccess(ResBaseBean<LoginData> loginResponse) {
        LoginData loginData = loginResponse.getData();
        mDataManager.setLoginAccount(loginData.username);
        mDataManager.setLoginPassword(loginData.password);
        mDataManager.setLoginStatus(true);
        mView.showAutoLoginSuccess();
    }

    @Override
    public void loadMoreData() {

//        addSubscribe(mDataManager.getFeedArticleList(mCurrentPage)
//                .compose(RxUtils.rxSchedulerHelper())
//                .compose(RxUtils.handleResult())
//                .filter(feedArticleListResponse -> mView != null)
//                .subscribeWith(new BaseObserver<FeedArticleListData>(mView,
//                        AppContext.getInstance().getString(R.string
//                                .failed_to_obtain_article_list),
//                        false) {
//                    @Override
//                    public void onNext(FeedArticleListData feedArticleListData) {
//                        if (feedArticleListData.datas.size() > 0) {
//                            mView.showArticleList(feedArticleListData, isRefresh);
//                        } else {
//                            mView.showNoMoreData();
//                        }
//                    }
//                }));


        addSubscribe(mDataManager.getFeedArticleList(mCurrentPage)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .filter(feedArticleListResponse -> mView != null)
                .subscribe(feedArticleListData -> {
                    if (feedArticleListData.datas.size() > 0) {
                        mView.showArticleList(feedArticleListData, isRefresh);
                    } else {
                        mView.showNoMoreData();
                    }
                }, throwable -> mView.showLoadMoreError()));

    }

    @Override
    public void addCollectArticle(int position, FeedArticleData feedArticleData) {

        addSubscribe(mDataManager.addCollectArticle(feedArticleData.id)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleCollectResult())
                .subscribeWith(new BaseObserver<FeedArticleListData>(mView,
                        AppContext.getInstance().getString(R.string.collect_fail)) {
                    @Override
                    public void onNext(FeedArticleListData feedArticleListData) {
                        feedArticleData.collect = true;
                        mView.showCollectArticleData(position, feedArticleData,
                                feedArticleListData);
                    }
                }));
    }

    @Override
    public void cancelCollectArticle(int position, FeedArticleData feedArticleData) {

        addSubscribe(mDataManager.cancelCollectArticle(feedArticleData.id)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleCollectResult())
                .subscribeWith(new BaseObserver<FeedArticleListData>(mView,
                        AppContext.getInstance().getString(R.string.collect_fail)) {
                    @Override
                    public void onNext(FeedArticleListData feedArticleListData) {
                        feedArticleData.collect = false;
                        mView.showCollectArticleData(position, feedArticleData,
                                feedArticleListData);
                    }
                }));

    }


    @Override
    public void autoRefresh(boolean isShowError) {
        isRefresh = true;
        mCurrentPage = 0;
        getBannerData(isShowError);
        getFeedArticleList(isShowError);
    }

    @Override
    public void loadMore() {
        isRefresh = false;
        mCurrentPage++;
        loadMoreData();
    }

    @Override
    public void getBannerData(boolean isShowError) {

        addSubscribe(mDataManager.getBannerData()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<BannerData>>(mView,
                        AppContext.getInstance().getString(R.string.failed_to_obtain_banner_data),
                        isShowError) {
                    @Override
                    public void onNext(List<BannerData> bannerDataList) {
                        mView.showBannerData(bannerDataList);
                    }
                }));
    }

    @Override
    public void getFeedArticleList(boolean isShowError) {

        addSubscribe(mDataManager.getFeedArticleList(mCurrentPage)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .filter(feedArticleListData -> mView != null)
                .subscribeWith(new BaseObserver<FeedArticleListData>(mView,
                        AppContext.getInstance().getString(R.string.failed_to_obtain_article_list),
                        isShowError) {
                    @Override
                    public void onNext(FeedArticleListData feedArticleListData) {
                        mView.showArticleList(feedArticleListData, isRefresh);
                    }
                }));
    }


    @NonNull
    private HashMap<String, Object> createResponseMap(ResBaseBean<LoginData> loginData,
                                                      ResBaseBean<List<BannerData>> bannerResponse,
                                                      ResBaseBean<FeedArticleListData> feedArticleListResponse) {
        HashMap<String, Object> map = new HashMap<>(3);
        map.put(Constants.LOGIN_DATA, loginData);
        map.put(Constants.BANNER_DATA, bannerResponse);
        map.put(Constants.ARTICLE_DATA, feedArticleListResponse);
        return map;
    }
}
