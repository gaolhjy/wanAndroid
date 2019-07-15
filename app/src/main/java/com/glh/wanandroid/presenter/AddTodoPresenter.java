package com.glh.wanandroid.presenter;

import com.doyo.sdk.rx.BaseObserver;
import com.glh.wanandroid.BasePresenter;
import com.glh.wanandroid.bean.TodoDesData;
import com.glh.wanandroid.core.DataManager;
import com.glh.wanandroid.presenter.contract.AddToDoContract;
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

public class AddTodoPresenter extends BasePresenter<AddToDoContract.View>
        implements AddToDoContract.Presenter {

    private AddToDoContract.View mView;

    public AddTodoPresenter(DataManager dataManager, AddToDoContract.View view) {
        super(dataManager);
        this.mDataManager = dataManager;
        this.mView = view;
    }


    @Override
    public void addTodoData(String title, String content, String date, int type,
                            boolean isShowError) {

        addSubscribe(mDataManager.addTodoData(title, content, date, type)
                .compose(RxUtils.handleResult()).compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseObserver<TodoDesData>(mView, "提交异常", isShowError) {
                    @Override
                    public void onNext(TodoDesData desData) {
                        mView.showData(desData);
                    }
                }));

    }
}
