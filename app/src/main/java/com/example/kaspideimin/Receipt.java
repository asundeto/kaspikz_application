package com.example.kaspideimin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Receipt extends AppCompatActivity {
    RelativeLayout receiptQrBtn, receiptCloseBtn;
    TextView resultCode, resultCode2, resultDate, resultTime;
    String code, date, time;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);
        receiptCloseBtn = findViewById(R.id.receipt_close_btn);
        receiptCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                receiptToPayments();
            }
        });


        receiptQrBtn = findViewById(R.id.receipt_qr_btn);
        receiptQrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                receiptToQr();
            }
        });

        resultCode = findViewById(R.id.resultCode);
        resultCode2 = findViewById(R.id.resultCode2);

        resultDate = findViewById(R.id.resultDate);
        resultTime = findViewById(R.id.resultTime);

        read();
    }
    public void receiptToPayments(){
        Intent intent = new Intent(this, Payments.class);
        startActivity(intent);
        finish();
    }
    public void receiptToQr(){
        Intent intent = new Intent(this, Qr.class);
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
            resultCode2.setText(strBuffer1.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            FileInputStream fileInput2 = openFileInput("example2.txt");
            InputStreamReader reader2 = new InputStreamReader(fileInput2);
            BufferedReader buffer2 = new BufferedReader(reader2);
            StringBuffer strBuffer2 = new StringBuffer();
            String lines;
            while((lines = buffer2.readLine()) != null){
                strBuffer2.append(lines);
            }
            resultDate.setText(strBuffer2.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            FileInputStream fileInput3 = openFileInput("example3.txt");
            InputStreamReader reader3 = new InputStreamReader(fileInput3);
            BufferedReader buffer3 = new BufferedReader(reader3);
            StringBuffer strBuffer3 = new StringBuffer();
            String lines;
            while((lines = buffer3.readLine()) != null){
                strBuffer3.append(lines);
            }
            resultTime.setText(strBuffer3.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}