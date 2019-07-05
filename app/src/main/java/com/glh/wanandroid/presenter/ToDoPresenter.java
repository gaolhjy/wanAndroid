package com.glh.wanandroid.presenter;

import com.doyo.sdk.rx.BaseObserver;
import com.glh.wanandroid.BasePresenter;
import com.glh.wanandroid.R;
import com.glh.wanandroid.bean.ToDoListData;
import com.glh.wanandroid.bean.TodoDesData;
import com.glh.wanandroid.core.DataManager;
import com.glh.wanandroid.global.AppContext;
import com.glh.wanandroid.presenter.contract.ToDoContract;
import com.glh.wanandroid.presenter.contract.WxArticleContract;
import com.glh.wanandroid.utils.RxUtils;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 磊华集团
 *     time   : 2019/05/23
 *     desc   : 项目二级
 *
 * </pre>
 */

public class ToDoPresenter extends BasePresenter<ToDoContract.View>
        implements ToDoContract.Presenter {

    private DataManager            mDataManager;
    private WxArticleContract.View view;

    public ToDoPresenter(DataManager dataManager, ToDoContract.View view) {
        super(dataManager);
        this.mDataManager = dataManager;
        this.mView = view;
    }


    @Override
    public void getTodoData(int pager, int type, boolean isShowError) {

        //        Observable<ResBaseBean<ToDoListData>> todoNodoData1 = mDataManager
        //        .getTodoNodoData(1,
        //                pager);
        //        Observable<ResBaseBean<ToDoListData>> todoNodoData2 = mDataManager
        //        .getTodoNodoData(2,
        //                pager);
        //        Observable<ResBaseBean<ToDoListData>> todoNodoData3 = mDataManager
        //        .getTodoNodoData(3,
        //                pager);
        //
        //
        //        addSubscribe(Observable.zip(todoNodoData1, todoNodoData2,
        //                new BiFunction<ResBaseBean<ToDoListData>, ResBaseBean<ToDoListData>,
        //                        List<TodoDesData>>() {
        //
        //                    @Override
        //                    public List<TodoDesData> apply(ResBaseBean<ToDoListData>
        //                    toDoListDataResBaseBean,
        //                                              ResBaseBean<ToDoListData>
        //                                              toDoListDataResBaseBean2) throws Exception {
        //                        List<TodoDesData> data = new ArrayList<>();
        //                        List<TodoDesData> datas = toDoListDataResBaseBean.getData().datas;
        //                        data.addAll(datas);
        //                        return data;
        //                    }
        //                })
        //                .compose(RxUtils.rxSchedulerHelper())
        //                .compose(RxUtils.handleResult())
        //                .subscribeWith(new BaseObserver<ToDoListData>(mView,
        //                        AppContext.getInstance().getString(R.string
        //                        .failed_to_obtain_wx_data),
        //                        isShowError) {
        //                    @Override
        //                    public void onNext(ToDoListData todoBean) {
        //                        if (todoBean.datas.size() > 0) {
        //                            mView.showData(todoBean);
        //                        } else {
        //                            mView.showNoMoreData();
        //                        }
        //                    }
        //                }));


        addSubscribe(mDataManager.getTodoNodoData(type, pager)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<ToDoListData>(mView,
                        AppContext.getInstance().getString(R.string.failed_to_obtain_todo_data),
                        isShowError) {
                    @Override
                    public void onNext(ToDoListData todoBean) {
                        if (todoBean.datas.size() > 0) {
                            mView.showData(todoBean);
                        } else {
                            mView.showNoMoreData();
                        }
                    }
                }));
    }

    @Override
    public void getCompleData(int pager, int type, boolean isShowError) {

        addSubscribe(mDataManager.getTodoDoneData(type, pager)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<ToDoListData>(mView,
                        AppContext.getInstance().getString(R.string.failed_to_obtain_todo_data),
                        isShowError) {
                    @Override
                    public void onNext(ToDoListData todoBean) {
                        if (todoBean.datas.size() > 0) {
                            mView.showData(todoBean);
                        } else {
                            mView.showNoMoreData();
                        }
                    }
                }));
    }

    @Override
    public void deleteData(String id, int position) {
        addSubscribe(mDataManager.deleteTodoData(id)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleTodo())
                .subscribeWith(new BaseObserver<TodoDesData>(mView,
                        AppContext.getInstance().getString(R.string.failed_to_obtain_todo_data),
                        false) {
                    @Override
                    public void onNext(TodoDesData todoDesData) {
                        mView.deleteData(position);
                    }
                }));
    }

    @Override
    public void changeData(String id, int status, int position) {
        addSubscribe(mDataManager.changeTodoData(id, status)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleTodo()).subscribeWith(new BaseObserver<TodoDesData>(mView,
                        AppContext.getInstance().getString(R.string.failed_to_obtain_todo_data),
                        false) {
                    @Override
                    public void onNext(TodoDesData todoDesData) {
                        mView.changeData(position);
                    }
                }));
    }

}
