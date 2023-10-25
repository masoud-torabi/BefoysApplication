package com.befoys.core.persian;

import android.content.res.AssetManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;

public class PersianMenu {
    public static void Build(Menu menu, AssetManager assets)
    {
        for (int i = 0; i < menu.size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            SpannableString mNewTitle = new SpannableString(menuItem.getTitle());
            mNewTitle.setSpan(new PersianMenuItem(assets), 0 , mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            menuItem.setTitle(mNewTitle);

            if (menuItem.getSubMenu() != null && menuItem.getSubMenu().size() > 0)
            {
                for (int j = 0; j < menuItem.getSubMenu().size(); j++) {
                    MenuItem subMenuItem = menuItem.getSubMenu().getItem(j);
                    SpannableString subNewTitle = new SpannableString(subMenuItem.getTitle());
                    subNewTitle.setSpan(new PersianMenuItem(assets), 0 , subNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                    subMenuItem.setTitle(subNewTitle);
                }
            }
        }
    }
}
