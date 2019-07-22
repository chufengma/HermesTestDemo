package ctrip.crn.utils;

import java.util.Map;

/**
 * Created by dev on 2018/9/11.
 */

public class CRNActionLogger {

    private static CRNActionLoggerImpl crnActionLoggerImpl;

    public static void setCrnActionLoggerImpl(CRNActionLoggerImpl crnActionLoggerImpl) {
        CRNActionLogger.crnActionLoggerImpl = crnActionLoggerImpl;
    }

    public interface CRNActionLoggerImpl {
        void logMetrics(String key, Map<String, Object> params);
    }

    public static void logMetrics(String key, Map<String, Object> params) {
        if (crnActionLoggerImpl != null) {
            crnActionLoggerImpl.logMetrics(key, params);
        }
    }
}
