package ctrip.crn.image;

import android.content.Context;
import android.net.Uri;

import ctrip.crn.image.CRNAssetResourceHandler;

/**
 * @author yfchu
 * @date 2016/8/1
 */
public class CRNResourceUriHelper {

    private static CRNResourceUriHelper mCRNResourceUriHelper;
    private static CRNAssetResourceHandler mCRNAssetResourceHandler;
    private static CRNImageResourceHandler mCRNImageResourceHandler;

    private CRNResourceUriHelper() {
    }

    public static CRNResourceUriHelper getInstance() {
        if (mCRNResourceUriHelper == null) {
            mCRNResourceUriHelper = new CRNResourceUriHelper();
        }
        return mCRNResourceUriHelper;
    }

    public static void setCRNAssetResourceHandler(CRNAssetResourceHandler CRNAssetResourceHandler) {
        CRNResourceUriHelper.mCRNAssetResourceHandler = CRNAssetResourceHandler;
    }

    public static void seCRNImageResourceHandler(CRNImageResourceHandler mCRNImageResourceHandler) {
        CRNResourceUriHelper.mCRNImageResourceHandler = mCRNImageResourceHandler;
    }

    public static Uri getResourceUri(Context context, Uri uri) {
        if (mCRNAssetResourceHandler != null) {
            return mCRNAssetResourceHandler.resolveResource(context, uri);
        }
        return null;
    }

    public static Uri resolveImageUri(Uri originUri) {
        if (mCRNImageResourceHandler != null) {
            return mCRNImageResourceHandler.resolveUri(originUri);
        }
        return originUri;
    }

}
