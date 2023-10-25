package com.befoys.core.persian;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

import com.befoys.core.configs.Constants;

public class PersianEditText extends EditText {
    public PersianEditText(Context context) {
        super(context);
        setFont(context);
    }

    public PersianEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont(context);
    }

    public PersianEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont(context);
    }

    private void setFont(Context context) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), Constants.APPLICATION_FONT_NAME);
        setTypeface(typeface);
    }
}

