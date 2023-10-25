package com.befoys.drivers.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.befoys.R;
import com.befoys.core.dagger.DaggerEntityComponent;
import com.befoys.core.dagger.EntityComponent;
import com.befoys.core.enums.Enum_ApiResultStatus;
import com.befoys.core.models.Driver;
import com.befoys.core.models.Travel;
import com.befoys.core.modules.BaseModule;
import com.befoys.core.webservice.base.ApiParameter;
import com.befoys.core.webservice.base.ApiResult;
import com.befoys.core.webservice.base.ApiResultListener;
import com.befoys.drivers.adapters.TravelAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TravelsFragment extends Fragment {
    EntityComponent _component;
    Context context;
    Integer loadCount = 0;
    List<Travel> travelList = new ArrayList<>();
    Driver driver;

    RelativeLayout loadingPart;

    SwipeRefreshLayout pullToRefresh;

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_travels, container, false);
        ButterKnife.bind(this, view);
        context = this.getContext();

        bindPageElements(view);

        _component = DaggerEntityComponent.builder().build();
        driver = _component.getDriverModule().getCurrentDriver();

        pullToRefresh.setVisibility(View.GONE);
        loadingPart.setVisibility(View.VISIBLE);

        bindPageData();

        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                bindPageData();
                loadCount = 0;
                pullToRefresh.setRefreshing(false);
            }
        });

        return view;
    }

    private void bindPageElements(View view) {
        loadingPart = view.findViewById(R.id.loadingPart);
        pullToRefresh = view.findViewById(R.id.pullToRefresh);
        recyclerView = view.findViewById(R.id.recyclerView);
    }

    private void bindPageData() {
        ArrayList<ApiParameter> params = new ArrayList();
        params.add(new ApiParameter("driverId", driver.getId()));
        _component.getTravelModule().search(params, new ApiResultListener() {
            @Override
            public void onSuccess(ApiResult result) {
                travelList.addAll(BaseModule.castObjectList((ArrayList)result.getValue(), Travel.class));

                LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setNestedScrollingEnabled(false);
                recyclerView.setHasFixedSize(false);
                recyclerView.setAdapter(new TravelAdapter(context, travelList));

                closeLoadingPart();
            }

            @Override
            public void onFailure(Enum_ApiResultStatus status, String message) {
                Log.e("ERROR", message);
            }
        });
    }

    private void closeLoadingPart() {
        loadCount++;
        if (loadCount == 1) {
            loadingPart.setVisibility(View.GONE);
            pullToRefresh.setVisibility(View.VISIBLE);
        }
    }
}