package com.glh.wanandroid.ui.activity;

import android.app.DatePickerDialog;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.doyo.sdk.activity.BaseNetActivity;
import com.doyo.sdk.mvp.AbstractPresenter;
import com.doyo.sdk.utils.TimeUtils;
import com.doyo.sdk.widget.HeaderBar;
import com.glh.wanandroid.R;
import com.glh.wanandroid.bean.TodoDesData;
import com.glh.wanandroid.core.DataManager;
import com.glh.wanandroid.core.http.ApiFactory;
import com.glh.wanandroid.core.http.HttpHelper;
import com.glh.wanandroid.core.http.HttpHelperImpl;
import com.glh.wanandroid.core.prefs.PreferenceHelper;
import com.glh.wanandroid.core.prefs.PreferenceHelperImpl;
import com.glh.wanandroid.presenter.AddTodoPresenter;
import com.glh.wanandroid.presenter.contract.AddToDoContract;
import com.hjq.toast.ToastUtils;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 磊华集团
 *     time   : 2019/7/1 15:36
 *     desc   : 增加新的todo 事项
 *
 * </pre>
 */
public class AddTodoActivity extends BaseNetActivity<AddTodoPresenter> implements AddToDoContract.View {

    @BindView(R.id.header)
    HeaderBar         mHeader;
    @BindView(R.id.todo_name)
    TextInputEditText mTodoName;
    @BindView(R.id.todo_des)
    TextInputEditText mTodoDes;
    @BindView(R.id.todo_date)
    TextView          mTodoDate;
    @BindView(R.id.save_todo)
    Button            mSaveTodo;
    @BindView(R.id.normal_view)
    LinearLayout      mNormalView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_todo;
    }


    @Override
    protected void reload() {

    }

    @Override
    protected AbstractPresenter initPresenter() {
        PreferenceHelper mPreferenceHelper = new PreferenceHelperImpl();
        HttpHelper mHttpHelper = new HttpHelperImpl(ApiFactory.getApiService());
        DataManager dataManager = new DataManager(mHttpHelper, mPreferenceHelper);
        mPresenter = new AddTodoPresenter(dataManager, this);
        return mPresenter;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        mTodoDate.setText(TimeUtils.date2String(new Date(), "yyyy-MM-dd"));
    }

    @Override
    public void showData(TodoDesData data) {
        ToastUtils.show("新增成功");
        finish();
    }


    @OnClick({R.id.todo_date, R.id.save_todo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.todo_date:
                chooseTime();
                break;
            case R.id.save_todo:
                saveData();
                break;
        }
    }

    private void saveData() {
        mTodoName.setError(null);
        if (TextUtils.isEmpty(mTodoName.getText())) {
            mTodoName.setError(getString(R.string.input_todo_name_toast));
            mTodoName.setFocusable(true);
            mTodoName.setFocusableInTouchMode(true);
            mTodoName.requestFocus();
            return;
        }

        mPresenter.addTodoData(mTodoName.getText().toString(), mTodoDes.getText().toString(),
                mTodoDate.getText().toString(), 1, false);
    }

    private void chooseTime() {

        Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month,
                                          int dayOfMonth) {
                        mTodoDate.setText(String.format("%d-%d-%d", year, month + 1, dayOfMonth));
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(new Date().getTime());
        datePickerDialog.show();
    }
}
