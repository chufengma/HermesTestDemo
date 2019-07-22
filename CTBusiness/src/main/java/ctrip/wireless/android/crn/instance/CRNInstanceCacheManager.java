/**
 * Copyright (c) Ctrip, Inc. and its affiliates.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package ctrip.wireless.android.crn.instance;

import android.text.TextUtils;

import com.facebook.react.ReactInstanceManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import ctrip.crn.instance.CRNInstanceInfo;
import ctrip.crn.instance.CRNInstanceState;
import ctrip.wireless.android.crn.CRNURL;
import ctrip.wireless.android.crn.utils.StringUtil;

public class CRNInstanceCacheManager {

    private static ArrayList<ReactInstanceManager> mCachedInstanceList = new ArrayList<>();
    private static final int Max_Dirty_Instance_Count = 6;

    private static Map<String, Boolean> reuseInstanceList = new HashMap<>();

    /**
     * 获取CRNInstance
     * 将按照业务缓存->common缓存的级别来寻找Instance
     */
    public static ReactInstanceManager getInstanceIfExist(CRNURL crnurl) {
        if (crnurl == null) {
            return null;
        }
        boolean ignoreCache = crnurl.ignoreCache();

        ReactInstanceManager readyToUseInstance = null;

        if (!ignoreCache) {
                // Dirty 且 useCount=0 的CacheInstance
                for (ReactInstanceManager mng : mCachedInstanceList) {
                    CRNInstanceInfo instanceInfo = null;
                    if (mng == null || ((instanceInfo = mng.getCRNInstanceInfo()) == null)) {
                        continue;
                    }
                    boolean isSameRNPage = StringUtil.equalsIgnoreCase(crnurl.urlStr, instanceInfo.businessURL);
                    if (instanceInfo.instanceState == CRNInstanceState.Dirty
                                && instanceInfo.inUseCount == 0 && isSameRNPage) {
                        readyToUseInstance = mng;
                    }
                }
        }

        if (readyToUseInstance == null) {
            readyToUseInstance = getCacheCommonReactInstance();
        }

        return readyToUseInstance;
    }

    /**
     * 获取Common CRNInstance
     */
    public static ReactInstanceManager getCacheCommonReactInstance() {
        for (ReactInstanceManager mng : mCachedInstanceList) {
            CRNInstanceInfo instanceInfo = null;
            if (mng == null || ((instanceInfo = mng.getCRNInstanceInfo()) == null)) {
                continue;
            }

            if (instanceInfo.instanceState == CRNInstanceState.Ready
                    && instanceInfo.inUseCount == 0 && CRNURL.COMMON_BUNDLE_PATH.equalsIgnoreCase(instanceInfo.businessURL)) {
                return mng;
            }
        }
        return null;
    }

    static int getCacheCommonReactInstanceCount() {
        int readyCount = 0;
        for (ReactInstanceManager mng : mCachedInstanceList) {
            CRNInstanceState instanceState = mng.getCRNInstanceInfo().instanceState;
            if (instanceState == CRNInstanceState.Ready) {
                readyCount++;
            }
        }
        return readyCount;
    }

    /**
     * 缓存创建好的ReactInstanceManager
     * @param manager manager
     */
    static void cacheReactInstanceIfNeed(ReactInstanceManager manager) {
        if (manager == null) {
            return;
        }
        synchronized (CRNInstanceCacheManager.class) {
            if (!mCachedInstanceList.contains(manager)) {
                mCachedInstanceList.add(manager);
            }
        }
    }

    static void cachePreloadBuInstance(String productName, boolean isShared) {
        reuseInstanceList.put(productName, isShared);
    }

    /**
     * 维护Instance缓存策略，释放error状态instance
     */
    static void performLRUCheck() {
        synchronized (CRNInstanceCacheManager.class) {
            int dirtyCount = 0;
            ReactInstanceManager olderMng = null;
            Iterator<ReactInstanceManager> iterator = mCachedInstanceList.listIterator();
            while(iterator.hasNext()) {
                ReactInstanceManager manager = iterator.next();
                if (manager == null) {
                    iterator.remove();
                } else if (manager.getCRNInstanceInfo().instanceState == CRNInstanceState.Error) {
                    releaseReactInstance(manager);
                    iterator.remove();
                } else if (manager.getCRNInstanceInfo() != null
                        && manager.getCRNInstanceInfo().inUseCount <= 0
                        && manager.getCRNInstanceInfo().instanceState == CRNInstanceState.Dirty) {
                    dirtyCount++;
                    if (olderMng == null
                            || olderMng.getCRNInstanceInfo().usedTimestamp > manager.getCRNInstanceInfo().usedTimestamp) {
                        olderMng = manager;
                    }
                }
            }

            if (dirtyCount > Max_Dirty_Instance_Count) {
                deleteCachedInstance(olderMng);
            }
        }
    }

    static void deleteCachedInstance(ReactInstanceManager manager) {
        mCachedInstanceList.remove(manager);
        releaseReactInstance(manager);
    }

    /**
     * 释放ReactInstance在无法获取attachRootView状态下，确保可被完全释放
     * @param instanceManager instanceManager
     */
    static void releaseReactInstance(ReactInstanceManager instanceManager) {
        if (instanceManager != null) {
            try {
                if (instanceManager.getAttachedRootView() != null) {
                    instanceManager.detachRootView(instanceManager.getAttachedRootView());
                }
                instanceManager.destroy();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * 重置无法使用Dirty状态下的instance为error
     * @param url url
     */
    static void invalidateDirtyBridgeForURL(CRNURL url) {
        if (url == null || TextUtils.isEmpty(url.getUrl())) {
            return;
        }
        synchronized (mCachedInstanceList) {
            for (ReactInstanceManager bridge : mCachedInstanceList) {
                if ((bridge != null && bridge.getCRNInstanceInfo() != null && bridge.getCRNInstanceInfo().businessURL != null)) {
                    CRNURL bridgeUrl = new CRNURL(bridge.getCRNInstanceInfo().businessURL);
                    if (StringUtil.equalsIgnoreCase(url.getProductName(), bridgeUrl.getProductName())) {
                        bridge.getCRNInstanceInfo().instanceState = CRNInstanceState.Error;
                    }
                }
            }
        }
    }


}
