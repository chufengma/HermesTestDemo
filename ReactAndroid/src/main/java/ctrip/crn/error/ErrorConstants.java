package ctrip.crn.error;

/**
 * Created by neo on 03/07/2017.
 */

public class ErrorConstants {

    public static final String TAG = "ReactNative";

    /**
     * fatal error prefix
     */
    public static final String CRN_FATAL_ERROR = "CRN_FATAL_ERROR";

    /**
     * exit error
     */
    public static final String CRN_EXIT_ERROR = "CRN_EXIST_ERROR";

    /**
     * normal error prefix
     */
    public static final String CRN_NORMAL_ERROR = "CRN_NORMAL_ERROR";

    /**
     * confused error prefix
     */
    public static final String CRN_UNKNOWN_ERROR = "CRN_UNKNOWN_ERROR";

    /**
     * do_not_delete
     */
    public static final String CRN_JS_ERROR_STACK = "CRN_JS_ERROR_STACK";

    /**
     * do_not_delete
     */
    public static final String CRN_JS_ERROR_DETAIL = "CRN_JS_ERROR_DETAIL";

    /**
     * instance 创建过程埋点tag
     */
    public static final String CRN_STATISTIC_INFO = "CRN_STATISTIC_INFO";

    /**
     * fatal error start from -1000
     */
    public static final int FATAL_CODE_EXCEPTION = -1001;

}
