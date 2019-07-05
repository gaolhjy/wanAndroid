package com.doyo.sdk.utils;

import android.content.Context;
import android.content.Intent;

import java.io.Serializable;

/**
 * <pre>
 *     author   : 高磊华
 *     time     : 2019/04/19
 *     company  : 磊华集团
 *     desc     : 界面跳转的工具类
 * </pre>
 */

public class JumpUtils {


    /**
     * @param cls 跳转的目标界面
     * @Title JumpToActivity
     * @Description 不带参数的Activity界面跳转
     */
    public static void JumpToActivity(Context mContext, Class<?> cls) {
        JumpToActivity(mContext, cls, null);
    }

    /**
     * @param cls 跳转的目标界面
     * @param obj 带过去的界面参数
     * @Title JumpToActivity
     * @Description 带参数的界面跳转
     */
    public static void JumpToActivity(Context mContext, Class<?> cls, Object obj) {
        Intent intent = new Intent(mContext, cls);
        if (obj != null) {
            intent.putExtra("data", (Serializable) obj);
        }
        mContext.startActivity(intent);
    }

    /**
     * @param cls  跳转的目标界面
     * @param data 带过去的界面参数
     * @Title JumpToActivity
     * @Description 带参数的界面跳转
     */
    public static void JumpToActivity(Context mContext, Class<?> cls, Intent data) {
        Intent intent = new Intent(mContext, cls);
        if (data != null) {
            intent.putExtras(data);
        }
        mContext.startActivity(intent);
    }
}
