package com.befoys.drivers.helpers;

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
import com.befoys.drivers.activities.FavoriteActivity;
import com.befoys.drivers.activities.MainActivity;
import com.befoys.drivers.fragments.HomeFragment;
import com.befoys.drivers.fragments.TravelsFragment;
import com.befoys.drivers.fragments.ProfileFragment;
import com.google.android.material.navigation.NavigationView;

public class DrawerNavigationHelper {
    private static boolean _isMainActivity;
    private static ImageView imgToggleBasket;
    private static TextView txtToolbarBasketCount;
    private static EntityComponent _component;

    public static void build(final FragmentActivity context, boolean isMainActivity) {
        final NavigationView navigationView = context.findViewById(R.id.navigation_menu);
        _isMainActivity = isMainActivity;

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
                if (_isMainActivity) {
                    gotoNextFragment(context, drawer, item.getItemId());
                } else {
                    gotoNextActivity(context, item.getItemId());
                }
                return false;
            }
        });
    }

    private static void gotoNextActivity(FragmentActivity context, Integer menuId)
    {
        Intent intent = new Intent(context, MainActivity.class);
        if (menuId == R.id.menu_home) {
            intent.putExtra("selectedTabId", R.id.menu_item_home);
        } else if (menuId == R.id.menu_profile) {
            intent.putExtra("selectedTabId", R.id.menu_item_profile);
        } else if (menuId == R.id.menu_drawer_travel) {
            intent.putExtra("selectedTabId", R.id.menu_item_travel);
        } else if (menuId == R.id.menu_support) {
            intent = new Intent(Intent.ACTION_CALL);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("tel:02188194363"));
        } else if (menuId == R.id.menu_address) {
            intent = new Intent(context, FavoriteActivity.class);
        } else if (menuId == R.id.menu_exit) {
            System.exit(0);
        }
        context.startActivity(intent);
    }

    private static void gotoNextFragment(FragmentActivity context, DrawerLayout drawer, Integer menuId) {
        Fragment fragment = null;
        Class activity = null;
        Bundle args = new Bundle();
        Integer bottomNavigationId = 0;
        if (menuId == R.id.menu_home) {
            fragment = new HomeFragment();
            bottomNavigationId = R.id.menu_item_home;
        } else if (menuId == R.id.menu_profile) {
            fragment = new ProfileFragment();
            bottomNavigationId = R.id.menu_item_profile;
        } else if (menuId == R.id.menu_drawer_travel) {
            fragment = new TravelsFragment();
            bottomNavigationId = R.id.menu_item_travel;
        } else if (menuId == R.id.menu_support) {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("tel:02188194363"));
            context.startActivity(intent);
        } else if (menuId == R.id.menu_address) {
            activity = FavoriteActivity.class;
        } else if (menuId == R.id.menu_exit) {
            System.exit(0);
        }

        drawer.closeDrawer(GravityCompat.END);
        if (fragment != null)
        {
            fragment.setArguments(args);
            BottomNavigationHelper.gotoNextFragment(context, fragment, bottomNavigationId);
        }
        else if (activity != null)
        {
            Intent intent = new Intent(context, activity);
            intent.putExtra("selectedTabId", bottomNavigationId);
            context.startActivity(intent);
        }
    }
}