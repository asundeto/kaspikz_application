package com.example.kaspideimin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Qr extends AppCompatActivity {
    String code, date, time;
    TextView resultCode, resultDate, resultTime;
    ImageView qrClose;
    Button qrBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        qrBackBtn = findViewById(R.id.qr_activity_btn);
        qrClose = findViewById(R.id.qr_qr_close);
        qrClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qrToReceipt();
            }
        });
        qrBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qrToPayments();
            }
        });

        resultDate = findViewById(R.id.resultDate);
        resultTime = findViewById(R.id.resultTime);
        resultCode = findViewById(R.id.resultCode);

        read();
    }
    public void qrToPayments(){
        Intent intent = new Intent(this, Payments.class);
        startActivity(intent);
        finish();
    }
    public void qrToReceipt(){
        Intent intent = new Intent(this, Receipt.class);
        startActivity(intent);
        finish();
    }
    public void read(){
        try {
            FileInputStream fileInput1 = openFileInput("example1.txt");
            InputStreamReader reader1 = new InputStreamReader(fileInput1);
            BufferedReader buffer1 = new BufferedReader(reader1);
            StringBuffer strBuffer1 = new StringBuffer();
            String lines;
            while((lines = buffer1.readLine()) != null){
                strBuffer1.append(lines);
            }
            resultCode.setText(strBuffer1.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileInputStream fileInput4 = openFileInput("example4.txt");
            InputStreamReader reader4 = new InputStreamReader(fileInput4);
            BufferedReader buffer4 = new BufferedReader(reader4);
            StringBuffer strBuffer4 = new StringBuffer();
            String lines;
            while((lines = buffer4.readLine()) != null){
                strBuffer4.append(lines);
            }
            resultDate.setText(strBuffer4.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileInputStream fileInput5 = openFileInput("example5.txt");
            InputStreamReader reader5 = new InputStreamReader(fileInput5);
            BufferedReader buffer5 = new BufferedReader(reader5);
            StringBuffer strBuffer5 = new StringBuffer();
            String lines;
            while((lines = buffer5.readLine()) != null){
                strBuffer5.append(lines);
            }
            resultTime.setText(strBuffer5.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}