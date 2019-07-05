package com.glh.wanandroid.bean;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 永无bug集团
 *     time   : 2019/05/13
 *     desc   :  文章对象
 *
 * </pre>
 */

public class FeedArticleData implements Serializable {

    public String         apkLink;
    public String         author;
    public String         chapterId;
    public String         chapterName;
    public boolean        collect;
    public String         courseId;
    public String         desc;
    public String         envelopePic;
    public boolean        fresh;
    public String         id;
    public String         link;
    public String         niceDate;
    public String         origin;
    public String         prefix;
    public String         projectLink;
    public long           publishTime;
    public String         superChapterId;
    public String         superChapterName;
    public String         title;
    public int            type;
    public String         userId;
    public int            visible;
    public int            zan;
    public List<TagsBean> tags;
}
