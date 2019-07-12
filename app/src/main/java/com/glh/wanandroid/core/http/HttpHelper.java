package com.glh.wanandroid.core.http;

import com.doyo.sdk.mvp.ResBaseBean;
import com.doyo.sdk.mvp.ResBaseListBean;
import com.glh.wanandroid.bean.BannerData;
import com.glh.wanandroid.bean.FeedArticleData;
import com.glh.wanandroid.bean.FeedArticleListData;
import com.glh.wanandroid.bean.KnowleData;
import com.glh.wanandroid.bean.LoginData;
import com.glh.wanandroid.bean.NaviData;
import com.glh.wanandroid.bean.ProjectData;
import com.glh.wanandroid.bean.ToDoListData;
import com.glh.wanandroid.bean.TodoDesData;
import com.glh.wanandroid.bean.WxNameListData;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author quchao
 * @date 2017/11/27
 */

public interface HttpHelper {

    /**
     * 广告栏
     * http://www.wanandroid.com/banner/json
     *
     * @return 取消收藏页面站内文章数据
     */
    Observable<ResBaseBean<List<BannerData>>> getBannerData();


    /**
     * 首页网站列表
     * http://www.wanandroid.com/banner/json
     *
     * @return 取消收藏页面站内文章数据
     */
    Observable<ResBaseBean<FeedArticleListData>> getFeedArticleList(int pageNum);


    /**
     * 登录
     * https://www.wanandroid.com/user/login
     * 方法：POST
     * 参数：
     * username，password
     */
    Observable<ResBaseBean<LoginData>> login(String username, String password);

    /**
     * 退出登录
     * https://www.wanandroid.com/user/logout/json
     * 方法：GET
     */
    Observable<ResBaseBean<LoginData>> loginOut();

    /**
     * 收藏站内文章
     * https://www.wanandroid.com/lg/collect/1165/json
     * <p>
     * 方法：POST
     * 参数： 文章id，拼接在链接中。
     */
    Observable<ResBaseBean<FeedArticleListData>> addCollectArticle(String id);


    /**
     * 取消站内文章
     * https://www.wanandroid.com/lg/uncollect_originId/2333/json
     * <p>
     * 方法：POST
     * 参数：
     * id:拼接在链接上
     */
    Observable<ResBaseBean<FeedArticleListData>> cancelCollectArticle(String id);


    /**
     * 我的收藏列表
     * https://www.wanandroid.com/lg/collect/list/0/json
     * <p>
     * 方法：GET
     * 参数： 页码：拼接在链接中，从0开始。
     */
    Observable<ResBaseBean<FeedArticleListData>> getMyCollectList(int pageNum);


    /**
     * 取消我的收藏中的收藏
     * https://www.wanandroid.com/lg/uncollect/2805/json
     * <p>
     * 方法：POST
     * 参数：
     * id:拼接在链接上
     * originId:列表页下发，无则为-1
     */
    Observable<ResBaseBean<FeedArticleListData>> cancelCollectPageArticle(String id);


    /**
     * 知识体系
     * https://www.wanandroid.com/tree/json
     * <p>
     * 方法：GET
     * 参数：无
     */
    Observable<ResBaseBean<List<KnowleData>>> getKnowledgeHierarchyData();


    /**
     * 知识体系二级列表
     * https://www.wanandroid.com/article/list/0/json?cid=60
     * <p>
     * 方法：GET
     * 参数：
     * cid 分类的id，上述二级目录的id
     * 页码：拼接在链接上，从0开始。
     */
    Observable<ResBaseBean<ResBaseListBean<FeedArticleData>>> getKnowledgeHierarchyDetail(int pageNum,
                                                                             String id);


    /**
     * 导航
     * https://www.wanandroid.com/navi/json
     * 方法：GET
     * 参数：无
     */
    Observable<ResBaseBean<List<NaviData>>> getNaviData();


    /**
     * 项目
     * https://www.wanandroid.com/project/tree/json
     * <p>
     * 方法： GET
     * 参数： 无
     */
    Observable<ResBaseBean<List<ProjectData>>> getProjectData();


    /**
     * 项目列表数据
     * https://www.wanandroid.com/project/list/1/json?cid=294
     * 方法：GET
     * 参数：
     * cid 分类的id，上面项目分类接口
     * 页码：拼接在链接中，从1开始。
     */
    Observable<ResBaseBean<ResBaseListBean<FeedArticleData>>> getProjectListData(int pageNum, String cid);


    /**
     * 获取公众号列表
     * <p>
     * https://wanandroid.com/wxarticle/chapters/json
     * 方法： GET
     */
    Observable<ResBaseBean<List<WxNameListData>>> getWxArticleData();


    /**
     * 查看某个公众号历史数据
     * <p>
     * https://wanandroid.com/wxarticle/list/408/1/json
     * 方法： GET
     */
    Observable<ResBaseBean<ResBaseListBean<FeedArticleData>>> getWxArticleListData(String id, int pager);

    /**
     * 未完成 Todo 列表
     * https://www.wanandroid.com/lg/todo/listnotdo/0/json/1
     * <p>
     * https://www.wanandroid.com/lg/todo/listnotdo/类型/json/页码
     * <p>
     * 方法：POST
     * 参数：
     * 类型：类型拼接在链接上，目前支持0,1,2,3
     * 页码: 拼接在链接上，从1开始；
     */
    Observable<ResBaseBean<ToDoListData>> getTodoNodoData(int type, int pageNum);

    /**
     * 已完成 Todo 列表
     * https://www.wanandroid.com/lg/todo/listdone/0/json/1
     * <p>
     * https://www.wanandroid.com/lg/todo/listdone/类型/json/页码
     * <p>
     * 方法：POST
     * 参数：
     * 类型：类型拼接在链接上，目前支持0,1,2,3
     * 页码: 拼接在链接上，从1开始；
     */
    Observable<ResBaseBean<ToDoListData>> getTodoDoneData(int type, int pageNum);


    /**
     * 新增一条Todo
     * https://www.wanandroid.com/lg/todo/add/json
     * 方法：POST
     * 参数：
     * title: 新增标题
     * content: 新增详情
     * date: 2018-08-01
     * type: 0
     */

    Observable<ResBaseBean<TodoDesData>> addTodoData(String title, String content, String date,
                                                     int type);


    /**
     * 更新一条Todo内容
     * https://www.wanandroid.com/lg/todo/update/83/json
     * 方法：POST
     * 参数：
     * id: 拼接在链接上，为唯一标识
     * title: 更新标题
     * content: 新增详情
     * date: 2018-08-01
     * status: 0 // 0为未完成，1为完成
     * type: 0
     */
    Observable<ResBaseBean<TodoDesData>> updateTodoData(String id, String title, String content,
                                                        String date, int type);

    /**
     * 删除一条Todo
     * https://www.wanandroid.com/lg/todo/delete/83/json
     * 方法：POST
     * 参数：
     * id: 拼接在链接上，为唯一标识
     */
    Observable<ResBaseBean<TodoDesData>> deleteTodoData(String id);


    /**
     * 仅更新完成状态todo
     * <p>
     * https://www.wanandroid.com/lg/todo/done/80/json
     * <p>
     * 方法：POST
     * 参数：
     * id: 拼接在链接上，为唯一标识
     * status: 0或1，传1代表未完成到已完成，反之则反之。
     */
    Observable<ResBaseBean<TodoDesData>> changeTodoData(String id, int status);

}
