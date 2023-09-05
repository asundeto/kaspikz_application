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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Mybank extends AppCompatActivity {
    RelativeLayout mybankKaspiGold;
    String moneyCount, moneyCountText;
    TextView cashCount;
    RelativeLayout lineBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybank);

        lineBack = findViewById(R.id.mybank_line_back);
        lineBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mybankToMain();
            }
        });

        cashCount = findViewById(R.id.mybank_kaspigold_text);
        read();

        moneyCount = "1000";
        mybankKaspiGold = findViewById(R.id.mybank_kaspigold);
        mybankKaspiGold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myBankToKaspiGold();
            }
        });


    }
    public void timeCash(){
        try {
            FileOutputStream fileOutputCash = openFileOutput("cash.txt", MODE_PRIVATE);
            fileOutputCash.write(moneyCount.getBytes());
            fileOutputCash.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            cashCount.setText(strBuffer1.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void myBankToKaspiGold(){
        Intent intent = new Intent(this, Kaspigold.class);
        startActivity(intent);
        finish();
    }
    public void mybankToMain(){
        finish();
    }
}