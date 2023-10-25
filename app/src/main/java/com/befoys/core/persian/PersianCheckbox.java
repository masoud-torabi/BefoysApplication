package com.befoys.core.persian;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.CheckBox;

import com.befoys.core.configs.Constants;

public class PersianCheckbox extends CheckBox {

    public PersianCheckbox(Context context) {
        super(context);
        setFont(context);
    }

    public PersianCheckbox(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont(context);
    }

    public PersianCheckbox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont(context);
    }

    private void setFont(Context context) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), Constants.APPLICATION_FONT_NAME);
        setTypeface(typeface);
    }
}
