/**
 * Copyright (c) Facebook, Inc. and its affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.facebook.react.bridge;

import android.content.Context;

import com.facebook.react.NativeModuleRegistryBuilder;

/**
 * A context wrapper that always wraps Android Application {@link Context} and
 * {@link CatalystInstance} by extending {@link ReactContext}
 */
public class ReactApplicationContext extends ReactContext {
  // We want to wrap ApplicationContext, since there is no easy way to verify that application
  // context is passed as a param, we use {@link Context#getApplicationContext} to ensure that
  // the context we're wrapping is in fact an application context.
  public ReactApplicationContext(Context context) {
    super(context.getApplicationContext());
  }
  // CRN BEGIN
  private boolean clearViewsWhenDestroy = false;

  public boolean clearViewsWhenDestroy() {
    return clearViewsWhenDestroy;
  }

  public void setClearViewsWhenDestroy(boolean clearViewsWhenDestroy) {
    this.clearViewsWhenDestroy = clearViewsWhenDestroy;
  }

  NativeModuleRegistryBuilder.ProcessPackageStep processPackageStep;
  public void setProcessPackageStep(NativeModuleRegistryBuilder.ProcessPackageStep step) {
    this.processPackageStep = step;
  }

  public void step(Object step) {
    if (processPackageStep != null) {
      processPackageStep.onStep(step);
    }
  }

  // CRN END
}
