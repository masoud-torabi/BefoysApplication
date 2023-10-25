package com.befoys.drivers.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.befoys.R;
import com.befoys.core.dagger.DaggerEntityComponent;
import com.befoys.core.dagger.EntityComponent;
import com.befoys.core.dialogs.CancelDialog;
import com.befoys.core.enums.Enum_ApiResultStatus;
import com.befoys.core.enums.Enum_TravelType;
import com.befoys.core.models.DriverOrder;
import com.befoys.core.models.DriverOrderItem;
import com.befoys.core.models.TravelStep;
import com.befoys.core.modules.BaseModule;
import com.befoys.core.persian.PersianDialog;
import com.befoys.core.utils.BaseConvert;
import com.befoys.core.webservice.base.ApiResult;
import com.befoys.core.webservice.base.ApiResultListener;
import com.befoys.drivers.adapters.OrderAdapter;
import com.befoys.drivers.helpers.DrawerNavigationHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderActivity extends AppCompatActivity {
    EntityComponent _component;
    Context context;
    Integer orderId, stepId, travelId;
    DriverOrder driverOrder;
    String travelType, name;
    private CancelDialog dialog;

    Button btnChangeStatus;
    RecyclerView recycleOrderItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        _component = DaggerEntityComponent.builder().build();
        context = getApplicationContext();
        ButterKnife.bind(this);

        orderId = getIntent().getExtras().getInt("orderId");
        stepId = getIntent().getExtras().getInt("stepId");
        travelId = getIntent().getExtras().getInt("travelId");
        travelType = getIntent().getExtras().getString("travelType");
        name = getIntent().getExtras().getString("name");

        Log.e("TRAVEL_TYPE => ", travelType);

        if (travelType.equals(Enum_TravelType.TRAVEL_TYPE_BUYER.toString())) {
            btnChangeStatus.setText("تحویل کالاهای زیر به " + name);
        } else if (travelType.equals(Enum_TravelType.TRAVEL_TYPE_SHOPRESELLER.toString())) {
            btnChangeStatus.setText("دریافت کالاهای زیر از " + name);
        }

        DrawerNavigationHelper.build(this, false);

        _component.getDriverOrderModule().get(orderId, new ApiResultListener() {
            @Override
            public void onSuccess(ApiResult result) {
                driverOrder = BaseModule.castObject(result.getValue(), DriverOrder.class);

                ArrayList<DriverOrderItem> listItems = new ArrayList();
                for (int i = 0; i < driverOrder.getItems().size(); i++) {
                    DriverOrderItem orderItem = new DriverOrderItem();
                    orderItem.setProduct(driverOrder.getItems().get(i).getProduct());
                    orderItem.setColor(driverOrder.getItems().get(i).getColor());
                    orderItem.setSize(driverOrder.getItems().get(i).getSize());
                    orderItem.setCount(driverOrder.getItems().get(i).getCount());
                    orderItem.setId(driverOrder.getItems().get(i).getId());
                    listItems.add(orderItem);
                }

                OrderAdapter adapter = new OrderAdapter(context, _component, listItems);
                LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                recycleOrderItems.setLayoutManager(layoutManager);
                recycleOrderItems.setNestedScrollingEnabled(false);
                recycleOrderItems.setHasFixedSize(false);
                recycleOrderItems.setAdapter(adapter);

                if (travelType.equals(Enum_TravelType.TRAVEL_TYPE_BUYER.toString())) {
                    adapter.setOnLongClickListener(new OrderAdapter.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View itemView, int position) {
                            DriverOrderItem orderItem = listItems.get(position);

                            dialog = new CancelDialog(OrderActivity.this);
                            dialog.show();
                            EditText txtCancelCount = dialog.findViewById(R.id.txtCancelCount);
                            EditText txtCancelDescription = dialog.findViewById(R.id.txtCancelDescription);
                            ImageView imgOrderItem = dialog.findViewById(R.id.imgOrderItem);
                            TextView lblOrderProductName = dialog.findViewById(R.id.lblOrderProductName);
                            TextView lblOrderCount = dialog.findViewById(R.id.lblOrderCount);
                            Button btnCancel = dialog.findViewById(R.id.btnCancel);

                            txtCancelCount.setText(orderItem.getCount().toString());
                            lblOrderCount.setText(orderItem.getCount().toString());
                            lblOrderProductName.setText(orderItem.getProduct().getName());
                            if (orderItem.getProduct().getPicture() != null)
                            {
                                Picasso.get()
                                        .load(orderItem.getProduct().getPicture().getUrl())
                                        .into(imgOrderItem);
                            }

                            btnCancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Float cancelCount = txtCancelCount.getText().toString() != null ? Float.parseFloat(BaseConvert.toEnglish(txtCancelCount.getText().toString())) : 0;
                                    if (cancelCount <= 0) {
                                        PersianDialog.showOkMessage(OrderActivity.this, "تعداد وارد شده معتبر نمی باشد");
                                    } else if (cancelCount > orderItem.getCount()) {
                                        PersianDialog.showOkMessage(OrderActivity.this, "تعداد وارد شده بیش از تعداد سفارش می باشد");
                                    } else {
                                        DriverOrderItem cancelItem = new DriverOrderItem();
                                        cancelItem.setId(orderItem.getId());
                                        cancelItem.setSize(orderItem.getSize());
                                        cancelItem.setColor(orderItem.getColor());
                                        cancelItem.setProduct(orderItem.getProduct());
                                        cancelItem.setCanceled(true);
                                        cancelItem.setCancelCount(cancelCount);
                                        cancelItem.setCancelDescription(txtCancelDescription.getText().toString());

                                        _component.getDriverOrderModule().cancel(cancelItem, new ApiResultListener() {
                                            @Override
                                            public void onSuccess(ApiResult result) {
                                                PersianDialog.showOkMessageWithClick(OrderActivity.this, result.getMessage(), new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        dialog.dismiss();
                                                        Intent intent = getIntent();
                                                        finish();
                                                        startActivity(intent);
                                                    }
                                                });
                                            }

                                            @Override
                                            public void onFailure(Enum_ApiResultStatus status, String message) {
                                                PersianDialog.showOkMessage(OrderActivity.this, message);
                                            }
                                        });
                                    }
                                }
                            });


                            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialog) {

                                }
                            });

                            return false;
                        }
                    });
                }

            }

            @Override
            public void onFailure(Enum_ApiResultStatus status, String message) {
                String error = message;
            }
        });
    }

    protected void changeStatus()
    {
        TravelStep entity = new TravelStep();
        entity.setId(stepId);

        _component.getTravelStepModule().changeStatus(entity, new ApiResultListener() {
            @Override
            public void onSuccess(ApiResult result) {
                PersianDialog.showOkMessageWithClick(OrderActivity.this, result.getMessage(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context, TravelActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("travelId", travelId);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Enum_ApiResultStatus status, String message) {
                PersianDialog.showOkMessage(OrderActivity.this, message);
            }
        });
    }
}