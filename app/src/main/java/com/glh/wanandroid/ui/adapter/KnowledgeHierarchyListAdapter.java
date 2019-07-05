package com.glh.wanandroid.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.doyo.sdk.adapter.BaseCompatAdapter;
import com.doyo.sdk.utils.CommonUtils;
import com.glh.wanandroid.R;
import com.glh.wanandroid.bean.KnowleData;

import java.util.List;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 磊华集团
 *     time   : 2019/05/21
 *     desc   :
 *
 * </pre>
 */

public class KnowledgeHierarchyListAdapter extends BaseCompatAdapter<KnowleData,
        BaseViewHolder> {


    public KnowledgeHierarchyListAdapter(int layoutResId, @Nullable List<KnowleData> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, KnowleData item) {

        if (item.name == null) {
            return;
        }
        helper.setText(R.id.item_knowledge_hierarchy_title, item.name);
        helper.setTextColor(R.id.item_knowledge_hierarchy_title, CommonUtils.randomColor());
        if (item.children == null) {
            return;
        }


        StringBuilder content = new StringBuilder();

        for (KnowleData data : item.children) {
            content.append(data.name).append("   ");
        }
        helper.setText(R.id.item_knowledge_hierarchy_content, content.toString());
    }

}
