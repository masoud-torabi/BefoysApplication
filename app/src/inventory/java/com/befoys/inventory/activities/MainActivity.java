package com.befoys.inventory.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.befoys.R;
import com.befoys.core.dagger.DaggerEntityComponent;
import com.befoys.core.dagger.EntityComponent;
import com.befoys.core.models.SiteUser;
import com.befoys.inventory.helpers.DrawerNavigationHelper;

public class MainActivity extends AppCompatActivity {
    EntityComponent _component;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _component = DaggerEntityComponent.builder().build();
        context = getApplicationContext();

        SiteUser currentUser = _component.getSiteUserModule().getCurrentSiteUser();
        if (currentUser == null) {
            Intent intent = new Intent(context, LoginActivity.class);
            startActivity(intent);
        }

        DrawerNavigationHelper.build(this);
    }
}