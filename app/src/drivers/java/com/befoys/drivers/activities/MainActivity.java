package com.befoys.drivers.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;

import com.befoys.R;
import com.befoys.core.dagger.DaggerEntityComponent;
import com.befoys.core.dagger.EntityComponent;
import com.befoys.drivers.fragments.ProfileFragment;
import com.befoys.drivers.fragments.TravelsFragment;
import com.befoys.drivers.helpers.BottomNavigationHelper;
import com.befoys.drivers.helpers.DrawerNavigationHelper;

public class MainActivity extends AppCompatActivity {
    EntityComponent _component;
    Context context;
    private Integer selectedTabId = R.id.menu_item_home, selectedDrawerMenuId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _component = DaggerEntityComponent.builder().build();
        context = getApplicationContext();

        BottomNavigationHelper.build(this);

        if (getIntent().getExtras() != null)
        {
            selectedTabId = getIntent().getExtras().getInt("selectedTabId");

            if (selectedTabId != null && selectedTabId != R.id.menu_item_home)
            {
                Fragment fragment = null;
                if (selectedTabId == R.id.menu_item_travel)
                {
                    fragment = new TravelsFragment();
                }
                else if (selectedTabId == R.id.menu_item_profile) {
                    fragment = new ProfileFragment();
                }
                BottomNavigationHelper.gotoNextFragment(this, fragment, selectedTabId);
            }
        }
        DrawerNavigationHelper.build(this, true);
    }
}