package com.sf_express.utilslibrary;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：花心大萝卜
 * 创建时间：2015/9/16
 * 功能描述：文件工具类
 * 使用说明：在使用前请先初始化是否打开日志,默认为打开日志
 */
public class FileUtil {
    /**
     * 获取SD卡位置
     *
     * @return
     */
    private static String getSDPath() {
        List list = getExtSDCardPath();
        if (list == null || list.size() == 0) {
            return getInnerSDCardPath();
        } else {
            return list.get(0).toString();
        }
    }

    /**
     * 获取内置SD卡路径
     *
     * @return
     */
    public static String getInnerSDCardPath() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    /**
     * 获取外置SD卡路径
     *
     * @return 应该就一条记录或空
     */
    public static List getExtSDCardPath() {
        List lResult = new ArrayList();
        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec("mount");
            InputStream is = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("extSdCard")) {
                    String[] arr = line.split(" ");
                    String path = arr[1];
                    File file = new File(path);
                    if (file.isDirectory()) {
                        lResult.add(path);
                    }
                }
            }
            isr.close();
        } catch (Exception e) {
        }
        return lResult;
    }


    private static void createDirectory(String url) {
        try {
            File file = new File(url);
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 本地文件存放位置
     */
    public static String getFile_PATH() {
        return getSDPath() +  "/sf/files/";
    }

    /**
     * 本地图片文件存放位置
     */
    public static String getImageFile_PATH() {
        return getSDPath() + "/sf/images/";
    }
}
