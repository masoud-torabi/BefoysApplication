package com.befoys.inventory.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.befoys.R;
import com.befoys.core.dagger.DaggerEntityComponent;
import com.befoys.core.dagger.EntityComponent;
import com.befoys.core.enums.Enum_ApiResultStatus;
import com.befoys.core.models.WarehouseDoc;
import com.befoys.core.modules.BaseModule;
import com.befoys.core.persian.PersianDialog;
import com.befoys.core.webservice.base.ApiResult;
import com.befoys.core.webservice.base.ApiResultListener;
import com.befoys.inventory.adapters.WarehouseDocItemAdapter;
import com.befoys.inventory.helpers.DrawerNavigationHelper;

public class WarehouseDocActivity extends AppCompatActivity {

    EntityComponent component;
    Context context;
    ImageButton btnAdd,imgWarehouseSearch;
    EditText txtWarehouseDocId;
    TextView lblToolbarTitle;
    RecyclerView recyclerProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehouse_doc);

        component = DaggerEntityComponent.builder().build();
        context = getApplicationContext();
        DrawerNavigationHelper.build(this);

        bindPageElements();

        lblToolbarTitle.setText(getResources().getString(R.string.menu_warehouse_doc));
    }

    private void bindPageElements()
    {
        btnAdd = findViewById(R.id.btnAdd);
        imgWarehouseSearch = findViewById(R.id.imgWarehouseSearch);
        txtWarehouseDocId = findViewById(R.id.txtWarehouseDocId);
        recyclerProducts = findViewById(R.id.recyclerProducts);
        lblToolbarTitle = findViewById(R.id.lblToolbarTitle);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doAddClick();
            }
        });

        imgWarehouseSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSearch();
            }
        });
    }

    private void doSearch()
    {
        String warehouseDocId = txtWarehouseDocId.getText().toString();
        component.getWarehouseDocModule().getById(warehouseDocId, new ApiResultListener() {
            @Override
            public void onSuccess(ApiResult result) {
                WarehouseDoc entity = BaseModule.castObject(result.getValue(), WarehouseDoc.class);

                WarehouseDocItemAdapter adapter = new WarehouseDocItemAdapter(context, entity.getItems());

                LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                recyclerProducts.setLayoutManager(layoutManager);
                recyclerProducts.setNestedScrollingEnabled(false);
                recyclerProducts.setHasFixedSize(false);
                recyclerProducts.setAdapter(adapter);
            }

            @Override
            public void onFailure(Enum_ApiResultStatus status, String message) {
                PersianDialog.showOkMessage(WarehouseDocActivity.this, message);
            }
        });
    }

    private void doAddClick()
    {
        Intent intent = new Intent(context, WarehouseDocAddActivity.class);
        startActivity(intent);
    }
}