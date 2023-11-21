package com.befoys.inventory.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.befoys.R;
import com.befoys.inventory.helpers.DrawerNavigationHelper;

public class WarehouseDocAddActivity extends AppCompatActivity {

    Spinner drpWarehouseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehouse_doc_add);
        DrawerNavigationHelper.build(this);

        bindPageElements();

        bindPageDropDownValues();
    }

    private void bindPageElements() {
        drpWarehouseList = findViewById(R.id.drpWarehouseList);
    }

    private void bindPageDropDownValues()
    {
        String[] items = new String[]{ "انبار گرمدره" };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        drpWarehouseList.setAdapter(adapter);


    }
}