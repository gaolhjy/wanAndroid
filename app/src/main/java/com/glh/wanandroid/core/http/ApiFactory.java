package com.glh.wanandroid.core.http;


import com.doyo.sdk.http.RetrofitManager;
import com.glh.wanandroid.core.http.api.GeeksApis;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 永无bug集团
 *     time   : 2019/05/09
 *     desc   : Api的实现类
 *
 * </pre>
 */

public class ApiFactory {

    private static final Object monitor = new Object();

    private static GeeksApis apiService;

    public static GeeksApis getApiService() {
        if (apiService == null) {
            synchronized (monitor) {
                if (apiService == null)
                    apiService = RetrofitManager.getInstance(GeeksApis.HOST).create(GeeksApis.class);
            }
        }
        return apiService;
    }
}
