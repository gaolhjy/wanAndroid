package com.glh.wanandroid.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 磊华集团
 *     time   : 2019/6/28 17:00
 *     desc   : 含有 时间头部 的todo 模型
 *
 * </pre>
 */
public class TodoSection extends SectionEntity<TodoDesData> {

    public TodoSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public TodoSection(TodoDesData todoBean) {
        super(todoBean);
    }
}
