/**
 * Copyright (c) Ctrip, Inc. and its affiliates.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package ctrip.wireless.android.crn;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import java.io.File;
import java.io.Serializable;
import java.net.URI;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import ctrip.wireless.android.crn.instance.PackageManager;
import ctrip.wireless.android.crn.utils.LogUtil;
import ctrip.wireless.android.crn.utils.RNUtils;
import ctrip.wireless.android.crn.utils.StringUtil;

public class CRNURL implements Serializable {

    public static final String DEFAULT_MODULE_NAME = "CtripApp";
    public static final String MAIN_MODULE_NAME_FOR_DEV = "index.android";
    public static final String CRN_MODULE_NAME_KEY = "crnmodulename";
    public static final String CRN_TYPE_URL_KEY = "crntype=1";
    public static final String RN_BUNDLE_WORK_DIR = "webapp";

    public final static String RN_COMMON_PACKAGE_NAME = "rn_common";
    public final static String COMMON_BUNDLE_PATH = getRNBundleWorkPath() + "/" + RN_COMMON_PACKAGE_NAME + "/common_android.js";

    private final static String UNBUNDLE_FILE = "_crn_unbundle";

    public String urlStr = "";
    private String moduleName = "";
    private String productName = "";
    private String absoluteFilePath = "";
    private String requestFileName;
    private String hostInfo;
    private Map<String, String> queryParams;
    private boolean canDebug;
    private boolean isPreload;

    private String mUnbundleWorkPath;

    public String initParams;

    public enum SourceType {
        Unknown,
        Sdcard,
        Assets,
        File,
        Online,
    }

    private SourceType rnSourceType;

    public CRNURL(String urlStr_) {
        this.urlStr = urlStr_;
        this.rnSourceType = getRNSourceTypeFromUrl(urlStr_);
        this.absoluteFilePath = getRNFileAbsolutePath(urlStr_, this.rnSourceType);

        if (this.rnSourceType == SourceType.File) {
            if (!this.urlStr.contains(getRNBundleWorkPath())) {
                this.urlStr = getRNBundleWorkPath() + urlStr_;
            }
            this.mUnbundleWorkPath = getUnbundleWorkPathFromURL(this.absoluteFilePath, this.rnSourceType);
        }

        this.moduleName = getModuleNameFromUrl(this.urlStr);
        this.productName = getProductName(this.absoluteFilePath);
        this.requestFileName = getLastPath();

        if (this.rnSourceType == SourceType.Online) {
            this.hostInfo = fetchHostInfo();
        }

        canDebug = (this.rnSourceType == SourceType.Online) && RNUtils.toLowerCase(urlStr_).contains("index.android.bundle");
    }

    public boolean isUnbundleFileExist() {
        return rnSourceType != SourceType.Online
            && !TextUtils.isEmpty(this.mUnbundleWorkPath)
            && new File(this.mUnbundleWorkPath + "/" + UNBUNDLE_FILE).exists();
    }

    public static void setCRNDevHost(String devHost) {
        if (devHost == null) {
            return;
        }
        // 设置rn默认的host
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(ContextHolder.context);
        mPreferences.edit().putString("debug_http_host", devHost).apply();
    }

    public boolean isCanDebug() {
        if (StringUtil.isOnlineHTTPURL(this.getUrl())) {
            return true;
        }
        if (getRnSourceType() == SourceType.Online) {
            return true;
        }
        return false;
    }

    private static SourceType getRNSourceTypeFromUrl(String urlStr) {
        SourceType sourceType = SourceType.Unknown;
        boolean isOnlineURL = StringUtil.isOnlineHTTPURL(urlStr);
        if (isOnlineURL) {
            sourceType = SourceType.Online;
        } else if (RNUtils.toLowerCase(urlStr).contains("sdcard")) {
            sourceType = SourceType.Sdcard;
        } else if (urlStr.startsWith("/")) {
            sourceType = SourceType.File;
        } else {
            sourceType = SourceType.Assets;
        }

        return sourceType;
    }

    public static String getRNBundleWorkPath() {
        return PackageManager.getFileWebappPath();
    }

    private static String getRNFileAbsolutePath(String urlStr_, SourceType sourceType_) {
        if (StringUtil.isEmpty(urlStr_)) {
            return null;
        }

        String absPath = "";
        int pathEndIndex = urlStr_.indexOf("?");
        if (pathEndIndex > 0) {
            absPath = urlStr_.substring(0, pathEndIndex);
        } else {
            return null;
        }

        switch (sourceType_) {
            case File:
                if (!absPath.contains(getRNBundleWorkPath())) {
                    absPath = getRNBundleWorkPath() + absPath;
                }
                break;
            default:
                break;
        }

        return absPath;
    }

    private static String getModuleNameFromUrl(String urlStr) {
        if (TextUtils.isEmpty(urlStr)) {
            return null;
        }
        String moduleName = null;
        Map<String, String> params = getQueryMap(urlStr);
        if (params != null) {
            Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
            Map.Entry<String, String> entry;

            while (it.hasNext()) {
                entry = it.next();
                String key = entry.getKey();
                if (CRN_MODULE_NAME_KEY.equalsIgnoreCase(key)) {
                    moduleName = entry.getValue();
                    break;
                }
            }
        }

        return moduleName;
    }

    private String getUnbundleWorkPathFromURL(String absPath, SourceType type) {
        if ((type == SourceType.File || type == SourceType.Sdcard) && !TextUtils.isEmpty(absPath)) {
            int index = absPath.lastIndexOf('/');
            if (index > 0) {
                return absPath.substring(0, index);
            }
        }
        return null;
    }

    /**
     * url是否是crn url
     *
     * @param url url
     * @return boolean
     */
    public static boolean isCRNURL(String url) {
        return !TextUtils.isEmpty(url)
                && url.indexOf('?') > -1
                && RNUtils.toLowerCase(url).contains(RNUtils.toLowerCase(CRN_MODULE_NAME_KEY))
                && RNUtils.toLowerCase(url).contains(RNUtils.toLowerCase(CRN_TYPE_URL_KEY));
    }

    /**
     * 获取unbundle的bu目录
     *
     * @return String
     */
    public String getUnbundleWorkPath() {
        return mUnbundleWorkPath;
    }

    /**
     * 获取针对于unbundle的common bundle
     *
     * @return String
     */
    public String getCommonBundlePath() {
        return COMMON_BUNDLE_PATH;
    }

    public String getUrl() {
        return this.urlStr;
    }

    public Map<String, String> getUrlQuery() {
        if (queryParams == null) {
            queryParams = getQueryMap(this.urlStr);
        }
        return queryParams;
    }

    public String getModuleName() {
        if (isUnbundleURL()) {
            return DEFAULT_MODULE_NAME;
        }
        else {
            return TextUtils.isEmpty(this.moduleName) ? DEFAULT_MODULE_NAME : this.moduleName;
        }
    }

    public String getAbsoluteFilePath() {
        return absoluteFilePath;
    }

    public SourceType getRnSourceType() {
        return rnSourceType;
    }

    public static Map<String, String> getQueryMap(String url) {
        Map<String, String> queries = new HashMap<>();
        try {
            if (url != null && url.contains("?") && url.length() > 1) {
                url = url.substring(url.indexOf("?") + 1, url.length());
                for (String str : url.split("&")) {
                    if (TextUtils.isEmpty(str)) {
                        continue;
                    }
                    int eIndex = str.indexOf("=");
                    String query[] = new String[2];
                    if (eIndex > 0 && eIndex < str.length()) {
                        query[0] = str.substring(0, eIndex);
                    }
                    if (eIndex + 1 > 0 && eIndex + 1 < str.length()) {
                        query[1] = str.substring(eIndex + 1, str.length());
                    }
                    queries.put(query[0], (query[1] != null ? URLDecoder.decode(query[1]) : null));
                }
            }
        } catch (Exception e) {
            LogUtil.e("error when parse querymap", e);
        }
        return queries;
    }

    private String getLastPath() {
        if (StringUtil.isEmpty(absoluteFilePath)) {
            return "";
        }
        if (absoluteFilePath.contains("/")) {
            String[] paths = absoluteFilePath.split("/");
            return paths[paths.length - 1];
        } else {
            return absoluteFilePath;
        }
    }

    public String getProductName() {
        return productName != null ? productName : "unkonwn_product";
    }

    public static String getProductName(String absFilePath) {
        String productName = null;
        if (absFilePath == null) {
            return productName;
        }

        String flag = getRNBundleWorkPath();
        int findIndex = absFilePath.indexOf(flag);
        if (findIndex >= 0 && flag != null) {
            String st = absFilePath.substring(findIndex + flag.length());
            if (st.startsWith("/")) {
                st = st.substring(1);
            }
            int endIndex = st.indexOf("/");
            if (endIndex >= 0) {
                productName = st.substring(0, endIndex);
            }
        }

        return productName;
    }

    private String fetchHostInfo() {
        try {
            URI uri = URI.create(getUrl());
            return uri.getHost() + ":" + uri.getPort();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isUnbundleURL() {
        return new File(this.mUnbundleWorkPath + "/" + UNBUNDLE_FILE).exists();
    }

    public boolean ignoreCache() {
        return (urlStr != null) && RNUtils.toLowerCase(urlStr).contains("ignorecached=1");
    }
}
