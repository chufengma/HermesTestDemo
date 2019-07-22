package ctrip.wireless.android.crn.instance;

import android.content.Context;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactInstanceManagerBuilder;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.DisplayMetricsHolder;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ctrip.crn.image.CRNAssetResourceHandler;
import ctrip.crn.image.CRNResourceUriHelper;
import ctrip.crn.instance.CRNInstanceInfo;
import ctrip.crn.instance.CRNInstanceState;
import ctrip.crn.instance.CRNLoadReportListener;
import ctrip.crn.instance.CRNPageInfo;
import ctrip.crn.instance.CRNReactContextLoadedListener;
import ctrip.crn.utils.ReactNativeJson;
import ctrip.wireless.android.crn.CRNProvider;
import ctrip.wireless.android.crn.CRNURL;
import ctrip.wireless.android.crn.CRNUnbundlePackage;
import ctrip.wireless.android.crn.ContextHolder;
import ctrip.wireless.android.crn.utils.LogUtil;
import ctrip.wireless.android.crn.utils.RNUtils;
import ctrip.wireless.android.crn.utils.StringUtil;
import ctrip.wireless.android.crn.utils.ThreadUtils;

public class CRNInstanceManager {

    public final static String kContainerViewReleaseMessageName = "containerViewDidReleased";

    /**
     * interface InitReactNativeCallBack
     */
    public interface ReactInstanceLoadedCallBack {

        /**
         * callback instance and status
         * @param instanceManager instanceManager
         * @param status instance状态码
         */
        void onReactInstanceLoaded(ReactInstanceManager instanceManager, int status);

    }

    /**
     * 一次性设置本地资源图片的获取方式
     */
    static {
        checkAndInstallCommonJS();

        CRNResourceUriHelper.setCRNAssetResourceHandler(new CRNAssetResourceHandler() {
            @Override
            public Uri resolveResource(Context context, Uri uri) {
                String imagePath = uri.getPath();
                return imagePath != null && new File(imagePath).exists() ? Uri.parse("file://" + imagePath) : null;
            }
        });
    }

    private static final String TOGGLE_LOAD_MODULE = "ToggleLoadModule";
    private static final String REACT_INSTANCE_READY_MESSAGE = "CRNStartLoadEvent";
    private static ArrayList<String> mInUsedCRNProduct = new ArrayList<>();

    public static boolean soLibError = false;

    /**
     * 所有Instance性能监控回调
     */
    private static CRNLoadReportListener mPerformanReportListener = new CRNLoadReportListener() {
        @Override
        public void onLoadComponentTime(ReactInstanceManager mng, long renderTime) {
            String productName = CRNURL.getProductName(mng.getCRNInstanceInfo().businessURL);

            double pkgLoadTime = 0;
            double commonLoadTime = 0;
            double bizRenderTime = 0;
            double totalTime = 0;
            double getInstanceTime = 0;

            CRNInstanceInfo crnInfo = mng.getCRNInstanceInfo();
            if (crnInfo != null) {
                pkgLoadTime = crnInfo.pkgDoneTime - crnInfo.enterViewTime;
                getInstanceTime = crnInfo.commonInstanceReadyTime - crnInfo.pkgDoneTime;
                bizRenderTime = crnInfo.renderDoneTime - crnInfo.commonInstanceReadyTime;
                totalTime = crnInfo.renderDoneTime  - crnInfo.enterViewTime;
                commonLoadTime = crnInfo.commonInstanceLoadFinishTime - crnInfo.commonInstanceLoadStatTime;
                pkgLoadTime = (pkgLoadTime<0)?0:pkgLoadTime;
                commonLoadTime = (commonLoadTime < 0)?0:commonLoadTime;
                getInstanceTime = (getInstanceTime < 0)?0:getInstanceTime;
            }

            HashMap<String, Object> userInfo = new HashMap<>();
            userInfo.put("pkgLoadTime", pkgLoadTime);
            userInfo.put("getInstanceTime", getInstanceTime);
            userInfo.put("renderTime", bizRenderTime);
            userInfo.put("totalTime", totalTime);
            userInfo.put("commonPreLoadTime", commonLoadTime);

            if (ContextHolder.debug) {
//                String tag = "";
//                if (mng.getCRNInstanceInfo().originalInstanceStatus == CRNInstanceState.Dirty) {
//                    tag = "业务缓存";
//                } else if (mng.getCRNInstanceInfo().originalInstanceStatus == CRNInstanceState.Ready) {
//                    tag = "框架缓存";
//                } else {
//                    tag = "无缓存";
//                }
//
//                String log =  ("[" + tag + "]框架JS耗时:"+ String.format("%.2f", (getInstanceTime/1000.0f)) +
//                        "s 业务Render耗时:" + String.format("%.2f", bizRenderTime/1000.0f) + "s");
//
//                Toast.makeText(ContextHolder.context,log, Toast.LENGTH_LONG).show();
            }

            WritableMap params = Arguments.createMap();
            params.putDouble("time", totalTime);
            // CRNInstanceManager.emitDeviceEventMessage(mng, "CRNLoadSuccessEvent", params);
        }
    };

    /**
     * 预创建ReactInstanceManager
     */
    public static void prepareReactInstanceIfNeed() {
        int readyCount = CRNInstanceCacheManager.getCacheCommonReactInstanceCount();
        if (readyCount >= 2) {
            LogUtil.e("CRN Instance ready count ="+readyCount);
            return;
        }

        ThreadUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                CRNInstanceInfo crnInstanceInfo = new CRNInstanceInfo();
                crnInstanceInfo.businessURL = CRNURL.COMMON_BUNDLE_PATH;
                crnInstanceInfo.instanceState = CRNInstanceState.Loading;
                crnInstanceInfo.errorReportListener = CRNErrorHandler.getErrorReportListener();
                crnInstanceInfo.loadReportListener = mPerformanReportListener;
                setupCRNInstanceInfoExtroInfo(crnInstanceInfo);
                createBundleInstance(new CRNURL(CRNURL.COMMON_BUNDLE_PATH), null, crnInstanceInfo, null);
            }
        }, 1000);
    }

    /**
     * 创建OnlineBundle、CacheUnbundle、AllUnbundle统一入口
     * @param rnURL rnURL
     * @param bundleScript bundleScript
     * @param crnInstanceInfo crnInstanceInfo
     * @param callBack callBack
     * @return ReactInstanceManagerpre
     */
    private static ReactInstanceManager createBundleInstance(final CRNURL rnURL,
                                                             String bundleScript,
                                                             CRNInstanceInfo crnInstanceInfo,
                                                             final ReactInstanceLoadedCallBack callBack) {

        if (rnURL == null || TextUtils.isEmpty(rnURL.getUrl())) {
            //极少，没有该错误
            callBack.onReactInstanceLoaded(null, -201);
            return null;
        }

        final boolean isOnlineBundle = rnURL.getRnSourceType() == CRNURL.SourceType.Online ;
        final boolean isNormalBundle = !isOnlineBundle && !StringUtil.isEmpty(bundleScript);
        final  boolean isCommonBundle = CRNURL.COMMON_BUNDLE_PATH.equalsIgnoreCase(rnURL.getUrl());
        final boolean isUnbundleBizURL =  rnURL.isUnbundleURL();
        final boolean isCRNUnbundle = isCommonBundle || isUnbundleBizURL;

        ReactInstanceManagerBuilder builder = ReactInstanceManager.builder();
        builder.setApplication(ContextHolder.application);
        builder.setInitialLifecycleState(LifecycleState.BEFORE_CREATE);
        builder.setCRNInstanceInfo(crnInstanceInfo);
        crnInstanceInfo.commonInstanceLoadStatTime = System.currentTimeMillis();
        for (ReactPackage reactPackage: CRNProvider.provideReactPackages()) {
            builder.addPackage(reactPackage);
        }

        if (isOnlineBundle) {
            builder.setUseDeveloperSupport(true);
            builder.setJSMainModulePath("index.android");
            builder.setBundleScript(bundleScript, rnURL.getUrl(), false);
            Uri uri = Uri.parse(rnURL.getUrl());
            String debugUrl = uri.getHost() + ":" + (uri.getPort() == -1 ? 80 : uri.getPort());
            PreferenceManager.getDefaultSharedPreferences(ContextHolder.context)
                    .edit().putString("debug_http_host", debugUrl).apply();
        }
        else if (isNormalBundle) {
            builder.setUseDeveloperSupport(false);
            builder.setBundleScript(bundleScript, rnURL.getUrl(), false);
            builder.setNativeModuleCallExceptionHandler(CRNErrorHandler.getNativeExceptionHandler());
            PreferenceManager.getDefaultSharedPreferences(ContextHolder.context)
                    .edit().remove("debug_http_host").apply();
        }
        else if (isCRNUnbundle){
            builder.setUseDeveloperSupport(false);
            builder.setJSBundleFile(CRNURL.COMMON_BUNDLE_PATH);
            builder.setNativeModuleCallExceptionHandler(CRNErrorHandler.getNativeExceptionHandler());
            PreferenceManager.getDefaultSharedPreferences(ContextHolder.context)
                    .edit().remove("debug_http_host").apply();
        }
        final ReactInstanceManager instanceManager = builder.build();
        instanceManager.setReactContextLoadedListener(new CRNReactContextLoadedListener() {
            boolean isInstanceLoaded = false;

            @Override
            public void onReactContextLoaded(ReactInstanceManager reactInstance) {
                if (ContextHolder.debug) {
                    if (isInstanceLoaded) {
                        return;
                    }
                    isInstanceLoaded = true;
                }
                int resultStatus = 0;
                if (reactInstance == null || reactInstance.getCRNInstanceInfo() == null || reactInstance.getCatalystInstance() == null)  {
                    //极少，无此错误
                    resultStatus = -301;
                }
                else if (reactInstance.getCRNInstanceInfo().instanceState == CRNInstanceState.Error) {
                    //较多，common错误，为和ios一直，修改成-505
                    resultStatus = -505;//rn_common加载失败，会走到这个分支，先前版本为-304
                }
                else {
                    CRNInstanceInfo instanceInfo = reactInstance.getCRNInstanceInfo();
                    if (isOnlineBundle || isNormalBundle) {
                        instanceInfo.instanceState = CRNInstanceState.Dirty;
                        reactInstance.setModulePath(RNUtils.getFontDownloadPath());
                        reactInstance.getCatalystInstance().setSourceURL(rnURL.getUrl());
                    }
                    else if (isCRNUnbundle) {
                        instanceInfo.commonInstanceLoadFinishTime = System.currentTimeMillis();
                        HashMap<String, Object> userMap = new HashMap<>();
                        long costTime = (instanceInfo.commonInstanceLoadFinishTime - instanceInfo.commonInstanceLoadStatTime);
                        userMap.put("status" , resultStatus + "");
                        LogUtil.e("o_crn_common_load_finished, instanceID:" + reactInstance.getCRNInstanceInfo().instanceID + "," + costTime);
                        if (isUnbundleBizURL) {
                            instanceInfo.instanceState = CRNInstanceState.Ready;
                            CRNUnbundlePackage unbundlePackage = new CRNUnbundlePackage(rnURL);
                            if (unbundlePackage.getModuleConfigHashMap() == null || unbundlePackage.getModuleConfigHashMap().isEmpty()) {
                                //极少，无此错误
                                resultStatus = -305;
                            } else {
                                reactInstance.getCatalystInstance().setCRNModuleIdConfig(unbundlePackage.getModuleConfigHashMap());
                                resultStatus = emitReRenderMessage(reactInstance, unbundlePackage.getMainModuleId(), rnURL.getUrl(), false);
                            }
                        } else {
                            instanceInfo.instanceState = CRNInstanceState.Ready;
                            resultStatus = -306;
                        }

                        cacheReactInstance(reactInstance);
                    }
                }

                if (callBack != null) {
                    callBack.onReactInstanceLoaded(reactInstance, resultStatus);
                }
            }
        });
        instanceManager.createReactContextInBackground();
        return instanceManager;
    }

    /**
     * 构建在线onlineBundle
     * @param rnURL rnURL
     * @param callBack callBack
     */
    private static ReactInstanceManager createOnlineReactInstance(CRNURL rnURL, ReactInstanceLoadedCallBack callBack) {
        File file = new File(ContextHolder.context.getFilesDir(), "ReactNativeDevBundle.js");
        if (file.exists()) {
            file.delete();
        }
        CRNInstanceInfo crnInstanceInfo = new CRNInstanceInfo();
        crnInstanceInfo.businessURL = rnURL.getUrl();
        crnInstanceInfo.instanceState = CRNInstanceState.Loading;
        crnInstanceInfo.originalInstanceStatus = CRNInstanceState.Loading;
        crnInstanceInfo.errorReportListener = CRNErrorHandler.getErrorReportListener();
        crnInstanceInfo.loadReportListener = mPerformanReportListener;
        return createBundleInstance(rnURL, "{}", crnInstanceInfo, callBack);
    }


    /**
     * 获取ReactInstanceManager
     * @param rnURL rnURL
     * @param callBack callBack
     */
    public static ReactInstanceManager getReactInstance(final CRNURL rnURL, CRNPageInfo crnPageInfo, final ReactInstanceLoadedCallBack callBack) {
        return getReactInstance(rnURL, crnPageInfo, false, false, callBack);
    }

    /**
     * 获取ReactInstanceManager
     * @param rnURL rnURL
     * @param callBack callBack
     */
    public static ReactInstanceManager getReactInstance(final CRNURL rnURL, CRNPageInfo crnPageInfo, boolean isPreLoad, boolean isShared, final ReactInstanceLoadedCallBack callBack) {
        ReactInstanceManager reactInstance = null;
        int errorStatus = 0;
        boolean needCallbackRightNow = false;
        if (rnURL == null || !CRNURL.isCRNURL(rnURL.getUrl())) {
            if (rnURL == null) {
                errorStatus = -101;
            } else if (!CRNURL.isCRNURL(rnURL.getUrl())) {
                errorStatus = -102;
            }
            needCallbackRightNow = true;
        } else if (rnURL.getRnSourceType() == CRNURL.SourceType.Online) {
            reactInstance = createOnlineReactInstance(rnURL, callBack);
        } else {
            String crnURLStr = rnURL.getUrl();
            if(rnURL.isUnbundleURL()) { //unbundle格式，处理cache策略
                ReactInstanceManager readyCachedInstance = null;
                ReactInstanceManager dirtyCachedInstance = null;

                ReactInstanceManager readyToUseInstance = CRNInstanceCacheManager.getInstanceIfExist(rnURL);
                if (readyToUseInstance != null) {
                    if (readyToUseInstance.getCRNInstanceInfo().instanceState == CRNInstanceState.Dirty) {
                        dirtyCachedInstance = readyToUseInstance;
                    } else if (readyToUseInstance.getCRNInstanceInfo().instanceState == CRNInstanceState.Ready) {
                        readyCachedInstance = readyToUseInstance;
                    }
                }

                CRNUnbundlePackage unbundlePackage = new CRNUnbundlePackage(rnURL);
                if (dirtyCachedInstance != null) {
                    reactInstance = dirtyCachedInstance;
                    reactInstance.getCRNInstanceInfo().originalInstanceStatus = CRNInstanceState.Dirty;
                    reactInstance.getCRNInstanceInfo().commonInstanceReadyTime = System.currentTimeMillis();
                    reactInstance.getCRNInstanceInfo().countTimeoutError = 0;
                    reactInstance.getCRNInstanceInfo().countJSFatalError = 0;
                    reactInstance.getCRNInstanceInfo().countLogFatalError = 0;
                    reactInstance.getCRNInstanceInfo().countNativeFatalError = 0;
                    reactInstance.getCRNInstanceInfo().crnPageInfo = crnPageInfo;
                    emitDimensionChangeMessage(dirtyCachedInstance);

                    needCallbackRightNow = true;
                } else if (readyCachedInstance != null) {
                    if (unbundlePackage.getModuleConfigHashMap() == null || unbundlePackage.getModuleConfigHashMap().isEmpty()) {
                        errorStatus = -103;
                    } else {
                        readyCachedInstance.getCRNInstanceInfo().businessURL = crnURLStr;
                        readyCachedInstance.getCRNInstanceInfo().isUnbundle = true;
                        readyCachedInstance.getCRNInstanceInfo().inUseProductName = rnURL.getProductName();
                        readyCachedInstance.getCRNInstanceInfo().loadReportListener = mPerformanReportListener;
                        readyCachedInstance.getCRNInstanceInfo().errorReportListener = CRNErrorHandler.getErrorReportListener();
                        readyCachedInstance.getCRNInstanceInfo().crnPageInfo = crnPageInfo;
                        readyCachedInstance.getCRNInstanceInfo().isBusinessPreload = isPreLoad;
                        readyCachedInstance.getCatalystInstance().setCRNModuleIdConfig(unbundlePackage.getModuleConfigHashMap());
                        setupCRNInstanceInfoExtroInfo(readyCachedInstance.getCRNInstanceInfo());
                        if (readyCachedInstance.getCRNInstanceInfo().extroInfo != null) {
                            readyCachedInstance.getCRNInstanceInfo().extroInfo.put("instanceFromPreLoad", isPreLoad + "");
                        }
                        readyCachedInstance.getCRNInstanceInfo().originalInstanceStatus = CRNInstanceState.Ready;
                        int emitMsgRet = emitReRenderMessage(readyCachedInstance, unbundlePackage.getMainModuleId(), crnURLStr, true);
                        if (emitMsgRet == 0) {
                            errorStatus = 0;
                            reactInstance = readyCachedInstance;
                        } else {
                            errorStatus = -104;
                        }

                        //Ready的被使用了，预创建
                        prepareReactInstanceIfNeed();
                    }

                    needCallbackRightNow = true;
                }
            }

            if (reactInstance == null && errorStatus == 0) {
                CRNInstanceInfo instanceInfo = new CRNInstanceInfo();
                instanceInfo.isUnbundle = true;
                instanceInfo.businessURL = crnURLStr;
                instanceInfo.originalInstanceStatus = CRNInstanceState.Loading;
                instanceInfo.instanceState = CRNInstanceState.Loading;
                instanceInfo.inUseProductName = rnURL.getProductName();
                instanceInfo.loadReportListener = mPerformanReportListener;
                instanceInfo.errorReportListener = CRNErrorHandler.getErrorReportListener();
                instanceInfo.crnPageInfo = crnPageInfo;
                instanceInfo.isBusinessPreload = isPreLoad;
                setupCRNInstanceInfoExtroInfo(instanceInfo);
                if (instanceInfo.extroInfo != null) {
                    instanceInfo.extroInfo.put("instanceFromPreLoad", isPreLoad + "");
                }
                String bundleScript = null;
                if(!rnURL.isUnbundleURL()) {
                    instanceInfo.commonInstanceLoadFinishTime = instanceInfo.enterViewTime;
                    instanceInfo.commonInstanceReadyTime = instanceInfo.enterViewTime;
                    instanceInfo.pkgDoneTime = instanceInfo.enterViewTime;
                    bundleScript = RNUtils.file2String(new File(rnURL.getAbsoluteFilePath()));
                }
                reactInstance = createBundleInstance(rnURL, bundleScript, instanceInfo, callBack);
                if (reactInstance == null) {
                    errorStatus = -105;
                }
            }
        }

        if (ContextHolder.debug && errorStatus != 0) {
            Toast.makeText(ContextHolder.context
                    , "createReactInstance error: status=" + errorStatus
                    , Toast.LENGTH_SHORT).show();
        }

        if (reactInstance != null && reactInstance.getCRNInstanceInfo() != null) {
            reactInstance.getCRNInstanceInfo().countTimeoutError = 0;
            reactInstance.getCRNInstanceInfo().countJSFatalError = 0;
            reactInstance.getCRNInstanceInfo().countLogFatalError = 0;
            reactInstance.getCRNInstanceInfo().countNativeFatalError = 0;
            reactInstance.getCRNInstanceInfo().crnPageInfo = crnPageInfo;
        }

        if (needCallbackRightNow) {
            callBack.onReactInstanceLoaded(reactInstance, errorStatus);
        }

        cacheReactInstance(reactInstance);

        if (isPreLoad && rnURL != null && rnURL.getProductName() != null) {
            CRNInstanceCacheManager.cachePreloadBuInstance(rnURL.getProductName(), isShared);
        }

        return reactInstance;
    }

    /**
     * 缓存创建好的ReactInstanceManager
     * @param manager manager
     */
    private static void cacheReactInstance(ReactInstanceManager manager) {
        CRNInstanceCacheManager.cacheReactInstanceIfNeed(manager);
    }

    /**
     * 离开RN容器页面，减少ReactInstanceManager的引用计数
     * @param  manager manager
     */
    public static void decreaseReactInstanceRetainCount(ReactInstanceManager manager, CRNURL crnurl) {
        synchronized (CRNInstanceManager.class) {
            if (manager != null && manager.getCRNInstanceInfo() != null) {
                manager.getCRNInstanceInfo().inUseCount -= 1;
                CRNInstanceCacheManager.performLRUCheck();
            }
        }
    }

    /**
     * 进入RN容器页面，增加ReactInstanceManager的引用计数
     * @param manager manager
     */
    public static void increaseReactInstanceRetainCount(ReactInstanceManager manager) {
        synchronized (CRNInstanceManager.class) {
            if (manager != null && manager.getCRNInstanceInfo() != null) {
                manager.getCRNInstanceInfo().inUseCount += 1;
            }
        }
    }

    /**
     * 重置无法使用Dirty状态下的instance为error
     * @param url url
     */
    public static void invalidateDirtyBridgeForURL(CRNURL url) {
        CRNInstanceCacheManager.invalidateDirtyBridgeForURL(url);
    }

    /**
     * 解决全明面屏的屏幕高度赋值问题
     * @param mng mng
     */
    private static void emitDimensionChangeMessage(ReactInstanceManager mng) {
        if (mng != null) {
            DisplayMetrics windowDisplayMetrics = DisplayMetricsHolder.getWindowDisplayMetrics();
            DisplayMetrics screenDisplayMetrics = DisplayMetricsHolder.getScreenDisplayMetrics();
            float fontScale = ContextHolder.context.getResources().getConfiguration().fontScale;

            WritableMap windowDisplayMetricsMap = Arguments.createMap();
            windowDisplayMetricsMap.putInt("width", windowDisplayMetrics.widthPixels);
            windowDisplayMetricsMap.putInt("height", windowDisplayMetrics.heightPixels);
            windowDisplayMetricsMap.putDouble("scale", windowDisplayMetrics.density);
            windowDisplayMetricsMap.putDouble("fontScale", fontScale);
            windowDisplayMetricsMap.putDouble("densityDpi", windowDisplayMetrics.densityDpi);

            WritableMap screenDisplayMetricsMap = Arguments.createMap();
            screenDisplayMetricsMap.putInt("width", screenDisplayMetrics.widthPixels);
            screenDisplayMetricsMap.putInt("height", screenDisplayMetrics.heightPixels);
            screenDisplayMetricsMap.putDouble("scale", screenDisplayMetrics.density);
            screenDisplayMetricsMap.putDouble("fontScale", fontScale);
            screenDisplayMetricsMap.putDouble("densityDpi", screenDisplayMetrics.densityDpi);

            WritableMap dimensionsMap = Arguments.createMap();
            dimensionsMap.putMap("windowPhysicalPixels", windowDisplayMetricsMap);
            dimensionsMap.putMap("screenPhysicalPixels", screenDisplayMetricsMap);
            dimensionsMap.putBoolean("fromCRN", true);

            emitDeviceEventMessage(mng, "didUpdateDimensions", dimensionsMap);
        }
    }

    /**
     * ready状态下，直接发送初次加载时间消息
     * @param mng mng
     * @return
     */
    public static boolean emitReactInstanceReadyMessage(ReactInstanceManager mng, long startTime) {
        WritableMap params = Arguments.createMap();
        params.putDouble("startLoadTime", startTime);
        return emitDeviceEventMessage(mng, REACT_INSTANCE_READY_MESSAGE, params);
    }

    /**
     * Unbundle包，通知重新刷新页面
     * @param mng mng
     * @param mainModuleId mainModuleId
     */
    private static int emitReRenderMessage(ReactInstanceManager mng, String mainModuleId, String businessUrl, boolean fromCache) {
        int status = 0;
        if (TextUtils.isEmpty(mainModuleId)) {
            mainModuleId = "666666";
        }

        if (mng.getCRNInstanceInfo() == null) {
            status = -104;
        }

        if (status == 0) {
            // 重置rerenderFlag
            if (mng != null) {
                mng.getCRNInstanceInfo().usedTimestamp = System.currentTimeMillis();
                mng.getCRNInstanceInfo().commonInstanceReadyTime = System.currentTimeMillis();
                mng.setCRNGlobalVariable("___resetrenderFlag", "false");
                com.alibaba.fastjson.JSONObject object = new com.alibaba.fastjson.JSONObject();
                object.put("instanceID", mng.getCRNInstanceInfo().instanceID);
                object.put("buPkgId", mng.getCRNInstanceInfo().inUseProductPkgId);
                mng.setCRNGlobalVariable("__crn_bu", object.toJSONString());
            }

            if (businessUrl != null && businessUrl.contains("?")) {
                mng.setModulePath(businessUrl.substring(0, businessUrl.lastIndexOf("?")));
            } else {
                mng.setModulePath(businessUrl);
            }
            com.alibaba.fastjson.JSONObject params = new com.alibaba.fastjson.JSONObject();
            params.put("moduleId", mainModuleId);
            params.put("modulePath", businessUrl == null ? "" : businessUrl);
            params.put("inUsePkgId", mng.getCRNInstanceInfo().inUseProductPkgId);
            params.put("inUseCommonPkgId",  mng.getCRNInstanceInfo().inUseCommonPkgId);
            params.put("productName", mng.getCRNInstanceInfo().inUseProductName);
            params.put("inAppPkgId", "888888");
            params.put("inAppCommonPkgId", "888888");
            params.put("crnDev", ContextHolder.debug ? "1" : "-1");

            if (!emitDeviceEventMessage(mng, TOGGLE_LOAD_MODULE, ReactNativeJson.convertJsonToMap(params))) {
                status = -103;
            }

        }
        mng.getCRNInstanceInfo().instanceState = CRNInstanceState.Dirty;
        return status; //status 不为0，后续invokeError会将mng状态设置为Error
    }

//    /**
//     * emit message
//     * @param crnBaseActivity crnBaseActivity
//     * @param paramMap paramMap
//     */
//    public static boolean emitDeviceEventMessage(CRNBaseActivity crnBaseActivity, String eventName, WritableMap paramMap) {
//        if (crnBaseActivity == null || crnBaseActivity.getReactInstanceManager() == null) {
//            return false;
//        }
//        return emitDeviceEventMessage(crnBaseActivity.getReactInstanceManager(), eventName, paramMap);
//    }
//
//    /**
//     * emit message
//     * @param crnBaseFragment crnBaseFragment
//     * @param paramMap paramMap
//     */
//    public static boolean emitDeviceEventMessage(CRNBaseFragment crnBaseFragment, String eventName, WritableMap paramMap) {
//        if (crnBaseFragment == null || crnBaseFragment.getReactInstanceManager() == null) {
//            return false;
//        }
//        return emitDeviceEventMessage(crnBaseFragment.getReactInstanceManager(), eventName, paramMap);
//    }

    /**
     * emit message
     * @param instanceManager instanceManager
     * @param paramMap paramMap
     */
    public static boolean emitDeviceEventMessage(ReactInstanceManager instanceManager, String eventName, WritableMap paramMap) {
        if (!isReactInstanceReady(instanceManager)) {
            return false;
        }
        long startTime = System.currentTimeMillis();
        try {
            if (instanceManager.getCurrentReactContext() != null){
                instanceManager.getCurrentReactContext()
                        .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                        .emit(eventName, paramMap);
            }
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    /**
     * check current instance could be used
     * @param instanceManager instanceManager
     */
    public static boolean isReactInstanceReady(ReactInstanceManager instanceManager) {
        if (instanceManager != null && instanceManager.getCRNInstanceInfo() != null ) {
            CRNInstanceInfo crnInfo = instanceManager.getCRNInstanceInfo();
            if (crnInfo.instanceState == CRNInstanceState.Dirty ||
                    crnInfo.instanceState == CRNInstanceState.Ready) {
                if (crnInfo.countJSFatalError > 0 || crnInfo.countLogFatalError > 0 || crnInfo.countNativeFatalError > 0 || crnInfo.countTimeoutError > 0) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 进入CRNPage
     * @param crnurl crn业务Url
     */
    public static void enterCRNPage(ReactInstanceManager reactInstanceManager, CRNURL crnurl) {
        if (crnurl != null && crnurl.getProductName() != null) {
            mInUsedCRNProduct.add(crnurl.getProductName());
        }
        CRNInstanceManager.increaseReactInstanceRetainCount(reactInstanceManager);
    }

    /**
     * 离开CRNPage
     * @param crnurl crn业务Url
     */
    public static void exitCRNPage(ReactInstanceManager mReactInstanceManager, CRNURL crnurl) {
        if (crnurl != null && crnurl.getProductName() != null) {
            int outPageIndex = mInUsedCRNProduct.lastIndexOf(crnurl.getProductName());
            if (outPageIndex != -1 && outPageIndex >= 0 && outPageIndex < mInUsedCRNProduct.size() ) {
                mInUsedCRNProduct.remove(outPageIndex);
            }
        }
        if (mReactInstanceManager != null) {
            CRNInstanceManager.emitDeviceEventMessage(mReactInstanceManager, kContainerViewReleaseMessageName, null);
            CRNInstanceManager.decreaseReactInstanceRetainCount(mReactInstanceManager, crnurl);
        }
    }

    /**
     * 查询当前业务是否有使用的Page
     * @param url url
     */
    public static boolean hasCRNPage(CRNURL url) {
        if (url == null || TextUtils.isEmpty(url.getProductName())) {
            return false;
        }
        for (String productName : mInUsedCRNProduct) {
            if (StringUtil.equalsIgnoreCase(productName, url.getProductName())) {
                return true;
            }
        }
        return false;
    }

    private static void setupCRNInstanceInfoExtroInfo(CRNInstanceInfo crnInstanceInfo) {
        if (crnInstanceInfo == null) {
            return;
        }
        crnInstanceInfo.extroInfo = new HashMap<>();
        Map<String, String> commonInfo = new HashMap<>();
        commonInfo.put("instanceID", crnInstanceInfo.instanceID + "");
        crnInstanceInfo.extroInfo.put("__crn_common", JSON.toJSONString(commonInfo));
    }

    public static void checkAndInstallCommonJS() {
        // commonDir存在，commonJS文件不存在，则删除commDir，重新安装
        File file = new File(CRNURL.COMMON_BUNDLE_PATH);
        File commonDir = new File( CRNURL.getRNBundleWorkPath() + "/" + CRNURL.RN_COMMON_PACKAGE_NAME);
        if (commonDir != null && commonDir.exists()
                && (file == null || !file.exists())) {
            RNUtils.delDir(CRNURL.getRNBundleWorkPath() + "/" + CRNURL.RN_COMMON_PACKAGE_NAME);
            LogUtil.e("CRNCheck", "commonDir exist, commonJS not found, delete commonDir!");
        }

        PackageManager.installPackageForProduct(CRNURL.RN_COMMON_PACKAGE_NAME);
    }

}

