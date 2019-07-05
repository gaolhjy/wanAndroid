package com.doyo.sdk.http;

import android.util.Log;

import com.doyo.sdk.global.GlobalApplication;
import com.doyo.sdk.utils.FileUtil;
import com.doyo.sdk.utils.NetUtils;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.annotations.NonNull;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 磊华集团
 *     time   : 2019/05/09
 *     desc   : Retrofit的管理类
 *
 * </pre>
 */

public class RetrofitManager {

    private static RetrofitManager instance;
    private static Retrofit        retrofit;

    private HttpLoggingInterceptor mLogInterceptor =
            new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.e("glh-okhttp", "log: " + message);
                }
            });


    private RetrofitManager(String baseUrl) {
        Gson gson = new GsonBuilder().serializeNulls().setLenient().create();
        mLogInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    public static RetrofitManager getInstance(String baseUrl) {
        if (instance == null) {
            synchronized (RetrofitManager.class) {
                if (instance == null) {
                    instance = new RetrofitManager(baseUrl);
                }
            }
        }
        return instance;
    }


    public <T> T create(Class<T> service) {
        return retrofit.create(service);
    }


    private OkHttpClient httpClient() {
        File cacheFile = new File(FileUtil.getAppCacheDir(GlobalApplication.getInstance()),
                "/GlhAppCache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100);
        return new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .cache(cache)
                .addInterceptor(mLogInterceptor)
                .addInterceptor(new HeadInterceptor())
                .addNetworkInterceptor(new HttpCacheInterceptor())
                .cookieJar(new PersistentCookieJar(new SetCookieCache(),
                        new SharedPrefsCookiePersistor(GlobalApplication.getInstance())))
                .build();
    }


    //缓存拦截器
    private class HttpCacheInterceptor implements Interceptor {

        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetUtils.isNetworkConnected()) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            if (NetUtils.isNetworkConnected()) {
                int maxAge = 0;
                // 有网络时 设置缓存超时时间0个小时
                Log.i("glh-okhttp", "intercept: " + "有网络时候设置缓存");
                response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                // 无网络时，设置超时为1周
                int maxStale = 60 * 60 * 24 * 28;
                Log.i("glh-okhttp", "intercept: " + "无网络时候设置缓存");
                response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
            return response;
        }
    }


    private class HeadInterceptor implements Interceptor {

        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {

            Request originalRequest = chain.request();
            Request authRequest = originalRequest.newBuilder().build();
            return chain.proceed(authRequest);
        }
    }

    public void reset() {
        instance = null;
    }

}
