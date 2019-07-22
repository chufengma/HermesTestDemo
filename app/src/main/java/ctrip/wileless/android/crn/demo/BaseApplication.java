/**
 * Copyright (c) Ctrip, Inc. and its affiliates.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package ctrip.wileless.android.crn.demo;

import android.app.Application;


import ctrip.wireless.android.crn.ContextHolder;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ContextHolder.context = this;
        ContextHolder.version = "1.0.0";
        ContextHolder.debug = true;
        ContextHolder.application = this;
    }

}
