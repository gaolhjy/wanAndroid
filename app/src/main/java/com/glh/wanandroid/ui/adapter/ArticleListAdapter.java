package com.glh.wanandroid.ui.adapter;

import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.doyo.sdk.adapter.BaseCompatAdapter;
import com.glh.wanandroid.R;
import com.glh.wanandroid.bean.FeedArticleData;

import java.util.List;


/**
 * @author quchao
 * @date 2018/2/24
 */

public class ArticleListAdapter extends BaseCompatAdapter<FeedArticleData, BaseViewHolder> {

    private boolean isCollectPage;
    private boolean isSearchPage;
    private boolean isNightMode;

    public ArticleListAdapter(int layoutResId, @Nullable List<FeedArticleData> data) {
        super(layoutResId, data);
    }

    public void isCollectPage() {
        isCollectPage = true;
        notifyDataSetChanged();
    }

    public void isSearchPage() {
        isSearchPage = true;
        notifyDataSetChanged();
    }

    public void isNightMode(boolean isNightMode) {
        this.isNightMode = isNightMode;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder helper, FeedArticleData article) {

        if (!TextUtils.isEmpty(article.title)) {
            helper.setText(R.id.item_search_pager_title, Html.fromHtml(article.title));
        }
        if (article.collect || isCollectPage) {
            helper.setImageResource(R.id.item_search_pager_like_iv, R.drawable.icon_like);
        } else {
            helper.setImageResource(R.id.item_search_pager_like_iv, R.drawable.icon_like_article_not_selected);
        }
        if (!TextUtils.isEmpty(article.author)) {
            helper.setText(R.id.item_search_pager_author, article.author);
        }
        setTag(helper, article);
        if (!TextUtils.isEmpty(article.chapterName)) {
            String classifyName = article.superChapterName + " / " + article.chapterName;
            if (isCollectPage) {
                helper.setText(R.id.item_search_pager_chapterName, article.chapterName);
            } else {
                helper.setText(R.id.item_search_pager_chapterName, classifyName);
            }
        }
        if (!TextUtils.isEmpty(article.niceDate)) {
            helper.setText(R.id.item_search_pager_niceDate, article.niceDate);
        }
        if (isSearchPage) {
            CardView cardView = helper.getView(R.id.item_search_pager_group);
            cardView.setForeground(null);
            if (isNightMode) {
                cardView.setBackground(ContextCompat.getDrawable(mContext, R.color.card_color));
            } else {
                cardView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.selector_search_item_bac));
            }
        }

        helper.addOnClickListener(R.id.item_search_pager_chapterName);
        helper.addOnClickListener(R.id.item_search_pager_like_iv);
        helper.addOnClickListener(R.id.item_search_pager_tag_red_tv);

    }

    private void setTag(BaseViewHolder helper, FeedArticleData article) {
        helper.getView(R.id.item_search_pager_tag_green_tv).setVisibility(View.GONE);
        helper.getView(R.id.item_search_pager_tag_red_tv).setVisibility(View.GONE);
        if (isCollectPage) {
            return;
        }
        if (article.superChapterName.contains(mContext.getString(R.string.open_project))) {
            setRedTag(helper, R.string.project);
        }

        if (article.superChapterName.contains(mContext.getString(R.string.navigation))) {
            setRedTag(helper, R.string.navigation);
        }

        if (article.niceDate.contains(mContext.getString(R.string.minute))
                || article.niceDate.contains(mContext.getString(R.string.hour))
                || article.niceDate.contains(mContext.getString(R.string.one_day))) {
            helper.getView(R.id.item_search_pager_tag_green_tv).setVisibility(View.VISIBLE);
            helper.setText(R.id.item_search_pager_tag_green_tv, R.string.text_new);
            helper.setTextColor(R.id.item_search_pager_tag_green_tv, ContextCompat.getColor(mContext, R.color.light_green));
            helper.setBackgroundRes(R.id.item_search_pager_tag_green_tv, R.drawable.shape_tag_green_background);
        }
    }

    private void setRedTag(BaseViewHolder helper, @StringRes int tagName) {
        helper.getView(R.id.item_search_pager_tag_red_tv).setVisibility(View.VISIBLE);
        helper.setText(R.id.item_search_pager_tag_red_tv, tagName);
        helper.setTextColor(R.id.item_search_pager_tag_red_tv, ContextCompat.getColor(mContext, R.color.light_deep_red));
        helper.setBackgroundRes(R.id.item_search_pager_tag_red_tv, R.drawable.selector_tag_red_background);
    }



}
