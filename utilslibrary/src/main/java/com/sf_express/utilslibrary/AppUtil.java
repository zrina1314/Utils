package com.sf_express.utilslibrary;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * 作者：花心大萝卜
 * 创建时间：2015/9/16
 * 功能描述：App工具类
 */
public class AppUtil {
    /**
     * 获得App包名
     *
     * @param context 上下文
     * @return App包名
     */
    public String getAppPackageName(Context context) {
        try {
            String pkName = context.getPackageName();
            String packageName = context.getPackageManager().getPackageInfo(pkName, 0).packageName;
            return packageName;
        } catch (Exception e) {
            return Constants.DEFAULT_PACKAGE;
        }
    }

    private static PackageInfo getPackageInfo(Context context)
            throws PackageManager.NameNotFoundException {
        return context.getPackageManager().getPackageInfo(
                context.getPackageName(), 0);
    }

    /**
     * 获得App版本号
     *
     * @param context 上下文
     * @return App版本号
     */
    public static int getVersionCode(Context context) {
        int versionCode = 0;
        try {
            PackageInfo info = getPackageInfo(context);
            versionCode = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获得App版本名称
     *
     * @param context 上下文
     * @return App版本名称
     */
    public static String getVersionName(Context context) {
        String versionName = "";
        try {
            PackageInfo info = getPackageInfo(context);
            versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }
}
