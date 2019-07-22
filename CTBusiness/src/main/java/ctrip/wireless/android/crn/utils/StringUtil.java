/**
 * Copyright (c) Ctrip, Inc. and its affiliates.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package ctrip.wireless.android.crn.utils;

public class StringUtil {

    public static boolean isEmpty(String source) {
        return source == null || source.length() == 0;
    }

    public static boolean equalsIgnoreCase(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
    }

    public static boolean isOnlineHTTPURL(String inUrl) {
        if (StringUtil.isEmpty(inUrl)) {
            return false;
        }
        return inUrl.toLowerCase().startsWith("http");
    }
}
