package com.befoys.core.persian;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.SearchView;
import android.widget.TextView;

import com.befoys.core.configs.Constants;

public class PersianSearchView extends SearchView {
    public PersianSearchView(Context context) {
        super(context);
        setFont(context);
    }

    public PersianSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont(context);
    }

    public PersianSearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont(context);
    }

    private void setFont(Context context) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), Constants.APPLICATION_FONT_NAME);

        int id = this.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        //TextView searchText = (TextView) this.findViewById(androidx.annotation.R.id.search_src_text);
        //searchText.setTypeface(typeface);

    }
}
