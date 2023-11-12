package com.befoys.inventory.activities;

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
import com.befoys.core.models.SiteUser;
import com.befoys.core.modules.BaseModule;
import com.befoys.core.persian.PersianDialog;
import com.befoys.core.webservice.base.ApiResult;
import com.befoys.core.webservice.base.ApiResultListener;

public class LoginActivity extends AppCompatActivity {
    EntityComponent _component;
    Context context;
    SiteUser user;

    EditText txtLoginMobile, txtLoginPassword;
    Button btnLogin, btnLoginComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _component = DaggerEntityComponent.builder().build();

        user = _component.getSiteUserModule().getCurrentSiteUser();
        if (user != null)
        {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
        }

        bindPageElements();
        bindButtonClickEvents();
    }

    private void bindPageElements()
    {
        txtLoginMobile = findViewById(R.id.txtLoginMobile);
        txtLoginPassword = findViewById(R.id.txtLoginPassword);
        btnLogin = findViewById(R.id.btnLogin);
    }

    private void bindButtonClickEvents() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobile = txtLoginMobile.getText().toString();
                String password = txtLoginPassword.getText().toString();
                _component.getSiteUserModule().login(mobile, password, new ApiResultListener() {
                    @Override
                    public void onSuccess(ApiResult result) {
                        user = BaseModule.castObject(result.getValue(), SiteUser.class);

                        _component.getSiteUserModule().setCurrentSiteUser(user);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
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