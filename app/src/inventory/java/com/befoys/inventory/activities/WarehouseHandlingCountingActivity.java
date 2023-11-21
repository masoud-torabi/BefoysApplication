package com.befoys.inventory.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.befoys.R;
import com.befoys.core.dagger.DaggerEntityComponent;
import com.befoys.core.dagger.EntityComponent;
import com.befoys.core.enums.Enum_ApiResultStatus;
import com.befoys.core.models.WarehouseHandlingNoItem;
import com.befoys.core.modules.BaseModule;
import com.befoys.core.persian.PersianDialog;
import com.befoys.core.viewmodel.ViewWarehouseHandlingPositionItem;
import com.befoys.core.webservice.base.ApiResult;
import com.befoys.core.webservice.base.ApiResultListener;
import com.befoys.inventory.adapters.WarehouseHandlingPositionAdapter;
import com.befoys.inventory.dialogs.WarehouseCountingDialog;
import com.befoys.inventory.helpers.DrawerNavigationHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WarehouseHandlingCountingActivity extends AppCompatActivity {
    EntityComponent component;
    Context context;
    TextView lblToolbarTitle;
    RecyclerView recyclerProducts;
    EditText txtBarcode;

    Integer warehouseHandlingNoId;
    String barcode = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehouse_handling_counting);

        component = DaggerEntityComponent.builder().build();
        context = getApplicationContext();
        warehouseHandlingNoId = getIntent().getIntExtra("HandlingNoId", 0);
        DrawerNavigationHelper.build(this);

        bindPageElements();
        bindPageList();

        txtBarcode.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(txtBarcode, InputMethodManager.SHOW_IMPLICIT);

        lblToolbarTitle.setText(getResources().getString(R.string.menu_warehouse_handling));
    }

    private void bindPageElements() {
        lblToolbarTitle = findViewById(R.id.lblToolbarTitle);
        recyclerProducts = findViewById(R.id.recyclerProducts);
        txtBarcode = findViewById(R.id.txtBarcode);

        txtBarcode.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    barcode = txtBarcode.getText().toString();
                    bindPageList();
                    return true;
                }
                return false;
            }
        });
    }

    private void bindPageList() {
        component.getWarehouseHandlingNoItemModule().search(warehouseHandlingNoId, "", barcode, null, new ApiResultListener() {
            @Override
            public void onSuccess(ApiResult result) {
                ArrayList<WarehouseHandlingNoItem> list = BaseModule.castObjectList((ArrayList)result.getValue(), WarehouseHandlingNoItem.class);
                List<WarehouseHandlingNoItem> listDistinct =
                        list
                        .stream()
                        .filter(distinctByKey(x -> x.getShelfName()))
                        .collect(Collectors.toList());

                ArrayList<ViewWarehouseHandlingPositionItem> listFinal = new ArrayList();
                for(int i = 0; i < listDistinct.size(); i++) {
                    ViewWarehouseHandlingPositionItem item = new ViewWarehouseHandlingPositionItem();
                    item.setPositionName(listDistinct.get(i).getShelfName());
                    item.setShelfId(listDistinct.get(i).getShelfId());

                    List<WarehouseHandlingNoItem> listValues = new ArrayList<>();
                    item.setItems(listValues);

                    if (barcode != null &&
                        barcode != "") {
                        listValues = list.stream()
                                .filter(a -> a.getShelfName().contains(item.getPositionName()))
                                .collect(Collectors.toList());
                    }

                    item.setItems(listValues);
                    listFinal.add(item);
                }

                WarehouseHandlingPositionAdapter adapter = new WarehouseHandlingPositionAdapter(context, component, barcode, warehouseHandlingNoId, listFinal);

                LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                recyclerProducts.setLayoutManager(layoutManager);
                recyclerProducts.setNestedScrollingEnabled(false);
                recyclerProducts.setHasFixedSize(false);
                recyclerProducts.setAdapter(adapter);

                adapter.setOnRowItemListener(new WarehouseHandlingPositionAdapter.OnRowListener() {
                    @Override
                    public void onItemClick(View itemView, WarehouseHandlingNoItem selectedItem) {
                        WarehouseCountingDialog dialog = new WarehouseCountingDialog(WarehouseHandlingCountingActivity.this, selectedItem.getProduct());

                        dialog.show();

                        Button btnRegister = dialog.findViewById(R.id.btnRegister);
                        TextView txtCount = dialog.findViewById(R.id.txtCount);
                        btnRegister.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                double count = Double.parseDouble(txtCount.getText().toString());
                                selectedItem.setCount(count);
                                component.getWarehouseHandlingNoItemModule().doCount(selectedItem, new ApiResultListener() {
                                    @Override
                                    public void onSuccess(ApiResult result) {
                                        txtBarcode.setText(null);
                                        barcode = null;
                                        bindPageList();
                                        PersianDialog.showOkMessageWithClick(WarehouseHandlingCountingActivity.this, result.getMessage(), new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                txtBarcode.requestFocus();
                                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                                imm.showSoftInput(txtBarcode, InputMethodManager.SHOW_IMPLICIT);

                                                dialog.dismiss();
                                            }
                                        });
                                    }

                                    @Override
                                    public void onFailure(Enum_ApiResultStatus status, String message) {
                                        PersianDialog.showOkMessage(WarehouseHandlingCountingActivity.this, message);
                                    }
                                });
                            }
                        });
                    }
                });
            }

            @Override
            public void onFailure(Enum_ApiResultStatus status, String message) {

            }
        });
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}