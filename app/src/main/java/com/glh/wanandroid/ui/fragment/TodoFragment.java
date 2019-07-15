package com.glh.wanandroid.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.doyo.sdk.adapter.BaseCompatAdapter;
import com.doyo.sdk.fragment.BaseListFragment;
import com.doyo.sdk.mvp.AbstractPresenter;
import com.doyo.sdk.utils.JumpUtils;
import com.doyo.sdk.utils.NetUtils;
import com.doyo.sdk.widget.HeaderBar;
import com.glh.wanandroid.R;
import com.glh.wanandroid.bean.ToDoListData;
import com.glh.wanandroid.bean.TodoDesData;
import com.glh.wanandroid.bean.TodoSection;
import com.glh.wanandroid.constant.Constants;
import com.glh.wanandroid.core.DataManager;
import com.glh.wanandroid.core.http.ApiFactory;
import com.glh.wanandroid.core.http.HttpHelper;
import com.glh.wanandroid.core.http.HttpHelperImpl;
import com.glh.wanandroid.core.prefs.PreferenceHelper;
import com.glh.wanandroid.core.prefs.PreferenceHelperImpl;
import com.glh.wanandroid.presenter.ToDoPresenter;
import com.glh.wanandroid.presenter.contract.ToDoContract;
import com.glh.wanandroid.ui.activity.AddTodoActivity;
import com.glh.wanandroid.ui.adapter.ToDoAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import butterknife.BindView;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 永无bug集团
 *     time   : 2019/6/28 14:59
 *     desc   : todo的fragment类型
 *
 * </pre>
 */
public class TodoFragment extends BaseListFragment<ToDoPresenter,ToDoAdapter,ToDoListData> implements ToDoContract.View,
        HeaderBar.OnCustonClickListener {

    @BindView(R.id.header)
    HeaderBar          mHeaderBar;
    @BindView(R.id.recycler_view)
    RecyclerView       mRecyclerView;
    @BindView(R.id.normal_view)
    SmartRefreshLayout mRefreshLayout;

    private boolean      isDone;
    private ToDoListData mTodoListBean;
    private ToDoAdapter  mAdapter;


    public static TodoFragment newInstance(boolean isDone) {
        Bundle args = new Bundle();
        args.putBoolean(Constants.ARG_PARAM1, isDone);
        TodoFragment fragment = new TodoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_todo;
    }

    @Override
    public void reload() {
        currentPage = 1;
        isRefresh = true;
        if (isDone) {   //完成事项
            mPresenter.getCompleData(currentPage, 1, false);
        } else {
            mPresenter.getTodoData(currentPage, 1, false);
        }
    }

    @Override
    protected AbstractPresenter initPresenter() {
        PreferenceHelper mPreferenceHelper = new PreferenceHelperImpl();
        HttpHelper mHttpHelper = new HttpHelperImpl(ApiFactory.getApiService());
        DataManager dataManager = new DataManager(mHttpHelper, mPreferenceHelper);
        mPresenter = new ToDoPresenter(dataManager, this);
        return mPresenter;
    }


    @Override
    protected void lazyFetchData() {
        super.lazyFetchData();
        Bundle bundle = getArguments();
        mHeaderBar.findViewById(R.id.btn_top_back).setVisibility(View.INVISIBLE);
        if (bundle != null) {
            isDone = bundle.getBoolean(Constants.ARG_PARAM1);
            if (isDone) {
                mHeaderBar.setTitle("完成事项");
            } else {
                mHeaderBar.setTitle("代办事项");
            }
        }

        mAdapter.setEnableLoadMore(false);
        setRefresh();

        currentPage = 1;
        if (isDone) {   //完成事项
            mPresenter.getCompleData(currentPage, 1, true);
        } else {
            mPresenter.getTodoData(currentPage, 1, true);
            mHeaderBar.setRightBtnSrc(R.drawable.icon_right_write);
            mHeaderBar.setOnRightBtnClickListener(this);
        }

        if (NetUtils.isNetworkConnected()) {
            showLoading();
        }
    }

    @Override
    protected void getInitData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            isDone = bundle.getBoolean(Constants.ARG_PARAM1);
        }
    }

    private void setRefresh() {

        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            currentPage = 1;
            isRefresh = true;
            mAdapter.setEnableLoadMore(false);
            if (isDone) {   //完成事项
                mPresenter.getCompleData(currentPage, 1, false);
            } else {
                mPresenter.getTodoData(currentPage, 1, false);
            }
            refreshLayout.finishRefresh(1000);
        });
    }



    @Override
    protected BaseCompatAdapter getAbstractAdapter() {

        mTodoListBean = new ToDoListData();
        mAdapter = new ToDoAdapter(R.layout.todo_item_view, R.layout.todo_item_head,
                null, isDone);
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            TodoSection bean = (TodoSection) adapter.getData().get(position);
            if (!bean.isHeader) {
                TodoDesData desData = bean.t;
                itemDo(view, desData, position);
            }
        });
        return mAdapter;
    }



    private void itemDo(View view, TodoDesData desData, int position) {
        int id = desData.id;
        int status = desData.status;
        switch (view.getId()) {
            case R.id.item_delete:
                mPresenter.deleteData(String.valueOf(id), position);
                break;
            case R.id.item_complete:
                mPresenter.changeData(String.valueOf(id), status, position);
                break;
        }
    }

    private List<TodoSection> getTodoSectionData(List<TodoDesData> datas) {

        List<TodoSection> todoSections = new ArrayList<>();
        LinkedHashSet<String> dates = new LinkedHashSet<>();
        for (TodoDesData todoDesBean : datas) {
            dates.add(todoDesBean.dateStr);
        }
        for (String date : dates) {
            TodoSection todoSectionHead = new TodoSection(true, date);
            todoSections.add(todoSectionHead);
            for (TodoDesData todoDesBean : datas) {
                if (TextUtils.equals(date, todoDesBean.dateStr)) {
                    TodoSection todoSectionContent = new TodoSection(todoDesBean);
                    todoSections.add(todoSectionContent);
                }
            }
        }
        return todoSections;
    }


    @Override
    public void showData(ToDoListData data) {

        mTodoListBean = data;
        mRecyclerView.setVisibility(View.VISIBLE);

        if (currentPage < data.pageCount) {
            mAdapter.loadMoreEnd(isRefresh);
        } else {
            mAdapter.loadMoreComplete();
        }

        if (isRefresh) {
            mAdapter.setNewData(getTodoSectionData(mTodoListBean.datas));
            mAdapter.setEnableLoadMore(true);
        } else {
            mAdapter.addData(getTodoSectionData(mTodoListBean.datas));
        }

        showNormal();

    }


    @Override
    public void changeData(int donePosition) {

        if (donePosition != -1) {
            if (mAdapter.getData().get(donePosition - 1).isHeader && (mAdapter.getData().size() == donePosition + 2 || mAdapter.getData().get(donePosition + 1).isHeader)) {
                mAdapter.getData().remove(donePosition - 1);
                mAdapter.getData().remove(donePosition - 1);
                mAdapter.notifyItemRangeRemoved(donePosition - 1, 2);
            } else {
                mAdapter.getData().remove(donePosition);
                mAdapter.notifyItemRemoved(donePosition);
            }

            showToast(getString(isDone ? R.string.notdo_todo_success : R.string.done_todo_success));
        }

    }

    @Override
    public void deleteData(int position) {

        if (mAdapter.getData().get(position - 1).isHeader && (mAdapter.getData().size() == position + 2 || mAdapter.getData().get(position + 1).isHeader)) {
            mAdapter.getData().remove(position - 1);
            mAdapter.getData().remove(position - 1);
            mAdapter.notifyItemRangeRemoved(position - 1, 2);
        } else {
            mAdapter.getData().remove(position);
            mAdapter.notifyItemRemoved(position);
        }
    }

    @Override
    public void onLoadMoreRequested() {
        isRefresh = false;
        currentPage++;
        mAdapter.setEnableLoadMore(true);
        if (isDone) {   //完成事项
            mPresenter.getCompleData(currentPage, 1, false);
        } else {
            mPresenter.getTodoData(currentPage, 1, false);
            mHeaderBar.setRightBtnSrc(R.drawable.icon_right_write);
            mHeaderBar.setOnRightBtnClickListener(this);
        }
    }

    @Override
    protected void getData(int currentPage, boolean isShow, String id) {
    }

    @Override
    public void customClick(View v) {
        JumpUtils.JumpToActivity(_mActivity, AddTodoActivity.class);
    }
}
