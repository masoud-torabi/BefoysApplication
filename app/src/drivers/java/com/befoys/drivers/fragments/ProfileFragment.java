package com.befoys.drivers.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.befoys.R;
import com.befoys.core.dagger.DaggerEntityComponent;
import com.befoys.core.dagger.EntityComponent;
import com.befoys.core.models.Driver;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment extends Fragment {
    EntityComponent _component;
    Context context;
    Driver driver;

    RelativeLayout loadingPart;
    SwipeRefreshLayout pullToRefresh;
    TextView lblDriverName, lblDriverVehicleName, lblDriverVehiclePlaque, lblDriverMobileNumber;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        bindPageElements(view);
        context = this.getContext();

        _component = DaggerEntityComponent.builder().build();
        driver = _component.getDriverModule().getCurrentDriver();

        pullToRefresh.setVisibility(View.GONE);
        loadingPart.setVisibility(View.VISIBLE);

        bindPageData();

        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                bindPageData();
                pullToRefresh.setRefreshing(false);
            }
        });

        return view;
    }

    private void bindPageElements(View view) {
        loadingPart = view.findViewById(R.id.loadingPart);
        pullToRefresh = view.findViewById(R.id.pullToRefresh);
        lblDriverName = view.findViewById(R.id.lblDriverName);
        lblDriverVehicleName = view.findViewById(R.id.lblDriverVehicleName);
        lblDriverVehiclePlaque = view.findViewById(R.id.lblDriverVehiclePlaque);
        lblDriverMobileNumber = view.findViewById(R.id.lblDriverMobileNumber);
    }

    private void bindPageData() {
        lblDriverName.setText(getString(R.string.label_driver_name) + driver.getName());
        lblDriverMobileNumber.setText(getString(R.string.label_mobile_number) + driver.getMobile());
        lblDriverVehicleName.setText(getString(R.string.label_vehicle_name) + driver.getVehicleName());
        lblDriverVehiclePlaque.setText(getString(R.string.label_vehicle_plaque) + driver.getVehiclePlaque());

        closeLoadingPart();
    }

    private void closeLoadingPart() {
        loadingPart.setVisibility(View.GONE);
        pullToRefresh.setVisibility(View.VISIBLE);
    }
}