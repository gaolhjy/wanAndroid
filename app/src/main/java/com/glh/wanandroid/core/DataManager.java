package com.glh.wanandroid.core;


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
import com.glh.wanandroid.core.http.HttpHelper;
import com.glh.wanandroid.core.prefs.PreferenceHelper;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author quchao
 * @date 2017/11/27
 */

public class DataManager implements PreferenceHelper, HttpHelper {

    private HttpHelper       mHttpHelper;
    //    private DbHelper mDbHelper;
    private PreferenceHelper mPreferenceHelper;

    public DataManager(HttpHelper httpHelper, PreferenceHelper preferencesHelper) {
        this.mHttpHelper = httpHelper;

        //        mDbHelper = dbHelper;
        mPreferenceHelper = preferencesHelper;
    }


    @Override
    public void setLoginAccount(String account) {
        mPreferenceHelper.setLoginAccount(account);
    }

    @Override
    public void setLoginPassword(String password) {
        mPreferenceHelper.setLoginPassword(password);
    }

    @Override
    public String getLoginAccount() {
        return mPreferenceHelper.getLoginAccount();
    }

    @Override
    public String getLoginPassword() {
        return mPreferenceHelper.getLoginPassword();
    }

    @Override
    public void setLoginStatus(boolean isLogin) {
        mPreferenceHelper.setLoginStatus(isLogin);
    }

    @Override
    public boolean getLoginStatus() {
        return mPreferenceHelper.getLoginStatus();
    }

    @Override
    public void setCookie(String domain, String cookie) {
        mPreferenceHelper.setCookie(domain, cookie);
    }

    @Override
    public String getCookie(String domain) {
        return mPreferenceHelper.getCookie(domain);
    }

    @Override
    public void setCurrentPage(int position) {
        mPreferenceHelper.setCurrentPage(position);
    }

    @Override
    public int getCurrentPage() {
        return mPreferenceHelper.getCurrentPage();
    }

    @Override
    public void setProjectCurrentPage(int position) {
        mPreferenceHelper.setProjectCurrentPage(position);
    }

    @Override
    public int getProjectCurrentPage() {
        return mPreferenceHelper.getProjectCurrentPage();
    }

    @Override
    public boolean getAutoCacheState() {
        return mPreferenceHelper.getAutoCacheState();
    }

    @Override
    public boolean getNoImageState() {
        return mPreferenceHelper.getNoImageState();
    }

    @Override
    public boolean getNightModeState() {
        return mPreferenceHelper.getNightModeState();
    }

    @Override
    public void setNightModeState(boolean b) {
        mPreferenceHelper.setNightModeState(b);
    }

    @Override
    public void setNoImageState(boolean b) {
        mPreferenceHelper.setNoImageState(b);
    }

    @Override
    public void setAutoCacheState(boolean b) {
        mPreferenceHelper.setAutoCacheState(b);
    }

    @Override
    public Observable<ResBaseBean<List<BannerData>>> getBannerData() {
        return mHttpHelper.getBannerData();
    }

    @Override
    public Observable<ResBaseBean<FeedArticleListData>> getFeedArticleList(int pageNum) {
        return mHttpHelper.getFeedArticleList(pageNum);
    }

    @Override
    public Observable<ResBaseBean<LoginData>> login(String username, String password) {
        return mHttpHelper.login(username, password);
    }

    @Override
    public Observable<ResBaseBean<LoginData>> loginOut() {
        return mHttpHelper.loginOut();
    }

    @Override
    public Observable<ResBaseBean<FeedArticleListData>> addCollectArticle(String id) {
        return mHttpHelper.addCollectArticle(id);
    }

    @Override
    public Observable<ResBaseBean<FeedArticleListData>> cancelCollectArticle(String id) {
        return mHttpHelper.cancelCollectArticle(id);
    }

    @Override
    public Observable<ResBaseBean<FeedArticleListData>> getMyCollectList(int pageNum) {
        return mHttpHelper.getMyCollectList(pageNum);
    }

    @Override
    public Observable<ResBaseBean<FeedArticleListData>> cancelCollectPageArticle(String id) {
        return mHttpHelper.cancelCollectPageArticle(id);
    }

    @Override
    public Observable<ResBaseBean<List<KnowleData>>> getKnowledgeHierarchyData() {
        return mHttpHelper.getKnowledgeHierarchyData();
    }

    @Override
    public Observable<ResBaseBean<ResBaseListBean<FeedArticleData>>> getKnowledgeHierarchyDetail(int pageNum,
                                                                                    String id) {
        return mHttpHelper.getKnowledgeHierarchyDetail(pageNum, id);
    }

    @Override
    public Observable<ResBaseBean<List<NaviData>>> getNaviData() {
        return mHttpHelper.getNaviData();
    }

    @Override
    public Observable<ResBaseBean<List<ProjectData>>> getProjectData() {
        return mHttpHelper.getProjectData();
    }

    @Override
    public Observable<ResBaseBean<ResBaseListBean<FeedArticleData>>> getProjectListData(int pageNum, String cid) {
        return mHttpHelper.getProjectListData(pageNum, cid);
    }

    @Override
    public Observable<ResBaseBean<List<WxNameListData>>> getWxArticleData() {
        return mHttpHelper.getWxArticleData();
    }

    @Override
    public Observable<ResBaseBean<ResBaseListBean<FeedArticleData>>> getWxArticleListData(String id, int pager) {
        return mHttpHelper.getWxArticleListData(id, pager);
    }

    @Override
    public Observable<ResBaseBean<ToDoListData>> getTodoNodoData(int type, int pageNum) {
        return mHttpHelper.getTodoNodoData(type, pageNum);
    }

    @Override
    public Observable<ResBaseBean<ToDoListData>> getTodoDoneData(int type, int pageNum) {
        return mHttpHelper.getTodoDoneData(type, pageNum);
    }

    @Override
    public Observable<ResBaseBean<TodoDesData>> addTodoData(String title, String content,
                                                            String date, int type) {
        return mHttpHelper.addTodoData(title, content, date, type);
    }

    @Override
    public Observable<ResBaseBean<TodoDesData>> updateTodoData(String id, String title,
                                                                String content, String date,
                                                                int type) {
        return mHttpHelper.updateTodoData(id, title, content, date, type);
    }

    @Override
    public Observable<ResBaseBean<TodoDesData>> deleteTodoData(String id) {
        return mHttpHelper.deleteTodoData(id);
    }

    @Override
    public Observable<ResBaseBean<TodoDesData>> changeTodoData(String id, int status) {
        return mHttpHelper.changeTodoData(id, status);
    }

}
