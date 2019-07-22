/**
 * Copyright (c) Facebook, Inc. and its affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.facebook.react.views.text;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextUtils;
import android.util.SparseArray;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;

/**
 * Class responsible to load and cache Typeface objects. It will first try to load typefaces inside
 * the assets/fonts folder and if it doesn't find the right Typeface in that folder will fall back
 * on the best matching system Typeface The supported custom fonts extensions are .ttf and .otf. For
 * each font family the bold, italic and bold_italic variants are supported. Given a "family" font
 * family the files in the assets/fonts folder need to be family.ttf(.otf) family_bold.ttf(.otf)
 * family_italic.ttf(.otf) and family_bold_italic.ttf(.otf)
 */
public class ReactFontManager {

  private static final String[] EXTENSIONS = {
      "",
      "_bold",
      "_italic",
      "_bold_italic"};
  private static final String[] FILE_EXTENSIONS = {".ttf", ".otf"};
  private static final String FONTS_ASSET_PATH = "fonts/";

  private static ReactFontManager sReactFontManagerInstance;

  final private Map<String, FontFamily> mFontCache;
  final private Map<String, Typeface> mCustomTypefaceCache;

  public static boolean needCacheFont = true;

  private ReactFontManager() {
    mFontCache = new HashMap<>();
    mCustomTypefaceCache = new HashMap<>();
  }

  public static ReactFontManager getInstance() {
    if (sReactFontManagerInstance == null) {
      sReactFontManagerInstance = new ReactFontManager();
    }
    return sReactFontManagerInstance;
  }

  public void removeCache(String fontFamilyName) {
    if (mFontCache != null) {
      mFontCache.remove(fontFamilyName);
    }
  }

  public @Nullable CustomTypeface getTypeface(
    String fontFamilyName,
    int style,
    //CRN BEGIN
    String fontPath,
    //CRN END
    AssetManager assetManager) {
    return getTypeface(fontFamilyName, style, 0, fontPath, assetManager);
  }

  public @Nullable CustomTypeface getTypeface(
      String fontFamilyName,
      int style,
      int weight,
      //CRN BEGIN
      String fontPath,
      //CRN END
      AssetManager assetManager) {
    if(mCustomTypefaceCache.containsKey(fontFamilyName)) {
      Typeface typeface = mCustomTypefaceCache.get(fontFamilyName);
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P && weight >= 100 && weight <= 1000) {
        return new CustomTypeface(Typeface.create(typeface, weight, (style & Typeface.ITALIC) != 0), false);
      }
      return new CustomTypeface(Typeface.create(typeface, style), false);
    }

    FontFamily fontFamily = mFontCache.get(fontFamilyName);
    if (fontFamily == null) {
      fontFamily = new FontFamily();
      mFontCache.put(fontFamilyName, fontFamily);
    }

    //CRN BEGIN
    CustomTypeface cacheType = fontFamily.getTypeface(style);
    if (cacheType == null || (cacheType.ignoreCacheIfNeed && !needCacheFont)) {
      cacheType = createTypeface(fontFamilyName, style, fontPath, assetManager);
      if (cacheType != null && cacheType.loadFontSuccess) {
        fontFamily.setTypeface(style, cacheType);
      }
      return cacheType;
    }

    return cacheType;
    //CRN END
  }

  /*
   * This method allows you to load custom fonts from res/font folder as provided font family name.
   * Fonts may be one of .ttf, .otf or XML (https://developer.android.com/guide/topics/ui/look-and-feel/fonts-in-xml).
   * To support multiple font styles or weights, you must provide a font in XML format.
   *
   * ReactFontManager.getInstance().addCustomFont(this, "Srisakdi", R.font.srisakdi);
   */
  public void addCustomFont(@NonNull Context context, @NonNull String fontFamily, int fontId) {
    Typeface font = ResourcesCompat.getFont(context, fontId);
    if (font != null) {
      mCustomTypefaceCache.put(fontFamily, font);
    }
  }

  /**
   * Add additional font family, or replace the exist one in the font memory cache.
   * @param style
   * @see {@link Typeface#DEFAULT}
   * @see {@link Typeface#BOLD}
   * @see {@link Typeface#ITALIC}
   * @see {@link Typeface#BOLD_ITALIC}
   */
  public void setTypeface(String fontFamilyName, int style, Typeface typeface) {
    if (typeface != null) {
      FontFamily fontFamily = mFontCache.get(fontFamilyName);
      if (fontFamily == null) {
        fontFamily = new FontFamily();
        mFontCache.put(fontFamilyName, fontFamily);
      }

      // CRN BEGIN
      fontFamily.setTypeface(style, new CustomTypeface(typeface, true));
      // CRN END
    }
  }

  private static
  @Nullable CustomTypeface createTypeface(
      String fontFamilyName,
      int style,
      String fontPath,
      AssetManager assetManager) {

    // CRN BEGIN

    if (!fontFamilyName.startsWith("crn_font_")) {
      String extension = fontFamilyName.equals("ct_font_common") ? "" : EXTENSIONS[style];
    for (String fileExtension : FILE_EXTENSIONS) {
      String fileName = new StringBuilder()
          .append(FONTS_ASSET_PATH)
          .append(fontFamilyName)
          .append(extension)
          .append(fileExtension)
          .toString();
      try {
        return new CustomTypeface(Typeface.createFromAsset(assetManager, fileName), true);
      } catch (RuntimeException e) {
        // unfortunately Typeface.createFromAsset throws an exception instead of returning null
        // if the typeface doesn't exist
      }
    }

    } else if (!TextUtils.isEmpty(fontPath)) {
      try {
        fontPath = correctFontPath(fontPath);
        final String fontFilePath = fontPath + FONTS_ASSET_PATH + fontFamilyName + ".ttf";
        File fontFile = new File(fontFilePath);
        if (fontFile.exists()) {
          return new CustomTypeface(Typeface.createFromFile(fontFilePath), true);
        } else {
          return new CustomTypeface(null, false);
        }
      } catch (Exception ex) {
        return new CustomTypeface(Typeface.create(fontFamilyName, style), false);
      }
    }

    return new CustomTypeface(Typeface.create(fontFamilyName, style), true);
    // CRN END
  }
  //CRN BEGIN
  private static String correctFontPath(String fontPath) {
    return fontPath.substring(fontPath.startsWith("file://") ? 7 : 0, fontPath.lastIndexOf('/') + 1);
  }

  public static class CustomTypeface {
    public Typeface typeface;
    public boolean ignoreCacheIfNeed = true;
    public boolean loadFontSuccess = true;

    public CustomTypeface(Typeface typeface, boolean loadFontSuccess) {
      this.typeface = typeface;
      this.loadFontSuccess = loadFontSuccess;
    }
  }

  public void addExtendFontFamily(Map<String, FontFamily> extFamily) {
    if (extFamily != null) {
      mFontCache.putAll(extFamily);
    }
  }

  private static class FontFamily {

    private SparseArray<CustomTypeface> mTypefaceSparseArray;

    private FontFamily() {
      mTypefaceSparseArray = new SparseArray<>(4);
    }

    public CustomTypeface getTypeface(int style) {
      return mTypefaceSparseArray.get(style);
    }

    public void setTypeface(int style, CustomTypeface typeface) {
      mTypefaceSparseArray.put(style, typeface);
    }

  }
}
