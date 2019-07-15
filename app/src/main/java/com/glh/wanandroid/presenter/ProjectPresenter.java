package com.glh.wanandroid.presenter;

import com.doyo.sdk.rx.BaseObserver;
import com.glh.wanandroid.BasePresenter;
import com.glh.wanandroid.R;
import com.glh.wanandroid.bean.ProjectData;
import com.glh.wanandroid.core.DataManager;
import com.glh.wanandroid.global.AppContext;
import com.glh.wanandroid.presenter.contract.ProjectContract;
import com.glh.wanandroid.utils.RxUtils;

import java.util.List;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 永无bug集团
 *     time   : 2019/05/23
 *     desc   :
 *
 * </pre>
 */

public class ProjectPresenter extends BasePresenter<ProjectContract.View>
        implements ProjectContract.Presenter {


    private ProjectContract.View mView;


    public ProjectPresenter(DataManager dataManager, ProjectContract.View view) {
        super(dataManager);
        this.mDataManager = dataManager;
        this.mView = view;
    }


    @Override
    public void getProjectData(boolean isShowError) {
        addSubscribe(mDataManager.getProjectData()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<ProjectData>>(mView,
                        AppContext.getInstance().getString(R.string.failed_to_obtain_knowledge_data),
                        isShowError) {
                    @Override
                    public void onNext(List<ProjectData> data) {
                        mView.showProject(data);
                    }
                }));
    }
}
