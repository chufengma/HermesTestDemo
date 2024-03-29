/**
 * Copyright (c) Facebook, Inc. and its affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.facebook.react.views.imagehelper;

import javax.annotation.Nullable;

import android.content.Context;
import android.net.Uri;

import com.facebook.infer.annotation.Assertions;

import ctrip.crn.image.CRNResourceUriHelper;

/**
 * Class describing an image source (network URI or resource) and size.
 */
public class ImageSource {

  private @Nullable Uri mUri;
  private String mSource;
  private double mSize;
  private boolean isResource;

  public ImageSource(Context context, String source, double width, double height) {
    mSource = source;
    mSize = width * height;

    // Important: we compute the URI here so that we don't need to hold a reference to the context,
    // potentially causing leaks.
    mUri = computeUri(context);
  }

  public ImageSource(Context context, String source) {
    this(context, source, 0.0d, 0.0d);
  }

  /**
   * Get the source of this image, as it was passed to the constructor.
   */
  public String getSource() {
    return mSource;
  }

  /**
   * Get the URI for this image - can be either a parsed network URI or a resource URI.
   */
  public Uri getUri() {
    return Assertions.assertNotNull(mUri);
  }

  /**
   * Get the area of this image.
   */
  public double getSize() {
    return mSize;
  }

  /**
   * Get whether this image source represents an Android resource or a network URI.
   */
  public boolean isResource() {
    return isResource;
  }

  private Uri computeUri(Context context) {
    try {
      Uri uri = Uri.parse(mSource);
      // CRN BEGIN
      // schemeUri is null, <ADD>

      // Verify scheme is set, so that relative uri (used by static resources) are not handled.
      Uri schemeUri = computeSchemeUri(context, uri);
      if (schemeUri != null) {
        return schemeUri;
      }

      Uri imageUri = computeImageUri(uri);
      if (imageUri != null) {
        return imageUri;
      }

      // CRN END
      // Verify scheme is set, so that relative uri (used by static resources) are not handled.
      return uri.getScheme() == null ? computeLocalUri(context) : uri;
    } catch (Exception e) {
      return computeLocalUri(context);
    }
  }

  // CRN BEGIN
// deal with local image resource uri, CRNResourceUriHelper, <ADD>

  private Uri computeSchemeUri(Context context, Uri uri) {
    return CRNResourceUriHelper.getResourceUri(context, uri);
  }

  private Uri computeImageUri(Uri uri) {
    return CRNResourceUriHelper.resolveImageUri( uri);
  }
  // CRN END

  private Uri computeLocalUri(Context context) {
    isResource = true;
    return ResourceDrawableIdHelper.getInstance().getResourceDrawableUri(context, mSource);
  }
}
