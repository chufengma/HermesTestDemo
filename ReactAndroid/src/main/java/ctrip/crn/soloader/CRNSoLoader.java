package ctrip.crn.soloader;

import android.content.Context;
import android.text.TextUtils;

import com.facebook.common.logging.FLog;
import com.facebook.react.common.ReactConstants;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import ctrip.crn.utils.CRNActionLogger;

/**
 * Created by lqliao on 2017/5/12.
 */

public class CRNSoLoader {

    /**
     * so lib，包含加载顺序
     */
    private final static String[] SO_LIB_ARR = new String[] {
            "libc++_shared.so", "libfb.so", "libfolly_json.so", "libglog.so", "libglog_init.so", "libjsc.so", "libjscexecutor.so", "libjsinspector.so", "libreactnativejni.so", "libyoga.so"
    };

    private static boolean mX86Cpu = false;
    private static String mX86SoLibPath = null;
    private static String mArmSolibPath = null;

    /**
     * 是否x86机型
     */
    public static boolean isX86Cpu() {
        return mX86Cpu;
    }

    /**
     * 设置是否x86机型
     * @param isX86Cpu isX86Cpu
     */
    public static void setX86Cpu(boolean isX86Cpu) {
        CRNSoLoader.mX86Cpu = isX86Cpu;
    }

    /**
     * 设置x86 so lib path
     * @param x86SoLibPath x86SoLibPath
     */
    public static void setX86SoLibPath(String x86SoLibPath) {
        CRNSoLoader.mX86SoLibPath = x86SoLibPath;
    }

    /**
     * 设置arm so lib path
     * @param armSolibPath armSolibPath
     */
    public static void setArmSolibPath(String armSolibPath) {
        CRNSoLoader.mArmSolibPath = armSolibPath;
    }

    /**
     * 获取so lib path
     */
    private static String getSoLibPath() {
        return mX86Cpu && !TextUtils.isEmpty(mX86SoLibPath)
                && new File(mX86SoLibPath).exists() ? mX86SoLibPath : mArmSolibPath;
    }

    /**
     * 加载所有的so lib
     */
    public static boolean loadAllSoLib() {
        String soPath = getSoLibPath();
        if (TextUtils.isEmpty(soPath)) {
            return false;
        }
        for (String soLib: SO_LIB_ARR) {
            if (!new File(soPath + soLib).exists()) {
                return false;
            }
        }
        try {
            for (String soLib: SO_LIB_ARR) {
                if (!TextUtils.isEmpty(mX86SoLibPath)
                        && new File(mX86SoLibPath).exists()) {
                    try {

                        System.load(soPath + soLib);
                    } catch (Throwable e) {
                        // x86 补偿
                        logSOLoader("o_crn_x86_try", e.getMessage());
                        System.load(mX86SoLibPath + soLib);
                    }
                } else {
                    System.load(soPath + soLib);
                }
            }

            logSOLoader("o_crn_autoload_so_success", "");
        } catch (Throwable ex) {
            logSOLoader("o_crn_autoload_so_failed", ex.getMessage());
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    private static void logSOLoader(String key, String message) {
        Map<String, Object> params = new HashMap<>();
        params.put("isX86Cpu", isX86Cpu());
        params.put("x86Path", mX86SoLibPath);
        params.put("soPath", getSoLibPath());
        params.put("x86PathExist", new File(mX86SoLibPath).exists());
        params.put("errorMsg", message);
        CRNActionLogger.logMetrics(key, params);
    }

    /**
     * 根据给定Key获取值.
     * @return 如果不存在该key则返回空字符串
     */
    public static String get(Context context, String key) {
        String ret= "";
        try {
            ClassLoader cl = context.getClassLoader();
            @SuppressWarnings("rawtypes")
            Class SystemProperties = cl.loadClass("android.os.SystemProperties");

            @SuppressWarnings("rawtypes")
            Class[] paramTypes= new Class[1];
            paramTypes[0]= String.class;
            Method get = SystemProperties.getMethod("get", paramTypes);

            Object[] params= new Object[1];
            params[0]= new String(key);
            ret= (String) get.invoke(SystemProperties, params);
        } catch(Throwable ex) {
            FLog.d(ReactConstants.TAG, "CRNSoLoader get SystemProperties error: " + ex.getMessage());
        }
        return ret;
    }

}
