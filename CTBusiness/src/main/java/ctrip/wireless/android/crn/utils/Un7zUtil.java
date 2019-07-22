package ctrip.wireless.android.crn.utils;

import android.content.Context;
import android.content.res.AssetManager;

import com.hzy.lib7z.Un7Zip;

import java.io.File;
import java.io.InputStream;

import ctrip.wireless.android.crn.ContextHolder;


/**
 * Created by dev on 2017/10/9.
 * @see https://github.com/hzy3774/AndroidUn7zip
 */
public class Un7zUtil {

    public static boolean extract7z(String filePath, String outPath) {
        if (filePath == null || outPath == null || !new File(filePath).exists()) {
            return  false;
        }
        return Un7Zip.extract7z(filePath, outPath);
    }

    public static boolean extractAssets(Context context, String assetPath, String outPath) {
        if (context == null || outPath == null) {
            return  false;
        }

        boolean isAssetPathExist = false;
        try {
            AssetManager assetManager = ContextHolder.context.getAssets();
            InputStream inputStream = assetManager.open(assetPath);
            if (inputStream != null) {
                inputStream.close();
            }
            isAssetPathExist = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        //从asset拷贝module.7z
        if (isAssetPathExist) {
            return Un7Zip.extract7zFromAssets(context, assetPath, outPath);
        }

        return  false;
    }



}
