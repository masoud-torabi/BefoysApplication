package com.befoys.inventory.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.befoys.R;
import com.befoys.core.dagger.DaggerEntityComponent;
import com.befoys.core.dagger.EntityComponent;
import com.befoys.core.enums.Enum_ApiResultStatus;
import com.befoys.core.models.WarehouseDoc;
import com.befoys.core.models.WarehouseHandling;
import com.befoys.core.modules.BaseModule;
import com.befoys.core.webservice.base.ApiResult;
import com.befoys.core.webservice.base.ApiResultListener;
import com.befoys.inventory.helpers.DrawerNavigationHelper;

import java.util.ArrayList;
import java.util.List;

public class WarehouseHandlingActivity extends AppCompatActivity {
    EntityComponent component;
    Context context;
    TextView lblToolbarTitle;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehouse_handling);

        component = DaggerEntityComponent.builder().build();
        context = getApplicationContext();
        DrawerNavigationHelper.build(this);

        bindPageElements();
        bindPageList();

        lblToolbarTitle.setText(getResources().getString(R.string.menu_warehouse_handling));
    }

    private void bindPageList() {
        component.getWarehouseHandlingModule().getAllActive(new ApiResultListener() {
            @Override
            public void onSuccess(ApiResult result) {
                ArrayList<String> listItem = new ArrayList<>();
                ArrayList<WarehouseHandling> entity = BaseModule.castObjectList((ArrayList)result.getValue(), WarehouseHandling.class);
                for (int i = 0; i < entity.size(); i++) {
                    listItem.add(entity.get(i).getName() + " (" + entity.get(i).getStatus() + ")");
                }
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.partial_simple_text, R.id.txtSimple, listItem);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        WarehouseHandling selectedItem = entity.get(position);
                        String value = selectedItem.getName();

                        Intent nextPage = new Intent(context, WarehouseHandlingCountingActivity.class);
                        nextPage.putExtra("HandlingId", selectedItem.getId());
                        nextPage.putExtra("HandlingNoId", selectedItem.getNoId());
                        startActivity(nextPage);
                    }
                });
            }

            @Override
            public void onFailure(Enum_ApiResultStatus status, String message) {
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void bindPageElements()
    {
        listView = findViewById(R.id.listView);
        lblToolbarTitle = findViewById(R.id.lblToolbarTitle);
    }
}