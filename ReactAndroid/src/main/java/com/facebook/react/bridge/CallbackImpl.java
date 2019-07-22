/**
 * Copyright (c) Facebook, Inc. and its affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.facebook.react.bridge;

import com.facebook.react.modules.core.DeviceEventManagerModule;

/**
 * Implementation of javascript callback function that use Bridge to schedule method execution
 */
public final class CallbackImpl implements Callback {

  private final JSInstance mJSInstance;
  private final int mCallbackId;
  private boolean mInvoked;

  public CallbackImpl(JSInstance jsInstance, int callbackId) {
    mJSInstance = jsInstance;
    mCallbackId = callbackId;
    mInvoked = false;
  }

  @Override
  public void invoke(Object... args) {
    if (mInvoked) {
      //CRN BEGIN
//      throw new RuntimeException("Illegal callback invocation from native "+
//        "module. This callback type only permits a single invocation from "+
//        "native code.");
      return;
      //CRN END
    }
    mJSInstance.invokeCallback(mCallbackId, Arguments.fromJavaArgs(args));
    mInvoked = true;
  }

  // CRN BEGIN
  @Override
  public void invokeEvent(String eventName, WritableNativeMap nativeMap) {
    if (mJSInstance != null && mJSInstance.getJSInstance() != null) {
      mJSInstance.getJSInstance()
              .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
              .emit(eventName, nativeMap);
    }
  }
  // CRN END
}
