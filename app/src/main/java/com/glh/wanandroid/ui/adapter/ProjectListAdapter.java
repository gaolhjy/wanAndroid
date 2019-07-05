package com.glh.wanandroid.ui.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.doyo.sdk.adapter.BaseCompatAdapter;
import com.glh.wanandroid.R;
import com.glh.wanandroid.bean.FeedArticleData;
import com.glh.wanandroid.utils.ImageLoader;

import java.util.List;


/**
 * @author quchao
 * @date 2018/2/24
 */
public class ProjectListAdapter extends BaseCompatAdapter<FeedArticleData, BaseViewHolder> {

    public ProjectListAdapter(int layoutResId, @Nullable List<FeedArticleData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FeedArticleData item) {

        if (!TextUtils.isEmpty(item.envelopePic)) {
            ImageLoader.load(mContext, item.envelopePic, helper.getView(R.id.item_project_list_iv));
        }
        if (!TextUtils.isEmpty(item.title)) {
            helper.setText(R.id.item_project_list_title_tv, item.title);
        }
        if (!TextUtils.isEmpty(item.desc)) {
            helper.setText(R.id.item_project_list_content_tv, item.desc);
        }
        if (!TextUtils.isEmpty(item.niceDate)) {
            helper.setText(R.id.item_project_list_time_tv, item.niceDate);
        }
        if (!TextUtils.isEmpty(item.author)) {
            helper.setText(R.id.item_project_list_author_tv, item.author);
        }
        if (!TextUtils.isEmpty(item.apkLink)) {
            helper.getView(R.id.item_project_list_install_tv).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.item_project_list_install_tv).setVisibility(View.GONE);
        }

        helper.addOnClickListener(R.id.item_project_list_install_tv);
    }
}
