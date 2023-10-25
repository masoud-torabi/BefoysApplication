package com.befoys.drivers.helpers;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.befoys.R;
import com.befoys.core.persian.PersianMenu;
import com.befoys.drivers.fragments.HomeFragment;
import com.befoys.drivers.fragments.ProfileFragment;
import com.befoys.drivers.fragments.TravelsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationHelper {
    //private static Integer selectedFragmentIndex;
    public static void build(final FragmentActivity activity)
    {
        //selectedFragmentIndex = selectedItem;
        final FragmentManager fragmentManager = activity.getSupportFragmentManager();
        final BottomNavigationView bottomNavigation = activity.findViewById(R.id.bottomNavigation);
        PersianMenu.Build(bottomNavigation.getMenu(), activity.getAssets());
        fragmentManager.beginTransaction().replace(R.id.home_fragment_container, new HomeFragment()).commit();
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = getFragmentItem(activity, item.getItemId());
                Integer num = getFragmentNumber(activity, item.getItemId());
                fragmentManager.beginTransaction().replace(R.id.home_fragment_container, fragment).commit();
                bottomNavigation.getMenu().getItem(num).setChecked(true);
                return false;
            }
        });

        //bottomNavigation.setSelectedItemId(selectedFragmentIndex);
    }

    public static void gotoNextFragment(final FragmentActivity activity, Fragment fragment, Integer itemId) {
        final FragmentManager fragmentManager = activity.getSupportFragmentManager();
        final BottomNavigationView bottomNavigation = activity.findViewById(R.id.bottomNavigation);
        Integer num = getFragmentNumber(activity, itemId);
        fragmentManager.beginTransaction().replace(R.id.home_fragment_container, fragment).commit();
        bottomNavigation.getMenu().getItem(num).setChecked(true);
    }

    private static Fragment getFragmentItem(FragmentActivity activity, int Id)
    {
        Fragment fragment = null;
        Bundle args = new Bundle();
        int num = 0;
        if (Id == R.id.menu_item_home) {
            fragment = new HomeFragment();
            num = 0;
        } else if (Id == R.id.menu_item_travel) {
            fragment = new TravelsFragment();
            num = 1;
        } else if (Id == R.id.menu_item_profile) {
            fragment = new ProfileFragment();
            num = 2;
        }
        fragment.setArguments(args);
        return fragment;
    }

    private static Integer getFragmentNumber(FragmentActivity activity, int Id)
    {
        int num = 0;
        if (Id == R.id.menu_item_home) {
            num = 0;
        } else if (Id == R.id.menu_item_travel) {
            num = 1;
        } else if (Id == R.id.menu_item_profile) {
            num = 2;
        }
        return num;
    }
}
