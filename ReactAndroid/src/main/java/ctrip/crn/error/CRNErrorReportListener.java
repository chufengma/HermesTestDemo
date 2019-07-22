package ctrip.crn.error;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.ReadableArray;

/**
 * Created by jim on 16/8/5.
 */
public interface CRNErrorReportListener {

    /**
     * throw js-fatal exception
     * @param title title
     * @param details details
     * @param exceptionId exceptionId
     */
    public void reportFatalException(ReactInstanceManager instanceManager, String title, ReadableArray details, int exceptionId);

    /**
     * throw js-soft exception
     * @param title title
     * @param details details
     * @param exceptionId exceptionId
     */
    public void reportSoftException(ReactInstanceManager instanceManager, String title, ReadableArray details, int exceptionId);

    /**
     * throw js-update exception
     * @param title title
     * @param details details
     * @param exceptionId exceptionId
     */
    public void updateExceptionMessage(ReactInstanceManager instanceManager, String title, ReadableArray details, int exceptionId);

}
