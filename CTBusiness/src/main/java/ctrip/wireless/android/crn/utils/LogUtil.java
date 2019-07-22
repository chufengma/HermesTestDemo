/**
 * Copyright (c) Ctrip, Inc. and its affiliates.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package ctrip.wireless.android.crn.utils;

public class LogUtil {

    public static void e(String msg) {
        android.util.Log.e("EXCEPTION", "##异常信息##:[" + msg + "]");
    }

    public static void e(String msg, Throwable thr) {
        android.util.Log.e("EXCEPTION", "##异常信息##:[" + msg + "]");
        if (thr != null) {
            thr.printStackTrace();
        }
    }

    public static void e(String tag, String msg) {
        android.util.Log.e(tag, msg);
    }


}
