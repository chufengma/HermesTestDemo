package ctrip.crn.keyboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.Keyboard.Key;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;

import com.facebook.react.R;

/**
 * 自定义KeyboardView,重绘删除键
 */
public class CRNKeyboardView extends KeyboardView {

    private Key mDeleteKey;
    private Drawable mBackgroundDrawable;
    private Rect mDrawablePadding;

    /**
     * CRNKeyboardView
     *
     * @param context context
     */
    public CRNKeyboardView(Context context) {
        this(context, null);
    }

    /**
     * CRNKeyboardView
     *
     * @param context context
     * @param attrs   attrs
     */
    public CRNKeyboardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * CRNKeyboardView
     *
     * @param context  context
     * @param attrs    attrs
     * @param defStyle defStyle
     */
    public CRNKeyboardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        redrawDeleteKey(canvas);
    }

    private void redrawDeleteKey(Canvas canvas) {
        if (mDeleteKey == null) {
            return;
        }
        if (mBackgroundDrawable == null) {
            mBackgroundDrawable = getResources().getDrawable(
                    R.drawable.keyboard_delete_bg);
        }
        int[] drawableState = mDeleteKey.getCurrentDrawableState();
        mBackgroundDrawable.setState(drawableState);

        final Rect bounds = mBackgroundDrawable.getBounds();
        if (mDeleteKey.width != bounds.right
                || mDeleteKey.height != bounds.bottom) {
            mBackgroundDrawable.setBounds(0, 0, mDeleteKey.width,
                    mDeleteKey.height);
        }
        canvas.translate(mDeleteKey.x + getPaddingLeft(), mDeleteKey.y
                + getPaddingTop());
        mBackgroundDrawable.draw(canvas);

        if (mDrawablePadding == null) {
            mDrawablePadding = new Rect(0, 0, 0, 0);
            mBackgroundDrawable.getPadding(mDrawablePadding);
        }

        final int drawableX = (mDeleteKey.width - mDrawablePadding.left
                - mDrawablePadding.right - mDeleteKey.icon.getIntrinsicWidth())
                / 2 + mDrawablePadding.left;
        final int drawableY = (mDeleteKey.height - mDrawablePadding.top
                - mDrawablePadding.bottom - mDeleteKey.icon
                .getIntrinsicHeight()) / 2 + mDrawablePadding.top;
        canvas.translate(drawableX, drawableY);
        mDeleteKey.icon.setBounds(0, 0, mDeleteKey.icon.getIntrinsicWidth(),
                mDeleteKey.icon.getIntrinsicHeight());
        mDeleteKey.icon.draw(canvas);
        canvas.translate(-drawableX, -drawableY);
        canvas.translate(-mDeleteKey.x - getPaddingLeft(), -mDeleteKey.y
                - getPaddingTop());
    }

    public void initDeleteKey() {
        Keyboard keyboard = getKeyboard();
        for (Key key : keyboard.getKeys()) {
            if (key.codes[0] == Keyboard.KEYCODE_DELETE) {
                mDeleteKey = key;
                break;
            }
        }
    }

}
