package ctrip.wireless.android.crn.utils;

import android.content.Context;
import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import ctrip.wireless.android.crn.ContextHolder;


/**
 * @author yfchu
 * @date 2016/7/13
 */
public class RNUtils {

    public static long covertJsDateToLong(String dateStr) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(dateStr).getTime();
        } catch (ParseException e) {
            return 0;
        }
    }

    public static Calendar covertJsDateToCalendar(String dateStr) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(covertJsDateToLong(dateStr));
        return calendar;
    }

    public static Calendar getCalendar(long timestamp, boolean isGMT08) {
        Calendar calendar = Calendar.getInstance();
        if (isGMT08) {
            calendar.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
        }
        calendar.setTimeInMillis(timestamp);
        return calendar;
    }

    public static long parseLong(String num) {
        try {
            return Long.parseLong(num);
        } catch (Exception e) {
            return 0;
        }
    }

    public static String toLowerCase(String originStr) {
        if (TextUtils.isEmpty(originStr)) {
            return "";
        }
        return originStr.toLowerCase(Locale.US);
    }


    /**
     * 获取Font下载文件目录
     */
    public static String getFontDownloadPath() {
        return ContextHolder.context.getDir("fontDownload", Context.MODE_PRIVATE).getPath() + '/';
    }

    public static boolean isFileExist(String path) {
        File file = new File(path);
        return file.exists();
    }


    public static String file2String(InputStream inputStream) {
        InputStreamReader reader = null;
        StringWriter writer = new StringWriter();
        try {
            reader = new InputStreamReader(inputStream);
            char[] buffer = new char[1024];
            int n = 0;
            while (-1 != (n = reader.read(buffer))) {
                writer.write(buffer, 0, n);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (reader != null)
                    reader.close();
                if (inputStream != null){
                    inputStream.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return writer.toString();
    }

    public static String file2String(File file) {
        try {
            return file2String(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     *
     * @param path
     *            删除目录,包括自己
     */
    public static void delDir(String path) {
        delFile(path);
        new File(path).delete();
    }

    /**
     * 功能描述:删除文件夹下所有文件和文件夹
     * <p/>
     * <pre>
     *     苟俊:   2013-1-16      新建
     * </pre>
     *
     * @param path
     */
    public static void delFile(String path) {
        File cacheFile = new File(path);
        if (!cacheFile.exists()) {
            return;
        }
        File[] files = cacheFile.listFiles();
        if (files == null) {
            return;
        }
        for (int i = 0; i < files.length; i++) {
            // 是文件则直接删除
            if (files[i].exists() && files[i].isFile()) {
                files[i].delete();
            } else if (files[i].exists() && files[i].isDirectory()) {
                // 递归删除文件
                delFile(files[i].getAbsolutePath());
                // 删除完目录下面的所有文件后再删除该文件夹
                files[i].delete();
            }
        }
    }

}
