package com.doyo.sdk.mvp;

import java.util.List;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 银江集团
 *     time   : 2019/7/11 09:26
 *     desc   : 返回的列表类型
 *
 * </pre>
 */
public class ResBaseListBean<T> {

    public int     curPage;
    public int     offset;
    public boolean over;
    public int     pageCount;
    public int     size;
    public int     total;
    public List<T> datas;
}
