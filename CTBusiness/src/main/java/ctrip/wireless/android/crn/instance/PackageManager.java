/**
 * Copyright (c) Ctrip, Inc. and its affiliates.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package ctrip.wireless.android.crn.instance;

import java.io.File;

import ctrip.wireless.android.crn.ContextHolder;
import ctrip.wireless.android.crn.utils.FileUtil;
import ctrip.wireless.android.crn.utils.LogUtil;
import ctrip.wireless.android.crn.utils.StringUtil;

public class PackageManager {

    public static final String RN_PACKAGE_DIR = "webapp";

    public static boolean installPackageForProduct(String productName) {
        if (StringUtil.isEmpty(productName)) {
            return false;
        }

        //检查是否安装成功
        boolean isWorkDirExist = isExistWorkDirForProduct(productName);

        //从IPA目录安装
        if (!isWorkDirExist) {
            return installFromAssets(productName);
        }

        return isWorkDirExist;
    }


    private static boolean installFromAssets(String productName) {
        if (productName == null) {
            return false;
        }
        boolean unzipSuccess;
        String pkgAssetsPath = packagePathInApkAssetsDir(productName);
        String webappDir = getFileWebappPath() + "/" + productName;
        unzipSuccess = FileUtil.copyDirFromAsset(ContextHolder.context, pkgAssetsPath, webappDir);
        LogUtil.e("Package： install from APK =" + pkgAssetsPath + ", to:" + productName + ", un-7z ret=" + unzipSuccess);
        return unzipSuccess;
    }


    public static boolean isExistWorkDirForProduct(String productName) {
        String productPath = getProductDirPath(productName);
        // TODO
        if (new File(productPath).exists()) {
            return true;
        }
        return false;
    }

    public static String getProductDirPath(String productName) {
        return getFileWebappPath() + "/" + productName;
    }

    public static String getFileWebappPath() {
        return ContextHolder.context.getFilesDir().getAbsolutePath() + "/" + RN_PACKAGE_DIR + "_" + ContextHolder.version;
    }

    public static String packagePathInApkAssetsDir(String productName) {
        if (productName == null) {
            return "";
        }
        String pkgAssetsPath = RN_PACKAGE_DIR + "/" + productName + "";
        return pkgAssetsPath;
    }

    public static void deleteWebapp() {
        deleteDir(new File(getFileWebappPath()));
    }

    public static void deleteDir(File file) {
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                deleteDir(f);
            }
        }
        file.delete();
    }
}
