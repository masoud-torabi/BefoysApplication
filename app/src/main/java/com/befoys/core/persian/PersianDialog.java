package com.befoys.core.persian;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TypefaceSpan;

import com.befoys.R;
import com.befoys.core.configs.Constants;

public class PersianDialog {
    public static void showOkMessage(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), Constants.APPLICATION_FONT_NAME);
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        dialog.dismiss();
                        break;
                }
            }
        };
        builder.setMessage(getTypeFace(typeface, message))
                .setPositiveButton(getTypeFace(typeface, context.getResources().getString(R.string.alert_ok)), dialogClickListener)
                .show();
    }

    public static void showOkMessageWithClick(Context context, String message, DialogInterface.OnClickListener clickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), Constants.APPLICATION_FONT_NAME);
        DialogInterface.OnClickListener dialogClickListener = clickListener;
        builder.setMessage(getTypeFace(typeface, message))
                .setPositiveButton(getTypeFace(typeface, context.getResources().getString(R.string.alert_ok)), dialogClickListener)
                .show();
    }

    public static void showButtonMessage(Context context, String message, String positiveButton, String negativeButton, final DialogInterface.OnClickListener clickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), Constants.APPLICATION_FONT_NAME);
        DialogInterface.OnClickListener dialogClickListener = clickListener;
        builder.setMessage(getTypeFace(typeface, message))
                .setPositiveButton(getTypeFace(typeface, positiveButton), dialogClickListener)
                .setNegativeButton(getTypeFace(typeface, negativeButton), dialogClickListener)
                .show();
    }

    private static SpannableString getTypeFace(Typeface typeface, CharSequence chars) {
        if (chars == null) {
            return null;
        }
        SpannableString s = new SpannableString(chars);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            s.setSpan(new TypefaceSpan(typeface), 0, s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return s;
    }
}
