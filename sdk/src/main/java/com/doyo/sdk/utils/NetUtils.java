package com.doyo.sdk.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import com.doyo.sdk.global.GlobalApplication;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     company: 永无bug集团
 *     time   : 2019/05/10
 *     desc   : 网络工具类
 *
 * </pre>
 */

public class NetUtils {

    /**
     * 检查是否有可用网络
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) GlobalApplication.getInstance().getApplicationContext()
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        return connectivityManager.getActiveNetworkInfo() != null;
    }
}
