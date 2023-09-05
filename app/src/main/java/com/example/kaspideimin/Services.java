package com.example.kaspideimin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class Services extends AppCompatActivity {
    RelativeLayout main, qr, message, serviceToPayments, serviceToMybank, servToTransBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        main = findViewById(R.id.main);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                servicesToMain();
            }
        });

        qr = findViewById(R.id.qr);
        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                servicesToQr();
            }
        });

        message = findViewById(R.id.message);
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                servicesToMessages();
            }
        });

        serviceToPayments = findViewById(R.id.service_to_payments_btn);
        serviceToPayments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serviceToPaymentsFunc();
            }
        });

        serviceToMybank = findViewById(R.id.service_to_mybank_btn);
        serviceToMybank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serviceToMyBankW();
            }
        });

        servToTransBtn = findViewById(R.id.service_to_trans);
        servToTransBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                servToTrans();
            }
        });
    }
    public void serviceToPaymentsFunc(){
        Intent intent = new Intent(this, Payments.class);
        startActivity(intent);
        finish();
    }
    public void servicesToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void servicesToQr(){
        Intent intent = new Intent(this, Scanner.class);
        startActivity(intent);
    }
    public void servicesToMessages(){
        Intent intent = new Intent(this, Messages.class);
        startActivity(intent);
        finish();
    }
    public void serviceToMyBankW(){
        Intent intent = new Intent(this, Mybank.class);
        startActivity(intent);
        finish();
    }
    public void servToTrans(){
        Intent intent = new Intent(this, Translations.class);
        startActivity(intent);
        finish();
    }
}