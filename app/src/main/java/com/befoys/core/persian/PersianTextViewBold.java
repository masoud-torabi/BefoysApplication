package com.befoys.core.persian;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.befoys.core.configs.Constants;

public class PersianTextViewBold extends TextView {

    public PersianTextViewBold(Context context) {
        super(context);
        setFont(context);
    }

    public PersianTextViewBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont(context);
    }

    public PersianTextViewBold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont(context);
    }

    public PersianTextViewBold(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
        setTextAppearance(defStyleRes);
        setFont(context);
    }

    private void setFont(Context context) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), Constants.APPLICATION_FONT_NAME_BOLD);
        setTypeface(typeface);
    }
}
