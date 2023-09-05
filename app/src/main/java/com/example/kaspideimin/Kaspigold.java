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

public class Kaspigold extends AppCompatActivity {
    TextView cashcount;
    RelativeLayout kaspiToMyBankBtn, replenishBtn, stats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaspigold);
        stats = findViewById(R.id.info_stats);
        stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kaspiToStats();
            }
        });

        replenishBtn = findViewById(R.id.replenish_btn);
        replenishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kaspiGoldToReplenish();
            }
        });

        kaspiToMyBankBtn = findViewById(R.id.mybank_line_back);
        kaspiToMyBankBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kaspiToMyBank();
            }
        });

        cashcount = findViewById(R.id.mybank_kaspigold_textview);

        read();
    }
    public void read(){
        try {
            FileInputStream fileInput1 = openFileInput("cash.txt");
            InputStreamReader reader1 = new InputStreamReader(fileInput1);
            BufferedReader buffer1 = new BufferedReader(reader1);
            StringBuffer strBuffer1 = new StringBuffer();
            String lines;
            while((lines = buffer1.readLine()) != null){
                strBuffer1.append(lines);
            }
            cashcount.setText(strBuffer1.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void kaspiGoldToReplenish(){
        Intent intent = new Intent(this, Replenish.class);
        startActivity(intent);
        finish();
    }
    public void kaspiToMyBank(){
        Intent intent = new Intent(this, Mybank.class);
        startActivity(intent);
        finish();
    }
    public void kaspiToStats(){
        Intent intent = new Intent(this, Stats.class);
        startActivity(intent);
        finish();
    }
}