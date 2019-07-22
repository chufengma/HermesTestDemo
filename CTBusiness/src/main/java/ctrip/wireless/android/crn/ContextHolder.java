/**
 * Copyright (c) Ctrip, Inc. and its affiliates.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package ctrip.wireless.android.crn;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

public class ContextHolder {

    public static Context context;

    public static String version = "1.0.0";

    public static Application application;

    public static boolean debug;

    public Activity getCurrentActivity() {
        return null;
    }

}
