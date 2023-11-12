package com.befoys.inventory.helpers;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.befoys.R;
import com.befoys.core.dagger.DaggerEntityComponent;
import com.befoys.core.dagger.EntityComponent;
import com.befoys.core.persian.PersianMenu;
import com.befoys.inventory.activities.MainActivity;
import com.befoys.inventory.activities.WarehouseDocActivity;
import com.google.android.material.navigation.NavigationView;

public class DrawerNavigationHelper {
    private static ImageView imgToggleBasket;
    private static TextView txtToolbarBasketCount;
    private static EntityComponent _component;

    public static void build(final FragmentActivity context) {
        final NavigationView navigationView = context.findViewById(R.id.navigation_menu);

        PersianMenu.Build(navigationView.getMenu(), context.getAssets());
        _component = DaggerEntityComponent.builder().build();

        initializeNavigationElements(context, navigationView);
        initializeNavigationViewClickEvents(context, navigationView);
    }

    private static void initializeNavigationElements(final Activity context, NavigationView navigationView) {
        final DrawerLayout drawer = context.findViewById(R.id.drawer);
        final ImageView imgToggleDrawer = context.findViewById(R.id.imgToggleDrawer);
        final ImageView imgToolbarBack = context.findViewById(R.id.imgToolbarBack);
        final LinearLayout mainToolbar = context.findViewById(R.id.mainToolbar);

        if (imgToggleDrawer != null) {
            imgToggleDrawer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawer.openDrawer(GravityCompat.END);
                }
            });
        }

        if (imgToolbarBack != null) {
            imgToolbarBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.finish();
                }
            });
        }
    }

    private static void initializeNavigationViewClickEvents(final FragmentActivity context, final NavigationView navigationView) {
        final DrawerLayout drawer = context.findViewById(R.id.drawer);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                gotoNextActivity(context, item.getItemId());
                return false;
            }
        });
    }

    private static void gotoNextActivity(FragmentActivity context, Integer menuId)
    {
        Intent intent = null;
        if (menuId == R.id.menu_home) {
            intent = new Intent(context, MainActivity.class);
            intent.putExtra("selectedTabId", R.id.menu_home);
        } else if (menuId == R.id.menu_barcode) {
            //intent = new Intent(context, Barcode.class);
            intent.putExtra("selectedTabId", R.id.menu_barcode);
        } else if (menuId == R.id.menu_handling) {
            intent.putExtra("selectedTabId", R.id.menu_handling);
        } else if (menuId == R.id.menu_doc) {
            intent = new Intent(context, WarehouseDocActivity.class);
            intent.putExtra("selectedTabId", R.id.menu_doc);
        }else if (menuId == R.id.menu_quantity) {
            //intent = new Intent(context, FavoriteActivity.class);
        } else if (menuId == R.id.menu_exit) {
            System.exit(0);
        }
        context.startActivity(intent);
    }
}