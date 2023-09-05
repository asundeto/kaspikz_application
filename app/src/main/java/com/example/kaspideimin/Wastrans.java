package com.example.kaspideimin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

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

public class Wastrans extends AppCompatActivity {
    TextView textTransed;
    AppCompatButton wasToTransed;
    RelativeLayout showReceiptBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wastrans);

        showReceiptBtn = findViewById(R.id.show_trans_receipt_btn);
        textTransed = findViewById(R.id.wastrans_sum);
        wasToTransed = findViewById(R.id.green_btn_to_transed);
        wasToTransed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wasToTransedWork();
            }
        });
        showReceiptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wasToReceipt();
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
            textTransed.setText(strBuffer1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void wasToTransedWork(){
        Intent intent = new Intent(this, Translations.class);
        startActivity(intent);
        finish();
    }
    public void wasToReceipt(){
        Intent intent = new Intent(this, Transreceipt.class);
        startActivity(intent);
        finish();
    }

}