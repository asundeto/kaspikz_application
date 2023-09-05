package com.example.kaspideimin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Transreceipt extends AppCompatActivity {
    TextView cash, date, clientNameRec;
    RelativeLayout closeBtn, againBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transreceipt);
        cash = findViewById(R.id.act_trans_receipt_text);
        date = findViewById(R.id.trans_receipt_date);

        clientNameRec = findViewById(R.id.receipt_client_name);

        closeBtn = findViewById(R.id.trans_receipt_close_btn);
        againBtn = findViewById(R.id.trans_receipt_to_again);

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                receiptToTrans();
            }
        });
        againBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                receiptToClient();
            }
        });
        read();
    }
    public void read(){
        try {
            FileInputStream fileInput1 = openFileInput("last_trans_sum.txt");
            InputStreamReader reader1 = new InputStreamReader(fileInput1);
            BufferedReader buffer1 = new BufferedReader(reader1);
            StringBuffer strBuffer1 = new StringBuffer();
            String lines;
            while((lines = buffer1.readLine()) != null){
                strBuffer1.append(lines);
            }
            cash.setText(strBuffer1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fileInput1 = openFileInput("trans_date.txt");
            InputStreamReader reader1 = new InputStreamReader(fileInput1);
            BufferedReader buffer1 = new BufferedReader(reader1);
            StringBuffer strBuffer1 = new StringBuffer();
            String lines;
            while((lines = buffer1.readLine()) != null){
                strBuffer1.append(lines);
            }
            date.setText(strBuffer1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fileInput1 = openFileInput("edited_client_name.txt");
            InputStreamReader reader1 = new InputStreamReader(fileInput1);
            BufferedReader buffer1 = new BufferedReader(reader1);
            StringBuffer strBuffer1 = new StringBuffer();
            String lines;
            while((lines = buffer1.readLine()) != null){
                strBuffer1.append(lines);
            }
            clientNameRec.setText(strBuffer1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void receiptToTrans(){
        Intent intent = new Intent(this, Translations.class);
        startActivity(intent);
        finish();
    }
    public void receiptToClient(){
        Intent intent = new Intent(this, Clienttrans.class);
        startActivity(intent);
        finish();
    }


}