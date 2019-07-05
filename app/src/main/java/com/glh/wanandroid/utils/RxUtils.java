package com.glh.wanandroid.utils;

import com.doyo.sdk.mvp.ResBaseBean;
import com.doyo.sdk.http.exception.OtherException;
import com.doyo.sdk.utils.BaseRxUtils;
import com.doyo.sdk.utils.CommonUtils;
import com.doyo.sdk.utils.NetUtils;
import com.glh.wanandroid.bean.FeedArticleListData;
import com.glh.wanandroid.bean.LoginData;
import com.glh.wanandroid.bean.TodoDesData;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;


/**
 * Created by chao.qu at 2017/10/20
 *
 * @author quchao
 */

public class RxUtils extends BaseRxUtils {

    /**
     * 退出登录返回结果处理
     *
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
    public static <T> ObservableTransformer<ResBaseBean<T>, T> handleLogoutResult() {
        return httpResponseObservable ->
                httpResponseObservable.flatMap((Function<ResBaseBean<T>, Observable<T>>) baseResponse -> {
                    if (baseResponse.getErrorCode() == ResBaseBean.SUCCESS
                            && NetUtils.isNetworkConnected()) {
                        //创建一个非空数据源，避免onNext()传入null
                        return createData(CommonUtils.cast(new LoginData()));
                    } else {
                        return Observable.error(new OtherException(baseResponse.getErrorCode()));
                    }
                });
    }


    /**
     * 收藏返回结果处理
     *
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
    public static <T> ObservableTransformer<ResBaseBean<T>, T> handleCollectResult() {
        return httpResponseObservable ->
                httpResponseObservable.flatMap((Function<ResBaseBean<T>, Observable<T>>) baseResponse -> {
                    if (baseResponse.getErrorCode() == ResBaseBean.SUCCESS
                            && NetUtils.isNetworkConnected()) {
                        //创建一个非空数据源，避免onNext()传入null
                        return createData(CommonUtils.cast(new FeedArticleListData()));
                    } else {
                        return Observable.error(new OtherException(baseResponse.getErrorCode()));
                    }
                });
    }


    /**
     * 删除todo
     */
    public static <T> ObservableTransformer<ResBaseBean<T>, T> handleTodo() {
        return httpResponseObservable ->
                httpResponseObservable.flatMap((Function<ResBaseBean<T>, Observable<T>>) baseResponse -> {
                    if (baseResponse.getErrorCode() == ResBaseBean.SUCCESS
                            && NetUtils.isNetworkConnected()) {
                        //创建一个非空数据源，避免onNext()传入null
                        return createData(CommonUtils.cast(new TodoDesData()));
                    } else {
                        return Observable.error(new OtherException(baseResponse.getErrorCode()));
                    }
                });
    }

}
