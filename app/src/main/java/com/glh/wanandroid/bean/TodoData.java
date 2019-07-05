package com.glh.wanandroid.bean;

import java.util.List;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 磊华集团
 *     time   : 2019/6/28 16:08
 *     desc   :
 *
 * </pre>
 */
public class TodoData {

    public int                  type;
    public List<DoneListBean>   doneList;
    public List<TodoListBeanXX> todoList;

    public static class DoneListBean {
        public long              date;
        public List<TodoDesData> todoList;
    }

    public static class TodoListBeanXX {
        public long              date;
        public List<TodoDesData> todoList;
    }
}
