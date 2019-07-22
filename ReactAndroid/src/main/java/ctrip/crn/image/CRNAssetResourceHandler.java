package ctrip.crn.image;

import android.content.Context;
import android.net.Uri;

/**
 * @author yfchu
 * @date 2016/8/1
 */
public interface CRNAssetResourceHandler {

    /**
     * 解决APP内部Asset图片路径
     * @param context context
     * @param uri uri
     */
    Uri resolveResource(Context context, Uri uri);

}
