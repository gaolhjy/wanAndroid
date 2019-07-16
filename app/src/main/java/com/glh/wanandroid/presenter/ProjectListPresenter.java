package com.glh.wanandroid.presenter;

import android.util.Log;

import com.doyo.sdk.mvp.IBaseListContract;
import com.doyo.sdk.mvp.IBaseListView;
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
 *     time   : 2019/05/23
 *     desc   : 项目二级
 *
 * </pre>
 */

public class ProjectListPresenter extends BasePresenter<IBaseListView>
        implements IBaseListContract.Presenter {


    private IBaseListView mView;


    public ProjectListPresenter(DataManager dataManager, IBaseListView view) {
        super(dataManager);
        this.mDataManager = dataManager;
        this.mView = view;
    }


    @Override
    public void getData(int pager, String cid, boolean isShowError) {

        Log.e("页面打印.............:", pager + "");

        addSubscribe(mDataManager.getProjectListData(pager, cid)
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
