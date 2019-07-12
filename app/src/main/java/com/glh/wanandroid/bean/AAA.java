package com.glh.wanandroid.bean;

import java.util.List;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 银江集团
 *     time   : 2019/7/12 14:36
 *     desc   :
 *
 * </pre>
 */
public class AAA {

    public List<ChildrenList> data;

    public static class ChildrenList {

        public int                courseId;
        public int                id;
        public String             name;
        public int                order;
        public int                parentChapterId;
        public boolean            userControlSetTop;
        public int                visible;
        public List<ChildrenBean> children;

        public static class ChildrenBean {

            public int     courseId;
            public int     id;
            public String  name;
            public int     order;
            public int     parentChapterId;
            public boolean userControlSetTop;
            public int     visible;
            public List<?> children;

        }
    }
}
