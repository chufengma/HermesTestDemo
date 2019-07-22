package ctrip.wireless.android.crn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;

import ctrip.wireless.android.crn.business.R;
import ctrip.crn.instance.CRNInstanceState;
import ctrip.crn.instance.CRNPageInfo;
import ctrip.crn.utils.ReactNativeJson;
import ctrip.wireless.android.crn.instance.CRNInstanceManager;
import ctrip.wireless.android.crn.utils.LogUtil;
import ctrip.wireless.android.crn.utils.ThreadUtils;

public final class CRNBaseFragment extends Fragment
        implements DefaultHardwareBackBtnHandler
        , ReactRootView.OnReactRootViewDisplayCallback, CRNInstanceManager.ReactInstanceLoadedCallBack {

    /**
     * ReactRootView Displayed
     */
    public interface OnReactViewDisplayListener{
        void reactViewDisplayed();
    }

    /**
     * 加载错误、运行错误回调
     */
    public interface OnLoadRNErrorListener {
        void onErrorBrokeCallback(int errCode, String message);
    }

    public final static String CRNURL_KEY = "CRNURLKey";

    private static int MAX_BEAT_COUNT = 10;

    private OnLoadRNErrorListener mErrorListener;
    private OnReactViewDisplayListener mDisplayListener;

    private FrameLayout mRNContainer;

    private CRNURL mCRNURL;
    private ReactInstanceManager mReactInstanceManager;
    private ReactRootView mReactRootView;

    private boolean mReactRootViewDisplay;
    private int mReactBeatCount = 0;
    private boolean mLoadingTimeout;
    private boolean mFragmentDisplaying;
    private CRNPageInfo crnPageInfo;
    private boolean entryRecord = false;
    private long createViewTime;

    private Runnable mCheckTimeoutRun = new Runnable() {
        @Override
        public void run() {
            if (mFragmentDisplaying) { // 如果当前页面正在显示，则增加timeout计时
                mReactBeatCount++;
            }
            if (mReactRootViewDisplay || mLoadingTimeout) {
                LogUtil.e("react", "Success in displaying ReactView");
                ThreadUtils.removeCallback(mCheckTimeoutRun);
            } else if (mReactBeatCount > MAX_BEAT_COUNT
                    && (mCRNURL != null && !TextUtils.isEmpty(mCRNURL.getUrl())
                    && !mCRNURL.getUrl().startsWith("http://"))) {
                mLoadingTimeout = true;
                if (mReactInstanceManager != null && mReactInstanceManager.getCRNInstanceInfo() != null) {
                    mReactInstanceManager.getCRNInstanceInfo().countTimeoutError++;
                }
                invokeError(CRNErrorCode.RENDER_TIMEOUT, "CRN load timeout(>10s) error, show retry page：", mFragmentDisplaying);
            } else {
                ThreadUtils.postDelayed(mCheckTimeoutRun, 1000);
            }
        }
    };


    /**
     * CRNBaseFragment
     */
    public CRNBaseFragment() {
        crnPageInfo = CRNPageInfo.newCRNPageInfo("CRNBaseFragment");
        createViewTime = System.currentTimeMillis();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CRNInstanceManager.checkAndInstallCommonJS();
        mReactRootViewDisplay = false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle instanceState) {
        View view = View.inflate(getActivity(), R.layout.rn_fragment, null);
        mRNContainer = view.findViewById(R.id.rnRootContainer);

        if (getArguments() != null && getArguments().containsKey(CRNURL_KEY) && !TextUtils.isEmpty(getArguments().getString(CRNURL_KEY))) {
            mCRNURL = new CRNURL(getArguments().getString(CRNURL_KEY));
            loadCRNView();
        } else {
            invokeError(CRNErrorCode.ILLEAGAL_ARGUMENTS, "Trying to load ReactInstance which CRNURL is null.", true);
        }
        return view;
    }

    private void loadCRNView() {
        mReactInstanceManager = CRNInstanceManager.getReactInstance(mCRNURL, crnPageInfo, CRNBaseFragment.this);
        if (mReactInstanceManager != null && mReactInstanceManager.getCRNInstanceInfo() != null) {
            if (!entryRecord) {
                entryRecord = true;
                mReactInstanceManager.getCRNInstanceInfo().usedCount++;
            }
        }

        // 开始计时 & 重置状态
        mReactBeatCount = 0;
        mLoadingTimeout = false;
        mReactRootViewDisplay = false;

        // 记录进入CRNPage
        CRNInstanceManager.enterCRNPage(mReactInstanceManager, mCRNURL);

        ThreadUtils.removeCallback(mCheckTimeoutRun);
        ThreadUtils.postDelayed(mCheckTimeoutRun, 1000);
    }

    @Override
    public void onReactInstanceLoaded(final ReactInstanceManager reactInstanceManager, final int emitMsgRet) {
        boolean hasError = false;
        if (reactInstanceManager == null || getActivity() == null
                || emitMsgRet != 0
                || reactInstanceManager.getCRNInstanceInfo() == null
                || reactInstanceManager.getCRNInstanceInfo().instanceState == CRNInstanceState.Error) {
            hasError = true;
        }

        if (!hasError) {
            mReactInstanceManager = reactInstanceManager;

            // 触发JS加载业务模块
            if(mCRNURL != null && mCRNURL.isUnbundleURL()) {
                CRNInstanceManager.emitReactInstanceReadyMessage(mReactInstanceManager, createViewTime);
            }

            if (mReactRootView != null && mReactRootView.getReactInstanceManager() != null) {
                mReactRootView.unmountReactApplication();
            }
            mReactRootView = new ReactRootView(getActivity());
            mReactRootView.markEntryRootView(true);
            mReactRootView.setAllowStatistic(true);
            mReactRootView.setReactRootViewDisplayCallback(CRNBaseFragment.this);
            if (mRNContainer != null) {
                mRNContainer.removeAllViews();
                mRNContainer.addView(mReactRootView, new FrameLayout.LayoutParams(-1, -1));
            }
            mReactRootView.startReactApplication(mReactInstanceManager, mCRNURL.getModuleName(), getLaunchOptions(mCRNURL));

            if (CRNInstanceManager.isReactInstanceReady(mReactInstanceManager)) {
                instanceHostResume();

                //检测display是否执行
                mReactBeatCount = 0;
                ThreadUtils.removeCallback(mCheckTimeoutRun);
                ThreadUtils.post(mCheckTimeoutRun);
            }
        } else {
            invokeError(CRNErrorCode.LOAD_INSTANCE_FAIL,"Trying to load ReactInstance but failed.", true);
        }
    }

    private Bundle getLaunchOptions(CRNURL crnurl) {
        Bundle bundle = new Bundle();
        bundle.putString("containerSequenceId", crnPageInfo.crnPageID);
        if (crnurl != null && crnurl.getUrl() != null) {
            bundle.putBundle("urlQuery", ReactNativeJson.bundleFromMap(crnurl.getUrlQuery()));
            bundle.putString("url", crnurl.getUrl());
            bundle.putString("initialProperties", crnurl.initParams);
        }
        return bundle;
    }

    private void instanceHostResume() {
        if (getActivity() != null) {
            if (mReactInstanceManager != null) {
                mReactInstanceManager.onHostResume(getActivity(), CRNBaseFragment.this);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mFragmentDisplaying = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (CRNInstanceManager.isReactInstanceReady(mReactInstanceManager)) {
            instanceHostResume();
        }

        if (mReactInstanceManager != null &&
                mReactInstanceManager.getCRNInstanceInfo() != null &&
                mReactInstanceManager.getCRNInstanceInfo().isRendered) {
            mReactInstanceManager.getCRNInstanceInfo().usedTimestamp = System.currentTimeMillis();
        }
    }

    @Override
    public void reactRootViewPageDisplay() {
        mReactRootViewDisplay = true;
        ThreadUtils.removeCallback(mCheckTimeoutRun);
        if (mDisplayListener != null) {
            mDisplayListener.reactViewDisplayed();
        }
    }

    public void invokeError(final int errorCode, final String message, boolean isFatal) {
        LogUtil.e("ReactError", "errorFrom: " + message + "|error: " + errorCode);

        if (isFatal && mReactInstanceManager != null && mReactInstanceManager.getCRNInstanceInfo() != null) {
            mReactInstanceManager.getCRNInstanceInfo().instanceState = CRNInstanceState.Error;
        }

        ThreadUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mErrorListener != null) {
                    mErrorListener.onErrorBrokeCallback(errorCode, message);
                }
            }
        });
    }

    /**
     * 设置error回调
     * @param errorListener errorListener
     */
    public void setLoadRNErrorListener(OnLoadRNErrorListener errorListener) {
        this.mErrorListener = errorListener;
    }

    /**
     * 设置ReactRootView显示回调
     * @param listener listener
     */
    public void setReactViewDisplayListener(OnReactViewDisplayListener listener) {
        this.mDisplayListener = listener;
    }

    /**
     * @return ReactInstanceManager
     */
    public ReactInstanceManager getReactInstanceManager() {
        return mReactInstanceManager;
    }

    /**
     * @return ReactRootView
     */
    public ReactRootView getReactRootView() {
        return mReactRootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getActivity() != null) {
            if (CRNInstanceManager.isReactInstanceReady(mReactInstanceManager)) {
                try {
                    mReactInstanceManager.onHostPause(getActivity());
                } catch (AssertionError ignore) {
                }
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        mFragmentDisplaying = false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mLoadingTimeout = true;

        ThreadUtils.removeCallback(mCheckTimeoutRun);

        if (mReactRootView != null) {
            try {
                ViewParent parent = mReactRootView.getParent();
                if (parent != null) {
                    ((ViewGroup) parent).removeView(mReactRootView);
                }
                mReactRootView.unmountReactApplication();
                mReactRootView = null;
                mReactInstanceManager.detachRootView(mReactRootView);
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }

        // 记录离开CRNPage
        CRNInstanceManager.exitCRNPage(mReactInstanceManager, mCRNURL);

        if (getActivity() != null && mReactInstanceManager != null) {
            mReactInstanceManager.onHostDestroy(getActivity());
        }

        if (mRNContainer != null){
            mRNContainer = null;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mReactInstanceManager.onActivityResult(getActivity(), requestCode, resultCode, data);
    }

    public void goBack() {
        if (getActivity() != null) {
            getActivity().getWindow().getDecorView().clearAnimation();
            if (CRNInstanceManager.isReactInstanceReady(mReactInstanceManager)) {
                mReactInstanceManager.onBackPressed();
            } else {
                getActivity().onBackPressed();
            }
        }
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        if (getActivity() != null && getActivity() instanceof FragmentActivity) {
            ((FragmentActivity) getActivity()).onBackPressed();
        }
    }

}
