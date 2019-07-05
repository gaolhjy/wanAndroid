package com.glh.wanandroid.ui.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseViewHolder;
import com.doyo.sdk.adapter.BaseCompatAdapter;
import com.doyo.sdk.utils.CommonUtils;
import com.doyo.sdk.utils.DisplayUtils;
import com.glh.wanandroid.R;
import com.glh.wanandroid.bean.FeedArticleData;
import com.glh.wanandroid.bean.NaviData;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

public class NavigationAdapter extends BaseCompatAdapter<NaviData, BaseViewHolder> {


    public NavigationAdapter(int layoutResId, @Nullable List<NaviData> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, NaviData item) {

        if (!TextUtils.isEmpty(item.name)) {
            helper.setText(R.id.item_navigation_tv, item.name);
        }
        TagFlowLayout mTagFlowLayout = helper.getView(R.id.item_navigation_flow_layout);
        List<FeedArticleData> mArticles = item.articles;
        mTagFlowLayout.setAdapter(new TagAdapter<FeedArticleData>(mArticles) {
            @Override
            public View getView(FlowLayout parent, int position, FeedArticleData feedArticleData) {
                TextView tv =
                        (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.flow_layout_tv,
                                mTagFlowLayout, false);
                if (feedArticleData == null) {
                    return null;
                }
                String name = feedArticleData.title;
                tv.setPadding(DisplayUtils.dp2px(10), DisplayUtils.dp2px(10),
                        DisplayUtils.dp2px(10), DisplayUtils.dp2px(10));
                tv.setText(name);
                tv.setTextColor(CommonUtils.randomColor());
                mTagFlowLayout.setOnTagClickListener((view, position1, parent1) -> {
                    startNavigationPager(view, position1, parent, mArticles);
                    return true;
                });
                return tv;
            }
        });

    }

    private void startNavigationPager(View view, int position1, FlowLayout parent2,
                                      List<FeedArticleData> mArticles) {

        Toast.makeText(parent2.getContext(), "跳转", Toast.LENGTH_LONG).show();
    }

}
