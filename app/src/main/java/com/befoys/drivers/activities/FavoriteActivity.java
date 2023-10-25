package com.befoys.drivers.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.befoys.R;
import com.befoys.core.dagger.DaggerEntityComponent;
import com.befoys.core.dagger.EntityComponent;
import com.befoys.core.enums.Enum_ApiResultStatus;
import com.befoys.core.models.DriverFavorite;
import com.befoys.core.modules.BaseModule;
import com.befoys.core.utils.ApplicationPermission;
import com.befoys.core.webservice.base.ApiResult;
import com.befoys.core.webservice.base.ApiResultListener;
import com.befoys.drivers.adapters.DriverFavoriteAdapter;
import com.befoys.drivers.helpers.DrawerNavigationHelper;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity {
    EntityComponent _component;
    Context context;
    List<DriverFavorite> listFavorites = new ArrayList();

    RecyclerView recycleFavorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        _component = DaggerEntityComponent.builder().build();
        context = getApplicationContext();

        bindPageElements();

        DrawerNavigationHelper.build(this, false);

        _component.getDriverFavoriteModule().getDriverFavorites(new ApiResultListener() {
            @Override
            public void onSuccess(ApiResult result) {

                listFavorites.addAll(BaseModule.castObjectList((ArrayList)result.getValue(), DriverFavorite.class));
                DriverFavoriteAdapter adapter = new DriverFavoriteAdapter(context, _component, listFavorites);

                LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                recycleFavorites.setLayoutManager(layoutManager);
                recycleFavorites.setNestedScrollingEnabled(false);
                recycleFavorites.setHasFixedSize(false);
                recycleFavorites.setAdapter(adapter);

                adapter.setOnCallButtonListener(new DriverFavoriteAdapter.OnCallListener() {
                    @Override
                    public void onItemClick(View itemView, int position) {
                        boolean hasPermission = ApplicationPermission.isTelephonePermissionGranted(FavoriteActivity.this);

                        if (hasPermission == true)
                        {
                            DriverFavorite entity = listFavorites.get(position);
                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                            callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            callIntent.setData(Uri.parse("tel:" + entity.getPhone()));
                            context.startActivity(callIntent);
                        }
                    }
                });
            }

            @Override
            public void onFailure(Enum_ApiResultStatus status, String message) {

            }
        });
    }

    private void bindPageElements() {
        recycleFavorites = findViewById(R.id.recycleFavorites);
    }
}