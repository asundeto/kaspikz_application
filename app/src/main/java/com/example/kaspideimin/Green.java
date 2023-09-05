package com.example.kaspideimin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Green extends AppCompatActivity {
    RelativeLayout greenBtn;
    Button greenBtnToPayments;
    String code, date, time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green);

        greenBtnToPayments = findViewById(R.id.green_btn_to_payments);
        greenBtnToPayments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                greenToPayments();
            }
        });


        greenBtn = findViewById(R.id.green_btn);
        code = getIntent().getStringExtra("codeKey");
        date = getIntent().getStringExtra("dateKey");
        time = getIntent().getStringExtra("timeKey");

        greenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                greenBtnWork();
            }
        });
    }
    public void  greenBtnWork(){
        Intent intent = new Intent(this, Qr.class);
        intent.putExtra("codeKey", code);
        intent.putExtra("dateKey", date);
        intent.putExtra("timeKey", time);
        startActivity(intent);
        finish();
    }
    public void greenToPayments(){
        Intent intent = new Intent(this, Payments.class);
        startActivity(intent);
        finish();
    }
}