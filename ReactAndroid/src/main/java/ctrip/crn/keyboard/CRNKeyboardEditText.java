package ctrip.crn.keyboard;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.facebook.common.logging.FLog;
import com.facebook.react.R;
import com.facebook.react.common.ReactConstants;

import java.lang.reflect.Method;
import java.util.List;

import static android.view.inputmethod.InputMethodManager.HIDE_NOT_ALWAYS;

/**
 * 自定义EditText,touch弹出自定义软键盘
 */
@SuppressLint("AppCompatCustomView")
public class CRNKeyboardEditText extends EditText implements View.OnFocusChangeListener {

    public static final String SOFT_KEYBOARD_IDENTITY = "identity";

    protected InputMethodManager mInputMethodManager;

    private View mRootView;
    private Dialog mDialog;
    private int mKeyboardHeight = 0;
    private int mKeyboardRes;
    protected boolean mUseIdentityKeyBoard = false;
    private float originTransX = 0;
    private String ctripKeyboardType;
    private List<String> ctripKeyboardTextList = null;

    private OnFocusChangeListener mFocusChangeListener;

    /**
     * CRNKeyboardEditText
     *
     * @param context context
     */
    public CRNKeyboardEditText(Context context) {
        super(context);
        mInputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        setOnFocusChangeListener(this);
    }

    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener l) {
        super.setOnFocusChangeListener(this);
        if (l != this) {
            mFocusChangeListener = l;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (mUseIdentityKeyBoard && !hasFocus && mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
        if (mFocusChangeListener != null) {
            mFocusChangeListener.onFocusChange(v, hasFocus);
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (mUseIdentityKeyBoard
                && event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!mUseIdentityKeyBoard) {
            return super.onTouchEvent(event);
        }
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            forceHideSoftInput();
        }

        requestFocus();
        enableSystemKeyboard(false);

        if (event.getAction() == MotionEvent.ACTION_UP) {
            boolean result = super.onTouchEvent(event);
            initKeyboardDialog();
            return result;
        }
        return super.onTouchEvent(event);
    }

    private void forceHideSoftInput() {
        if (mInputMethodManager != null) {
            mInputMethodManager.hideSoftInputFromWindow(getWindowToken(), HIDE_NOT_ALWAYS);
        }
    }

    private void initKeyboardDialog() {
        if (mDialog == null) {
            mDialog = initDialog();
            initKeyboardHeight();
        }

        modifyContentHeight();

        if (!mDialog.isShowing()) {
            mDialog.show();
        }
    }

    private void modifyContentHeight() {
        try {
            originTransX = getRootView().getTranslationY();
            if (mKeyboardHeight > 0) {
                Rect rect = new Rect();
                getGlobalVisibleRect(rect);
                int offsets = getResources().getDisplayMetrics().heightPixels - rect.bottom;
                if (mKeyboardHeight > offsets) {
                    getRootView().setTranslationY(offsets - mKeyboardHeight - (int)(2 * getResources().getDisplayMetrics().density));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void resetContentHeight() {
        try {
            getRootView().setTranslationY(originTransX);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface KeyboardDialogProvider {
        Dialog initDialog(CRNKeyboardEditText keyboardEditText, View rootView, String keyboardType, List<String> ctripKeyboardTextList, CRNKeyboardDialog.OnVisiableListener onVisiableListener);
    }

    private static KeyboardDialogProvider keyboardDialogProvider;

    public static void setKeyboardDialogProvider(KeyboardDialogProvider keyboardDialogProvider) {
        CRNKeyboardEditText.keyboardDialogProvider = keyboardDialogProvider;
    }

    public Dialog initDialog() {
        CRNKeyboardDialog.OnVisiableListener monVisiableListener = new CRNKeyboardDialog.OnVisiableListener() {
            @Override
            public void onShow() {
            }
            @Override
            public void onDismiss() {
                resetContentHeight();
            }
        };
        if (keyboardDialogProvider != null && !SOFT_KEYBOARD_IDENTITY.equalsIgnoreCase(ctripKeyboardType)) {
            return keyboardDialogProvider.initDialog(CRNKeyboardEditText.this, mRootView, ctripKeyboardType, ctripKeyboardTextList, monVisiableListener);
        } else {
            CRNKeyboardDialog dialog = new CRNKeyboardDialog(getContext()
                    , R.style.CRNKeyboardDialog
                    , R.layout.identity_keyboard_layout,
                    CRNKeyboardEditText.this
                    , mRootView
                    , mKeyboardRes);
            dialog.setOnVisiableListener(monVisiableListener);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);
            return dialog;
        }
    }

    private void initKeyboardHeight() {
        if (mDialog == null || mKeyboardHeight > 0) return;
        final View rootView = mDialog.findViewById(R.id.common_keyboard_rootview);
        if (rootView == null) return;
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mKeyboardHeight = rootView.getMeasuredHeight();
                modifyContentHeight();
                rootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    //CtripKeyboardType.NUMBER_ABC == ctripKeyboardType

    /**
     * ctripKeyboardType is number&abc keyboard, setCtripNumAbcKeyboardTextList is available
     *
     * @param textList
     */
    public void setCtripNumAbcKeyboardTextList(List<String> textList){
        if(textList != null && textList.size()>0){
            ctripKeyboardTextList = textList;
        }
    }

    public void setCtripKeyboard(boolean useCtripKeyBoard, String type, View view) {
        if (!useCtripKeyBoard) {
            hideCtripKeyboard();
            this.mUseIdentityKeyBoard = useCtripKeyBoard;
            mKeyboardRes = 0;
            mRootView = null;
            enableSystemKeyboard(true);
            return;
        }
        ctripKeyboardType = type;
        this.mUseIdentityKeyBoard = useCtripKeyBoard;
        enableSystemKeyboard(false);
        switch (type) {
            case SOFT_KEYBOARD_IDENTITY:
                mKeyboardRes = R.xml.identity_symbols;
                break;
            default:
                mKeyboardRes = R.xml.identity_symbols;
                break;
        }
        mRootView = view;
    }

    public void hideCtripKeyboard() {
        resetContentHeight();
        if (mUseIdentityKeyBoard && mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    public void enableSystemKeyboard(boolean enable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setShowSoftInputOnFocus(enable);
        } else {
            try {
                Method method = this
                        .getClass()
                        .getSuperclass()
                        .getSuperclass()
                        .getDeclaredMethod("setShowSoftInputOnFocus", boolean.class);
                method.setAccessible(true);
                method.invoke(this, enable);
            } catch (Exception ex) {
                FLog.d(ReactConstants.TAG, getClass().getCanonicalName() + " setShowSoftInputOnFocus error: " + ex.getMessage());
            }
        }
    }

    public void onShowNotify(){
    }

    public void onDismissNotify(){
    }
}
