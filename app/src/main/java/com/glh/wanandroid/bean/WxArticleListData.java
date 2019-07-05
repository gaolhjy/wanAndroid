package com.glh.wanandroid.bean;

import java.util.List;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 永无bug集团
 *     time   : 2019/5/24 11:24
 *     desc   : 查看某个公众号历史数据
 *
 * </pre>
 */
public class WxArticleListData {

    public int                   curPage;
    public int                   offset;
    public boolean               over;
    public int                   pageCount;
    public int                   size;
    public int                   total;
    public List<FeedArticleData> datas;

}
