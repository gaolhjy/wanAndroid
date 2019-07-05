package com.glh.wanandroid.presenter;

import android.util.Log;

import com.doyo.sdk.rx.BaseObserver;
import com.glh.wanandroid.BasePresenter;
import com.glh.wanandroid.R;
import com.glh.wanandroid.bean.ProjectListData;
import com.glh.wanandroid.core.DataManager;
import com.glh.wanandroid.global.AppContext;
import com.glh.wanandroid.presenter.contract.ProjectContract;
import com.glh.wanandroid.presenter.contract.ProjectListContract;
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

public class ProjectListPresenter extends BasePresenter<ProjectListContract.View>
        implements ProjectListContract.Presenter {

    private DataManager          mDataManager;
    private ProjectContract.View view;
    private boolean              isRefresh = true;

    public ProjectListPresenter(DataManager dataManager, ProjectListContract.View view) {
        super(dataManager);
        this.mDataManager = dataManager;
        this.mView = view;
    }


    @Override
    public void getProjectData(int pager, String cid, boolean isShowError) {

        Log.e("页面打印.............:", pager + "");

        addSubscribe(mDataManager.getProjectListData(pager, cid)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<ProjectListData>(mView,
                        AppContext.getInstance().getString(R.string.failed_to_obtain_knowledge_data),
                        isShowError) {
                    @Override
                    public void onNext(ProjectListData data) {
                        if (data.datas.size() > 0) {
                            mView.showProject(data);
                        } else {
                            mView.showNoMoreData();
                        }
                    }
                }));
    }
}
