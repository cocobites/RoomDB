package com.cocobites.roomdb.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;

import androidx.annotation.Nullable;

import com.cocobites.roomdb.R;
import com.google.android.material.textfield.TextInputLayout;

/**
 * @author Rishad
 * @since 06/11/2019
 */

public class TextInputEditText extends com.google.android.material.textfield.TextInputEditText {

    String mLabel;
    String mHint;

    public TextInputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.TextInputEditText, 0, 0);
        mLabel = a.getString(R.styleable.TextInputEditText_label);
        mHint = getHint() != null ? getHint().toString() : "";

        if (mLabel == null)
            mLabel = "";

        a.recycle();

    }

    @Nullable
    private TextInputLayout getTextInputLayout() {
        for (ViewParent parent = this.getParent(); parent instanceof View; parent = parent.getParent()) {
            if (parent instanceof TextInputLayout) {
                return (TextInputLayout) parent;
            }
        }

        return null;
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        TextInputLayout textInputLayout = getTextInputLayout();
        if (textInputLayout != null)
            textInputLayout.setHint(lengthAfter == 0 ? mHint : mLabel);

    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        TextInputLayout textInputLayout = getTextInputLayout();
        String text = getText() != null ? getText().toString() : "";
        if (textInputLayout != null)
            textInputLayout.setHint(focused || !TextUtils.isEmpty(text) ? mLabel : mHint);
    }
}
