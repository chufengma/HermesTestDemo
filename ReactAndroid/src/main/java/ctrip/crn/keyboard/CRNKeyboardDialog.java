package ctrip.crn.keyboard;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.facebook.react.R;

/**
 * 实现软键盘对话框
 */
public class CRNKeyboardDialog extends Dialog implements DialogInterface.OnDismissListener
        , DialogInterface.OnShowListener, KeyboardView.OnKeyboardActionListener {

    private View mBaseView;
    private int mInitialScrollY;
    private EditText mEditText;

    private OnVisiableListener onVisiableListener;

    public interface OnVisiableListener {
        void onShow();
        void onDismiss();
    }

    public void setOnVisiableListener(OnVisiableListener onVisiableListener) {
        this.onVisiableListener = onVisiableListener;
    }

    /**
     * CRNKeyboardDialog
     *
     * @param context     context
     * @param theme       theme
     * @param resourceId  resourceId
     * @param editText    editText
     * @param baseView    baseView
     * @param keyboardRes keyboardRes
     */
    public CRNKeyboardDialog(Context context
            , int theme
            , int resourceId
            , EditText editText
            , View baseView
            , int keyboardRes) {
        super(context, theme);

        mBaseView = baseView;
        mEditText = editText;
        if (mBaseView != null) {
            mInitialScrollY = mBaseView.getScrollY();
        }

        View view = LayoutInflater.from(context).inflate(resourceId, null);
        setContentView(view);
        initKeyboardView(context, view, keyboardRes);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.setTitle("CRNSoftKeyboard");
        lp.gravity = Gravity.BOTTOM;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        setOnDismissListener(this);
        setOnShowListener(this);
        view.findViewById(R.id.input_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void initKeyboardView(Context context, View rootView, int keyboardRes) {
        if (rootView != null) {
            CRNKeyboardView mKeyboardView = (CRNKeyboardView) rootView.findViewById(R.id.keyboard_view);
            Keyboard keyboard = new Keyboard(context, keyboardRes);
            mKeyboardView.setKeyboard(keyboard);
            mKeyboardView.setEnabled(true);
            mKeyboardView.setOnKeyboardActionListener(this);
            mKeyboardView.initDeleteKey();
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (mBaseView != null) {
            mBaseView.scrollTo(0, mInitialScrollY);
        }
    }

    @Override
    public void onShow(DialogInterface dialog) {
        if (mBaseView != null) {
            int[] temp = new int[2];
            mEditText.getLocationOnScreen(temp);
            DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
            int screenHeight = dm.heightPixels;
            int dialogHeight = getWindow().getDecorView().getHeight();
            int delta = screenHeight - dialogHeight
                    - (temp[1] + mEditText.getHeight());
            if (delta < 0) {
                mBaseView.scrollBy(0, -delta);
            }
        }
        if (onVisiableListener != null) {
            onVisiableListener.onShow();
        }
    }

    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        Editable editable = mEditText.getText();
        int start = mEditText.getSelectionStart();
        if (primaryCode == Keyboard.KEYCODE_DELETE) {
            if (editable != null && editable.length() > 0) {
                if (start > 0) {
                    editable.delete(start - 1, start);
                }
            }
        } else {
            editable.insert(start, Character.toString((char) primaryCode)
                    .toUpperCase());
        }
    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }

    @Override
    public void dismiss() {
        if (onVisiableListener != null) {
            onVisiableListener.onDismiss();
        }
        super.dismiss();
    }
}
