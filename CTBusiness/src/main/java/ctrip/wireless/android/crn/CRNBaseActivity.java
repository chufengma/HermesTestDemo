package ctrip.wireless.android.crn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.facebook.react.ReactInstanceManager;

import ctrip.wireless.android.crn.business.R;


public class CRNBaseActivity extends FragmentActivity implements
        CRNBaseFragment.OnLoadRNErrorListener, CRNBaseFragment.OnReactViewDisplayListener {

    public static final String INTENT_COMPONENT_NAME = "ComponentName";
    private static final String CRN_FRAGMENT_TAG = "crn_fragment_tag";


    private CRNBaseFragment mCRNBaseFragment;
    public CRNURL mCRNURL;
    private View loadingView;

    long startLoadTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startLoadTime = System.currentTimeMillis();
        mCRNURL = (CRNURL) getIntent().getSerializableExtra(INTENT_COMPONENT_NAME);
        if (mCRNURL == null || !CRNURL.isCRNURL(mCRNURL.getUrl())) {
            onErrorBrokeCallback(-1003, "");
            return;
        }

        setContentView(R.layout.rn_activity);
        loadingView = findViewById(R.id.rnloadingView);
        renderCRNBaseFragment();
    }

    private void renderCRNBaseFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (fragmentTransaction == null) {
            return;
        }
        mCRNBaseFragment = new CRNBaseFragment();
        mCRNBaseFragment.setLoadRNErrorListener(CRNBaseActivity.this);
        mCRNBaseFragment.setReactViewDisplayListener(CRNBaseActivity.this);
        try {
            Bundle bundle = new Bundle();
            bundle.putString(CRNBaseFragment.CRNURL_KEY, mCRNURL.getUrl());
            mCRNBaseFragment.setArguments(bundle);
        } catch (Exception ignore) {
        }
        fragmentTransaction.add(R.id.rnFragmentView, mCRNBaseFragment, CRN_FRAGMENT_TAG).commitAllowingStateLoss();
    }

    /**
     * get ReactInstanceManager
     */
    public ReactInstanceManager getReactInstanceManager() {
        return mCRNBaseFragment != null ? mCRNBaseFragment.getReactInstanceManager() : null;
    }

    public void hideLoading() {
        loadingView.setVisibility(View.GONE);
    }

    public void showLoading() {
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void reactViewDisplayed() {
        hideLoading();
        Toast.makeText(this, "page render cost " + (System.currentTimeMillis() - startLoadTime) + " ms", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorBrokeCallback(int errCode, String message) {
        Toast.makeText(this, "RN ERROR:" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mCRNBaseFragment != null) {
            mCRNBaseFragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode && mCRNBaseFragment != null) {
            mCRNBaseFragment.goBack();
            return true;
        } else if (KeyEvent.KEYCODE_MENU == keyCode) {
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCRNBaseFragment != null) {
            mCRNBaseFragment.setReactViewDisplayListener(null);
            mCRNBaseFragment.setLoadRNErrorListener(null);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

}