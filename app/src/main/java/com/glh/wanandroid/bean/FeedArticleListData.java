package com.glh.wanandroid.bean;

import java.util.List;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 磊华集团
 *     time   : 2019/05/09
 *     desc   : 文章列表 数据模型
 *
 * </pre>
 */

public class FeedArticleListData {

    public int                   curPage;
    public int                   offset;
    public boolean               over;
    public int                   pageCount;
    public int                   size;
    public int                   total;
    public List<FeedArticleData> datas;

}
