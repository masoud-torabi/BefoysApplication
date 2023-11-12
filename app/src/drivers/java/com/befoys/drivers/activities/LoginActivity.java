package com.befoys.drivers.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.befoys.R;
import com.befoys.core.dagger.DaggerEntityComponent;
import com.befoys.core.dagger.EntityComponent;
import com.befoys.core.enums.Enum_ApiResultStatus;
import com.befoys.core.models.Driver;
import com.befoys.core.modules.BaseModule;
import com.befoys.core.persian.PersianDialog;
import com.befoys.core.webservice.base.ApiResult;
import com.befoys.core.webservice.base.ApiResultListener;

public class LoginActivity extends AppCompatActivity {
    EntityComponent _component;
    Context context;
    Driver driver;

    EditText txtLoginMobile, txtLoginCodeValue;
    CardView panelLogin, panelLoginComplete;
    Button btnLogin, btnLoginComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _component = DaggerEntityComponent.builder().build();

        driver = _component.getDriverModule().getCurrentDriver();
        if (driver != null)
        {
            //_component.getDeviceModule().sendDeviceInfoToServer(context);
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
        }

        bindPageElements();
        bindButtonClickEvents();
    }

    private void bindPageElements()
    {
        txtLoginMobile = findViewById(R.id.txtLoginMobile);
        txtLoginCodeValue = findViewById(R.id.txtLoginCodeValue);
        panelLogin = findViewById(R.id.panelLogin);
        panelLoginComplete = findViewById(R.id.panelLoginComplete);
        btnLogin = findViewById(R.id.btnLogin);
        btnLoginComplete = findViewById(R.id.btnLoginComplete);
    }

    private void bindButtonClickEvents() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobile = txtLoginMobile.getText().toString();
                _component.getDriverModule().login(mobile, new ApiResultListener() {
                    @Override
                    public void onSuccess(ApiResult result) {
                        driver = BaseModule.castObject(result.getValue(), Driver.class);

                        panelLogin.setVisibility(View.GONE);
                        panelLoginComplete.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onFailure(Enum_ApiResultStatus status, String message) {
                        PersianDialog.showOkMessage(LoginActivity.this, message);
                    }
                });
            }
        });

        btnLoginComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                driver.setSmsValue(txtLoginCodeValue.getText().toString());
                _component.getDriverModule().loginComplete(driver, new ApiResultListener() {
                    @Override
                    public void onSuccess(ApiResult result) {
                        Driver entity = BaseModule.castObject(result.getValue(), Driver.class);

                        //_component.getDeviceModule().sendDeviceInfoToServer(context);

                        _component.getDriverModule().setCurrentDriver(entity);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("selectedTabId", R.id.menu_item_home);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Enum_ApiResultStatus status, String message) {
                        PersianDialog.showOkMessage(LoginActivity.this, message);
                    }
                });
            }
        });
    }
}