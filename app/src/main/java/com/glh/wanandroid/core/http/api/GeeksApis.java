package com.glh.wanandroid.core.http.api;


import com.doyo.sdk.mvp.ResBaseBean;
import com.glh.wanandroid.bean.BannerData;
import com.glh.wanandroid.bean.FeedArticleListData;
import com.glh.wanandroid.bean.KnowleData;
import com.glh.wanandroid.bean.LoginData;
import com.glh.wanandroid.bean.NaviData;
import com.glh.wanandroid.bean.ProjectData;
import com.glh.wanandroid.bean.ProjectListData;
import com.glh.wanandroid.bean.ToDoListData;
import com.glh.wanandroid.bean.TodoDesData;
import com.glh.wanandroid.bean.WxArticleListData;
import com.glh.wanandroid.bean.WxNameListData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author quchao
 * @date 2018/2/12
 */

public interface GeeksApis {

    String HOST = "https://www.wanandroid.com/";

    /**
     * 广告栏
     * http://www.wanandroid.com/banner/json
     *
     * @return 广告栏数据
     */
    @GET("banner/json")
    Observable<ResBaseBean<List<BannerData>>> getBannerData();


    //首页文章列表
    //https://www.wanandroid.com/article/list/1/json (从第0页开始)
    @GET("article/list/{pageNum}/json")
    Observable<ResBaseBean<FeedArticleListData>> getFeedArticleList(@Path("pageNum") int pageNum);

    /**
     * 登录
     * https://www.wanandroid.com/user/login
     * 方法：POST
     * 参数：
     * username，password
     */
    @POST("user/login")
    @FormUrlEncoded
    Observable<ResBaseBean<LoginData>> login(@Field("username") String username, @Field(
            "password") String password);

    /**
     * 退出登录
     * https://www.wanandroid.com/user/logout/json
     * 方法：GET
     */
    @GET("user/logout/json")
    Observable<ResBaseBean<LoginData>> loginOut();

    /**
     * 收藏站内文章
     * https://www.wanandroid.com/lg/collect/1165/json
     * <p>
     * 方法：POST
     * 参数： 文章id，拼接在链接中。
     */
    @POST("/lg/collect/{id}/json")
    Observable<ResBaseBean<FeedArticleListData>> addCollectArticle(@Path("id") String id);


    /**
     * 取消站内文章
     * https://www.wanandroid.com/lg/uncollect_originId/2333/json
     * <p>
     * 方法：POST
     * 参数：
     * id:拼接在链接上
     */
    @POST("lg/uncollect_originId/{id}/json")
    Observable<ResBaseBean<FeedArticleListData>> cancelCollectArticle(@Path("id") String id);

    /**
     * 我的收藏列表
     * https://www.wanandroid.com/lg/collect/list/0/json
     * <p>
     * 方法：GET
     * 参数： 页码：拼接在链接中，从0开始。
     */
    @GET("/lg/collect/list/{pageNum}/json")
    Observable<ResBaseBean<FeedArticleListData>> getMyCollectList(@Path("pageNum") int pageNum);


    /**
     * 取消我的收藏中的收藏
     * https://www.wanandroid.com/lg/uncollect/2805/json
     * <p>
     * 方法：POST
     * 参数：
     * id:拼接在链接上
     * originId:列表页下发，无则为-1
     */
    @POST("lg/uncollect/{id}/json")
    @FormUrlEncoded
    Observable<ResBaseBean<FeedArticleListData>> cancelCollectPageArticle(@Path("id") String id,
                                                                          @Field("originId") int originId);

    /**
     * 知识体系
     * https://www.wanandroid.com/tree/json
     * <p>
     * 方法：GET
     * 参数：无
     */
    @GET("tree/json")
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
    @GET("article/list/{pageNum}/json")
    Observable<ResBaseBean<FeedArticleListData>> getKnowledgeHierarchyDetail(@Path("pageNum") int pageNum, @Query("cid") String id);


    /**
     * 导航
     * https://www.wanandroid.com/navi/json
     * 方法：GET
     * 参数：无
     */
    @GET("navi/json")
    Observable<ResBaseBean<List<NaviData>>> getNaviData();


    /**
     * 项目
     * https://www.wanandroid.com/project/tree/json
     * <p>
     * 方法： GET
     * 参数： 无
     */
    @GET("project/tree/json")
    Observable<ResBaseBean<List<ProjectData>>> getProjectData();


    /**
     * 项目列表数据
     * https://www.wanandroid.com/project/list/1/json?cid=294
     * 方法：GET
     * 参数：
     * cid 分类的id，上面项目分类接口
     * 页码：拼接在链接中，从1开始。
     */
    @GET("project/list/{pageNum}/json")
    Observable<ResBaseBean<ProjectListData>> getProjectListData(@Path("pageNum") int pageNum
            , @Query("cid") String cid);


    /**
     * 获取公众号列表
     * <p>
     * https://wanandroid.com/wxarticle/chapters/json
     * 方法： GET
     */
    @GET("wxarticle/chapters/json")
    Observable<ResBaseBean<List<WxNameListData>>> getWxArticleData();


    /**
     * 查看某个公众号历史数据
     * <p>
     * https://wanandroid.com/wxarticle/list/408/1/json
     * 方法： GET
     */
    @GET("wxarticle/list/{id}/{pager}/json")
    Observable<ResBaseBean<WxArticleListData>> getWxArticleListData(@Path("id") String id,
                                                                    @Path("pager") int pager);


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
    @POST("/lg/todo/listnotdo/{type}/json/{pageNum}")
    Observable<ResBaseBean<ToDoListData>> getTodoNodoData(@Path("type") int type,
                                                          @Path("pageNum") int pageNum);

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
    @POST("/lg/todo/listdone/{type}/json/{pageNum}")
    Observable<ResBaseBean<ToDoListData>> getTodoDoneData(@Path("type") int type,
                                                          @Path("pageNum") int pageNum);


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
    @POST("/lg/todo/add/json")
    Observable<ResBaseBean<TodoDesData>> addTodoData(@Query("title") String title,
                                                     @Query("content") String content,
                                                     @Query("date") String date,
                                                     @Query("type") int type);


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
    @POST("/lg/todo/update/{id}/json")
    Observable<ResBaseBean<TodoDesData>> updateTodoData(@Path("id") String id,
                                                         @Query("title") String title,
                                                         @Query("content") String content,
                                                         @Query("date") String date,
                                                         @Query("type") int type);

    /**
     * 删除一条Todo
     * https://www.wanandroid.com/lg/todo/delete/83/json
     * 方法：POST
     * 参数：
     * id: 拼接在链接上，为唯一标识
     */
    @POST("/lg/todo/delete/{id}/json")
    Observable<ResBaseBean<TodoDesData>> deleteTodoData(@Path("id") String id);


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
    @POST("/lg/todo/done/{id}/json")
    Observable<ResBaseBean<TodoDesData>> changeTodoData(@Path("id") String id,
                                                         @Query("status") int status);


}
