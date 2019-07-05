package com.glh.wanandroid.ui.activity;

import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.doyo.sdk.activity.BaseSimpleActivity;
import com.glh.wanandroid.R;
import com.glh.wanandroid.bean.FeedArticleData;
import com.just.agentweb.AgentWeb;

import butterknife.BindView;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 永无bug集团
 *     time   : 2019/5/24 18:01
 *     desc   : 文章详情
 *
 * </pre>
 */
public class ArticleDetailActivity extends BaseSimpleActivity {

    @BindView(R.id.article_detail_toolbar)
    Toolbar     mToolbar;
    @BindView(R.id.article_detail_web_view)
    FrameLayout mWebContent;

    private AgentWeb mAgentWeb;

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_article_detail;
    }


    @Override
    protected void onViewCreated() {

    }

    @Override
    protected void initEventAndData() {

        FeedArticleData data = (FeedArticleData) getIntent().getSerializableExtra("data");

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mWebContent, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(data.projectLink);
    }


    @Override
    public void showLoading() {

    }


}
