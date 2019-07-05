package com.glh.wanandroid.bean;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 磊华集团
 *     time   : 2019/05/21
 *     desc   : 知识体系返回的数据
 *
 * </pre>
 */

public class KnowleData implements Serializable {

    public List<KnowleData> children;
    public String           courseId;
    public String           id;
    public String           name;
    public int              order;
    public int              parentChapterId;
    public int              visible;
}
