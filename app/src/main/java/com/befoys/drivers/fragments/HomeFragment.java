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
import com.befoys.core.models.Slider;
import com.befoys.core.models.Travel;
import com.befoys.core.modules.BaseModule;
import com.befoys.core.webservice.base.ApiParameter;
import com.befoys.core.webservice.base.ApiResult;
import com.befoys.core.webservice.base.ApiResultListener;
import com.befoys.drivers.adapters.TravelAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    EntityComponent _component;
    Context context;
    Integer loadCount = 0;
    List<Travel> travelList = new ArrayList<>();
    Driver driver;

    RelativeLayout loadingPart;
    SwipeRefreshLayout pullToRefresh;
    //SliderView sliderView;
    RecyclerView recyclerLastOrders;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
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
        recyclerLastOrders = view.findViewById(R.id.recyclerLastOrders);
    }

    private void bindPageData() {
        //initializeSlider();
        initializeLastOrder();
    }

    private void initializeLastOrder() {
        ArrayList<ApiParameter> params = new ArrayList();
        params.add(new ApiParameter("driverId", driver.getId()));
        _component.getTravelModule().search(params, new ApiResultListener() {
            @Override
            public void onSuccess(ApiResult result) {
                List<Travel> list = BaseModule.castObjectList((ArrayList)result.getValue(), Travel.class);
                if (list != null && list.size() > 0) {
                    travelList.add(list.get(0));
                }

                LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                recyclerLastOrders.setLayoutManager(layoutManager);
                recyclerLastOrders.setNestedScrollingEnabled(false);
                recyclerLastOrders.setHasFixedSize(false);
                recyclerLastOrders.setAdapter(new TravelAdapter(context, travelList));

                closeLoadingPart();
            }

            @Override
            public void onFailure(Enum_ApiResultStatus status, String message) {
                Log.e("ERROR", message);
            }
        });
    }

    /*
    private void initializeSlider() {
        SliderAdapter adapter = new SliderAdapter(getActivity());
        final List<Slider> sliderItemList = new ArrayList();
        _component.getSliderModule().search(new ApiResultListener() {
            @Override
            public void onSuccess(ApiResult result) {
                sliderItemList.addAll(BaseModule.castObjectList((ArrayList)result.getValue(), Slider.class));

                adapter.renewItems(sliderItemList);
                sliderView.setSliderAdapter(adapter);
                sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
                sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                sliderView.setIndicatorSelectedColor(Color.WHITE);
                sliderView.setIndicatorUnselectedColor(Color.GRAY);
                sliderView.setScrollTimeInSec(4);
                sliderView.startAutoCycle();

                closeLoadingPart();
            }

            @Override
            public void onFailure(Enum_ApiResultStatus status, String message) {
                Log.e("ERROR", message);
            }
        });
    }
    */

    private void closeLoadingPart() {
        loadCount++;
        if (loadCount == 2) {
            loadingPart.setVisibility(View.GONE);
            pullToRefresh.setVisibility(View.VISIBLE);
        }
    }
}