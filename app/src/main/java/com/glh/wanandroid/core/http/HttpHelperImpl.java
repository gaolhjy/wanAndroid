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
import com.glh.wanandroid.core.http.api.GeeksApis;

import java.util.List;

import io.reactivex.Observable;


/**
 * 对外隐藏进行网络请求的实现细节
 *
 * @author quchao
 * @date 2017/11/27
 */

public class HttpHelperImpl implements HttpHelper {

    private GeeksApis mGeeksApis;

    public HttpHelperImpl(GeeksApis geeksApis) {
        mGeeksApis = geeksApis;
    }


    @Override
    public Observable<ResBaseBean<List<BannerData>>> getBannerData() {
        return mGeeksApis.getBannerData();
    }

    @Override
    public Observable<ResBaseBean<FeedArticleListData>> getFeedArticleList(int pageNum) {
        return mGeeksApis.getFeedArticleList(pageNum);
    }

    @Override
    public Observable<ResBaseBean<LoginData>> login(String username, String password) {
        return mGeeksApis.login(username, password);
    }

    @Override
    public Observable<ResBaseBean<LoginData>> loginOut() {
        return mGeeksApis.loginOut();
    }

    @Override
    public Observable<ResBaseBean<FeedArticleListData>> addCollectArticle(String id) {
        return mGeeksApis.addCollectArticle(id);
    }

    @Override
    public Observable<ResBaseBean<FeedArticleListData>> cancelCollectArticle(String id) {
        return mGeeksApis.cancelCollectArticle(id);
    }

    @Override
    public Observable<ResBaseBean<FeedArticleListData>> getMyCollectList(int pageNum) {
        return mGeeksApis.getMyCollectList(pageNum);
    }

    @Override
    public Observable<ResBaseBean<FeedArticleListData>> cancelCollectPageArticle(String id) {
        return mGeeksApis.cancelCollectPageArticle(id, -1);
    }

    @Override
    public Observable<ResBaseBean<List<KnowleData>>> getKnowledgeHierarchyData() {
        return mGeeksApis.getKnowledgeHierarchyData();
    }

    @Override
    public Observable<ResBaseBean<ResBaseListBean<FeedArticleData>>> getKnowledgeHierarchyDetail(int pageNum,
                                                                                    String id) {
        return mGeeksApis.getKnowledgeHierarchyDetail(pageNum, id);
    }

    @Override
    public Observable<ResBaseBean<List<NaviData>>> getNaviData() {
        return mGeeksApis.getNaviData();
    }

    @Override
    public Observable<ResBaseBean<List<ProjectData>>> getProjectData() {
        return mGeeksApis.getProjectData();
    }

    @Override
    public Observable<ResBaseBean<ResBaseListBean<FeedArticleData>>> getProjectListData(int pageNum, String cid) {
        return mGeeksApis.getProjectListData(pageNum, cid);
    }

    @Override
    public Observable<ResBaseBean<List<WxNameListData>>> getWxArticleData() {
        return mGeeksApis.getWxArticleData();
    }

    @Override
    public Observable<ResBaseBean<ResBaseListBean<FeedArticleData>>> getWxArticleListData(String id, int pager) {
        return mGeeksApis.getWxArticleListData(id, pager);
    }

    @Override
    public Observable<ResBaseBean<ToDoListData>> getTodoNodoData(int type, int pageNum) {
        return mGeeksApis.getTodoNodoData(type, pageNum);
    }

    @Override
    public Observable<ResBaseBean<ToDoListData>> getTodoDoneData(int type, int pageNum) {
        return mGeeksApis.getTodoDoneData(type, pageNum);
    }

    @Override
    public Observable<ResBaseBean<TodoDesData>> addTodoData(String title, String content,
                                                            String date, int type) {
        return mGeeksApis.addTodoData(title, content, date, type);
    }

    @Override
    public Observable<ResBaseBean<TodoDesData>> updateTodoData(String id, String title,
                                                                String content, String date,
                                                                int type) {
        return mGeeksApis.updateTodoData(id, title, content, date, type);
    }

    @Override
    public Observable<ResBaseBean<TodoDesData>> deleteTodoData(String id) {
        return mGeeksApis.deleteTodoData(id);
    }

    @Override
    public Observable<ResBaseBean<TodoDesData>> changeTodoData(String id, int status) {
        return mGeeksApis.changeTodoData(id, status);
    }


}
