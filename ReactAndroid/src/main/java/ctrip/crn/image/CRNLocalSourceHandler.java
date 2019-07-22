package ctrip.crn.image;

import android.content.Context;

/**
 * @author yfchu
 * @date 2016/7/29
 */
public interface CRNLocalSourceHandler {

    /**
     * 解决APP内部drawable图片路径
     * @param context context
     * @param name name
     */
    public int resolveResourceId(Context context, String name);

}