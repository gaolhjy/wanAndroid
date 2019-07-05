package com.glh.wanandroid.ui.adapter;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.glh.wanandroid.R;
import com.glh.wanandroid.bean.TodoSection;

import java.util.List;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 磊华集团
 *     time   : 2019/6/28 16:58
 *     desc   : 待办清单  完成清单适配器
 *
 * </pre>
 */
public class ToDoAdapter extends BaseSectionQuickAdapter<TodoSection, BaseViewHolder> {

    private boolean isDone;

    public ToDoAdapter(int layoutResId, int sectionHeadResId, List<TodoSection> data, boolean isDone) {
        super(layoutResId, sectionHeadResId, data);
        this.isDone = isDone;
    }

    @Override
    protected void convertHead(BaseViewHolder helper, TodoSection item) {
        helper.setText(R.id.todo_head, item.header);
        if (isDone) {
            helper.setTextColor(R.id.todo_head, mContext.getResources().getColor(R.color.done_todo_date));
        } else {
            helper.setTextColor(R.id.todo_head, mContext.getResources().getColor(R.color.colorPrimary));
        }
    }

    @Override
    protected void convert(BaseViewHolder helper, TodoSection item) {
        helper.setText(R.id.item_name, item.t.title);
        if (TextUtils.isEmpty(item.t.content)) {
            helper.setGone(R.id.item_des, false);
        } else {
            helper.setGone(R.id.item_des, true);
            helper.setText(R.id.item_des, item.t.content);
        }

        if (isDone) {
            helper.setGone(R.id.item_done_time, true);
            helper.setText(R.id.item_done_time, String.format(mContext.getResources().getString(R.string.done_todo_date),item.t.completeDateStr));
            helper.setImageResource(R.id.item_complete, R.drawable.cancel_todo);
        } else {
            helper.setGone(R.id.item_done_time, false);
            helper.setImageResource(R.id.item_complete, R.drawable.complete_todo);
        }

        helper.addOnClickListener(R.id.item_complete);
        helper.addOnClickListener(R.id.item_delete);
    }
}
