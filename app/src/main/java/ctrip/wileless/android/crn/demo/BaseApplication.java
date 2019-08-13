/**
 * Copyright (c) Ctrip, Inc. and its affiliates.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package ctrip.wileless.android.crn.demo;

import android.app.Application;
import android.util.Log;


import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;

import javax.annotation.Nullable;

import ctrip.wireless.android.crn.ContextHolder;

public class BaseApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    ContextHolder.context = this;
    ContextHolder.version = "1.0.0";
    ContextHolder.debug = true;
    ContextHolder.application = this;

    final long startTime = System.currentTimeMillis();

    ReactMarker.addListener(new ReactMarker.MarkerListener() {
      @Override
      public void logMarker(ReactMarkerConstants name, @Nullable String tag, int instanceKey) {
        Log.i("AXE", name.toString() + " " + tag + " " + instanceKey + ": " + (System.currentTimeMillis() - startTime));
      }
    });
  }

}
