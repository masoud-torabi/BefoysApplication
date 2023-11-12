package com.befoys.drivers.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.befoys.R;
import com.befoys.core.dagger.DaggerEntityComponent;
import com.befoys.core.dagger.EntityComponent;
import com.befoys.core.enums.Enum_ApiResultStatus;
import com.befoys.core.models.DriverFavorite;
import com.befoys.core.models.Travel;
import com.befoys.core.models.TravelStep;
import com.befoys.core.modules.BaseModule;
import com.befoys.core.persian.PersianDialog;
import com.befoys.core.utils.ApplicationPermission;
import com.befoys.core.webservice.base.ApiResult;
import com.befoys.core.webservice.base.ApiResultListener;
import com.befoys.drivers.adapters.TravelStepAdapter;
import com.befoys.drivers.helpers.DrawerNavigationHelper;

import java.util.ArrayList;
import java.util.List;

public class TravelActivity extends AppCompatActivity {
    EntityComponent _component;
    Context context;
    private Integer travelId = null;
    private Travel travel = null;
    private List<TravelStep> listStep = new ArrayList();

    RecyclerView recycleTravelItems;
    TextView lblTravelId, lblTravelName, lblTravelStatus, lblTravelDatetime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        _component = DaggerEntityComponent.builder().build();
        context = getApplicationContext();

        bindPageElements();

        DrawerNavigationHelper.build(this, false);

        if (getIntent().getExtras() != null)
        {
            travelId = getIntent().getExtras().getInt("travelId");
            _component.getTravelModule().getById(travelId, new ApiResultListener() {
                @Override
                public void onSuccess(ApiResult result) {
                    travel = BaseModule.castObject(result.getValue(), Travel.class);

                    lblTravelId.setText(getString(R.string.label_travel_id) + travel.getId());
                    lblTravelName.setText(getString(R.string.label_travel_name) + travel.getName());
                    lblTravelStatus.setText(getString(R.string.label_travel_status) + travel.getStatus().getName());
                    lblTravelDatetime.setText(getString(R.string.label_travel_datetime) + travel.getStartDatetime());

                    for (int i = 0; i < travel.getTravelSteps().size(); i++) {
                        TravelStep step = travel.getTravelSteps().get(i);
                        listStep.add(step);
                    }

                    TravelStepAdapter adapter = new TravelStepAdapter(context, listStep);

                    LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                    recycleTravelItems.setLayoutManager(layoutManager);
                    recycleTravelItems.setNestedScrollingEnabled(false);
                    recycleTravelItems.setHasFixedSize(false);
                    recycleTravelItems.setAdapter(adapter);

                    adapter.setOnCallButtonListener(new TravelStepAdapter.OnCallButtonListener() {
                        @Override
                        public void onItemClick(View itemView, int position) {
                            if (ApplicationPermission.isTelephonePermissionGranted(TravelActivity.this))
                            {
                                TravelStep entity = listStep.get(position);
                                Intent callIntent = new Intent(Intent.ACTION_CALL);
                                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                callIntent.setData(Uri.parse("tel:" + entity.getPhone()));
                                context.startActivity(callIntent);
                            }
                        }
                    });

                    adapter.setOnDirectionClickListener(new TravelStepAdapter.OnDirectionButtonListener() {
                        @Override
                        public void onItemClick(View itemView, int position) {
                            TravelStep entity = listStep.get(position);
                            TravelStep previousStep = position > 0 ? listStep.get(position - 1) : null;
                            String directionLink = "https://www.google.com/maps/dir/";
                            if (previousStep != null)
                            {
                                directionLink += previousStep.getLatitude().toString() + ",";
                                directionLink += previousStep.getLongitude().toString() + "/";
                            }
                            else
                            {
                                directionLink += "/";
                            }
                            directionLink += entity.getLatitude().toString() + ",";
                            directionLink += entity.getLongitude().toString() + "/";

                            Intent browser = new Intent(Intent.ACTION_VIEW);
                            browser.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            browser.setData(Uri.parse(directionLink));
                            context.startActivity(browser);
                        }
                    });

                    adapter.setOnStartClickListener(new TravelStepAdapter.OnStartButtonListener() {
                        @Override
                        public void onItemClick(View itemView, int position) {
                            TravelStep entity = listStep.get(position);

                            Intent intent = new Intent(context, OrderActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("orderId", entity.getResellerOrderId());
                            intent.putExtra("stepId", entity.getId());
                            intent.putExtra("travelType", entity.getType().getLabel());
                            intent.putExtra("name", entity.getName());
                            intent.putExtra("travelId", travelId);
                            startActivity(intent);
                        }
                    });

                    adapter.setOnEndClickListener(new TravelStepAdapter.OnEndButtonListener() {
                        @Override
                        public void onItemClick(View itemView, int position) {
                            TravelStep entity = listStep.get(position);

                            Intent intent = new Intent(context, OrderActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("orderId", entity.getResellerOrderId());
                            intent.putExtra("stepId", entity.getId());
                            intent.putExtra("travelType", entity.getType().getLabel());
                            intent.putExtra("name", entity.getName());
                            intent.putExtra("travelId", travelId);
                            startActivity(intent);
                        }
                    });

                    adapter.setOnFavoriteButtonListener(new TravelStepAdapter.OnFavoriteButtonListener() {
                        @Override
                        public void onItemClick(View itemView, int position) {
                            TravelStep entity = listStep.get(position);
                            DriverFavorite favoriteEntity = new DriverFavorite();
                            favoriteEntity.setBuyerId(entity.getBuyerId());
                            favoriteEntity.setShopResellerId(entity.getShopResellerId());
                            _component.getDriverFavoriteModule().toggleDriverFavorite(favoriteEntity, null);
                        }
                    });

                    adapter.setOnPointButtonListener(new TravelStepAdapter.OnPointButtonListener() {
                        @Override
                        public void onItemClick(View itemView, int position) {
                            TravelStep entity = listStep.get(position);
                            String directionLink = "https://www.google.com/maps/dir//";
                            directionLink += entity.getLatitude().toString() + ",";
                            directionLink += entity.getLongitude().toString() + "/";

                            Intent browser = new Intent(Intent.ACTION_VIEW);
                            browser.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            browser.setData(Uri.parse(directionLink));
                            context.startActivity(browser);
                        }
                    });
                }

                @Override
                public void onFailure(Enum_ApiResultStatus status, String message) {
                    PersianDialog.showOkMessage(TravelActivity.this, message);
                }
            });
        }
    }

    private void bindPageElements() {
        recycleTravelItems = findViewById(R.id.recycleTravelItems);
        lblTravelId = findViewById(R.id.lblTravelId);
        lblTravelName = findViewById(R.id.lblTravelName);
        lblTravelStatus = findViewById(R.id.lblTravelStatus);
        lblTravelDatetime = findViewById(R.id.lblTravelDatetime);
    }
}

