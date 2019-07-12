package com.glh.wanandroid.presenter;

import com.doyo.sdk.mvp.IBaseListContract;
import com.doyo.sdk.mvp.IBaseListView2;
import com.doyo.sdk.rx.BaseObserver;
import com.glh.wanandroid.BasePresenter;
import com.glh.wanandroid.R;
import com.glh.wanandroid.bean.KnowleData;
import com.glh.wanandroid.core.DataManager;
import com.glh.wanandroid.global.AppContext;
import com.glh.wanandroid.utils.RxUtils;

import java.util.List;

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

public class KnowlePresenter extends BasePresenter<IBaseListView2>
        implements IBaseListContract.Presenter {

    private DataManager    mDataManager;
    private IBaseListView2 mView;


    public KnowlePresenter(DataManager dataManager, IBaseListView2 view) {
        super(dataManager);
        this.mDataManager = dataManager;
        this.mView = view;
    }


    @Override
    public void getData(int pager, String cid, boolean isShowError) {

        addSubscribe(mDataManager.getKnowledgeHierarchyData()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .filter(KnowledgeHierarchyData -> mView != null)
                .subscribeWith(new BaseObserver<List<KnowleData>>(mView,
                        AppContext.getInstance().getString(R.string.failed_to_obtain_knowledge_data), isShowError) {
                    @Override
                    public void onNext(List<KnowleData> data) {
                        if (data.size() > 0) {
                            mView.showData(data);
                        } else {
                            mView.showNoMoreData();
                        }
                    }
                }));


        //                addSubscribe(mDataManager.getKnowledgeHierarchyData()
        //                        .map(KnowResultToBaseMapper.getInstance())
        //                        .compose(RxUtils.rxSchedulerHelper())
        //                        .compose(RxUtils.handleResult())
        //                        .subscribeWith(new BaseObserver<ResBaseListBean<KnoelwData2>>
        //                        (mView,
        //                                AppContext.getInstance().getString(R.string
        //                                .failed_to_obtain_knowledge_data),
        //                                isShowError) {
        //
        //                            @Override
        //                            public void onNext(ResBaseListBean<KnoelwData2> data) {
        //                                if (data.datas.size() > 0) {
        //                                    mView.showData(data);
        //                                } else {
        //                                    mView.showNoMoreData();
        //                                }
        //                            }
        //                        }));


        //        addSubscribe(mDataManager.getKnowledgeHierarchyData()
        //                .map(KnowResultToBaseMapper.getInstance())
        //                .subscribeOn(Schedulers.io())
        //                .observeOn(AndroidSchedulers.mainThread())
        //                .subscribe(new Consumer<List<Item>>() {
        //                    @Override
        //                    public void accept(@NonNull List<Item> items) throws Exception {
        //                        swipeRefreshLayout.setRefreshing(false);
        //                        pageTv.setText(getString(R.string.page_with_number, MapFragment
        //                        .this.page));
        //                        adapter.setItems(items);
        //                    }
        //                }, new Consumer<Throwable>() {
        //                    @Override
        //                    public void accept(@NonNull Throwable throwable) throws Exception {
        //                        swipeRefreshLayout.setRefreshing(false);
        //                        Toast.makeText(getActivity(), R.string.loading_failed, Toast
        //                        .LENGTH_SHORT).show();
        //                    }
        //                }));
    }
}
