package com.befoys.core.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.befoys.R;

public class CancelDialog extends Dialog implements android.view.View.OnClickListener{
    public CancelDialog(@NonNull Context context) {
        super(context);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(getContext(), R.layout.dialog_cancel_order, null);

        setContentView(view);
    }

    @Override
    public void onClick(View view) {

    }
}
