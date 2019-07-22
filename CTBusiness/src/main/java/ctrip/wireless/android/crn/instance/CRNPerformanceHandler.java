/**
 * Copyright (c) Ctrip, Inc. and its affiliates.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package ctrip.wireless.android.crn.instance;


import com.facebook.react.ReactInstanceManager;

import java.util.HashMap;

import ctrip.crn.instance.CRNLoadReportListener;
import ctrip.wireless.android.crn.CRNURL;
import ctrip.wireless.android.crn.utils.LogUtil;

public class CRNPerformanceHandler {

    private static long mStartLoadJSTime = 0L;
    private static long mEndLoadJSTime = 0L;

    private static CRNLoadReportListener loadReportListener = new CRNLoadReportListener() {
        @Override
        public void onLoadComponentTime(ReactInstanceManager mng, long l) {
            String productName = CRNURL.getProductName(mng.getCRNInstanceInfo().businessURL);
            HashMap<String, String> userInfo = new HashMap<>();
            userInfo.put("bundle",productName);
            userInfo.put("isUnbundle", "" + mng.getCRNInstanceInfo().isUnbundle);
            long loadJSTime = l;
            if (mStartLoadJSTime > 0 && mEndLoadJSTime > mStartLoadJSTime) {
                loadJSTime += mEndLoadJSTime - mStartLoadJSTime;
            }
            float time = loadJSTime / 1000.0f;
            time = Math.round(time * 100) / 100.0f;
            LogUtil.e("RNSHow", "show time:" + time);
        }
    };

    public static CRNLoadReportListener getLoadReportListener() {
        return  loadReportListener;
    }

    public static void setStartLoadJSTime(long startLoadJSTime) {
        mStartLoadJSTime = startLoadJSTime;
    }

    public static void setEndLoadJSTime(long endLoadJSTime) {
        mEndLoadJSTime = endLoadJSTime;
    }
}
