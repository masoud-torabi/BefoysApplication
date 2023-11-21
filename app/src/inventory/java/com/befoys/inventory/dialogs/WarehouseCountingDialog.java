package com.befoys.inventory.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.befoys.R;
import com.befoys.core.models.Product;
import com.squareup.picasso.Picasso;

public class WarehouseCountingDialog extends Dialog implements android.view.View.OnClickListener{

    TextView lblProductName;
    ImageView imgProduct, imgBackSpace;
    TextView txtCount;
    Button btnRegister, btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnDot;

    Product product;

    public WarehouseCountingDialog(@NonNull Context context, Product product) {
        super(context);
        this.product = product;
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(getContext(), R.layout.dialog_warehouse_counting, null);

        bindPageElements(view);

        setContentView(view);
    }

    @Override
    public void onClick(View view) {
    }

    private void bindPageElements(View view) {
        lblProductName = view.findViewById(R.id.lblProductName);
        imgProduct = view.findViewById(R.id.imgProduct);
        txtCount = view.findViewById(R.id.txtCount);
        btnRegister = view.findViewById(R.id.btnRegister);

        btn0 = view.findViewById(R.id.btn0);
        btn1 = view.findViewById(R.id.btn1);
        btn2 = view.findViewById(R.id.btn2);
        btn3 = view.findViewById(R.id.btn3);
        btn4 = view.findViewById(R.id.btn4);
        btn5 = view.findViewById(R.id.btn5);
        btn6 = view.findViewById(R.id.btn6);
        btn7 = view.findViewById(R.id.btn7);
        btn8 = view.findViewById(R.id.btn8);
        btn9 = view.findViewById(R.id.btn9);
        btnDot = view.findViewById(R.id.btnDot);
        imgBackSpace = view.findViewById(R.id.imgBackSpace);

        lblProductName.setText(product.getId() + " - " + product.getName());
        txtCount.setText(null);
        txtCount.requestFocus();
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(txtCount, InputMethodManager.SHOW_IMPLICIT);
        if (product.getPicture() != null)
        {
            Picasso.get()
                    .load(product.getPicture().getUrl())
                    .into(imgProduct);
        }

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtCount.getText() != null && txtCount.getText().length() > 0) {
                    txtCount.setText(txtCount.getText() + "0");
                }
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtCount.setText(txtCount.getText() + "1");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtCount.setText(txtCount.getText() + "2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtCount.setText(txtCount.getText() + "3");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtCount.setText(txtCount.getText() + "4");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtCount.setText(txtCount.getText() + "5");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtCount.setText(txtCount.getText() + "6");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtCount.setText(txtCount.getText() + "7");
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtCount.setText(txtCount.getText() + "8");
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtCount.setText(txtCount.getText() + "9");
            }
        });
        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtCount.getText() != null && txtCount.getText().length() > 0) {
                    txtCount.setText(txtCount.getText() + ".");
                }
            }
        });
        imgBackSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = txtCount.getText().toString();
                if (value != null && value.length() > 0) {
                    value = value.substring(0, value.length() - 1);
                    txtCount.setText(value);
                }
            }
        });
    }
}