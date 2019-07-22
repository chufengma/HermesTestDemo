/**
 * Copyright (c) Ctrip, Inc. and its affiliates.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package ctrip.wireless.android.crn.instance;

import com.alibaba.fastjson.JSON;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.NativeModuleCallExceptionHandler;
import com.facebook.react.bridge.ReadableArray;

import ctrip.crn.error.CRNErrorReportListener;
import ctrip.wireless.android.crn.dev.CRNDebugTool;
import ctrip.wireless.android.crn.utils.LogUtil;

/**
 * Created by jim on 16/8/8.
 */
public class CRNErrorHandler {

    private static CRNErrorReportListener errorReportListener = new CRNErrorReportListener() {
        @Override
        public void reportFatalException(ReactInstanceManager instanceManager, String title, ReadableArray details, int exceptionId) {
            LogUtil.e("Fatal Error:", title + " ----- " + JSON.toJSONString(details));
        }

        @Override
        public void reportSoftException(ReactInstanceManager instanceManager, String title, ReadableArray details, int exceptionId) {
            LogUtil.e("Soft Error:", title + " ----- " + JSON.toJSONString(details));
        }

        @Override
        public void updateExceptionMessage(ReactInstanceManager instanceManager, String title, ReadableArray details, int exceptionId) {
            LogUtil.e("update Error:", title + " ----- " + JSON.toJSONString(details));
        }
    };


    public static CRNErrorReportListener getErrorReportListener() {
        return  errorReportListener;
    }

    public static NativeModuleCallExceptionHandler getNativeExceptionHandler() {
        return mNativeExceptionHandler;
    }

    private static NativeModuleCallExceptionHandler mNativeExceptionHandler = new NativeModuleCallExceptionHandler() {
        @Override
        public void handleException(final Exception e) {
            if (e != null) {
                CRNDebugTool.showRedBoxDialog(e);
            }
            LogUtil.e("Native Error:", e);
        }
    };
}
