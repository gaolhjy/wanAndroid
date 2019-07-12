package com.glh.wanandroid.bean;

import com.doyo.sdk.mvp.ResBaseListBean;

import java.util.List;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 银江集团
 *     time   : 2019/7/12 10:03
 *     desc   :
 *
 * </pre>
 */
public class KnowleDataList extends ResBaseListBean {

    public int               courseId;
    public int               id;
    public String            name;
    public int               order;
    public int               parentChapterId;
    public boolean           userControlSetTop;
    public int               visible;
    public List<KnoelwData2> children;
}
