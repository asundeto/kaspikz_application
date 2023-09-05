package com.example.kaspideimin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Between extends AppCompatActivity {
    String moneyCount;
    View lineToBack;
    Button continiueBtn;
    String code, date, time,statsCount;
    Integer moneyCheck;
    TextView resultCode, betweenMoneyCount, betweenMoneyCount2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_between);
        betweenMoneyCount = findViewById(R.id.between_money_count);
        betweenMoneyCount2 = findViewById(R.id.between_money_count2);
        resultCode = (TextView) findViewById(R.id.resultCode);
        //resultCode2 = (TextView) findViewById(R.id.resultCode2);
        //resultDate = (TextView) findViewById(R.id.resultDate);
        //resultTime = (TextView) findViewById(R.id.resultTime);


        code = getIntent().getStringExtra("codeKey");
        date = getIntent().getStringExtra("dateKey");
        time = getIntent().getStringExtra("timeKey");

        resultCode.setText(code);
        //resultCode2.setText(code);
        //resultDate.setText(date);
        //resultTime.setText(time);
        statsCount = "1";
        continiueBtn = findViewById(R.id.between_btn);
        lineToBack = findViewById(R.id.lineToBack);
        lineToBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lineToBackWork();
            }
        });
        continiueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(moneyCheck >= 90) {
                    continiueWork();
                    readAndWriteBank();
                    readAndWriteStats();
                } else {
                    betweenMoneyCount.setTextColor(Color.RED);
                    betweenMoneyCount2.setTextColor(Color.RED);
                    return;
                }

            }
        });
        read();
    }

    public void lineToBackWork(){
        Intent intent = new Intent(this, Scanner.class);
        startActivity(intent);
        finish();
    }
    public void continiueWork(){
        Intent intent = new Intent(this, Green.class);
        intent.putExtra("codeKey", code);
        intent.putExtra("dateKey", date);
        intent.putExtra("timeKey", time);
        startActivity(intent);
        finish();
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
            moneyCount = strBuffer1.toString();
            moneyCheck = Integer.parseInt(moneyCount);
            String lastCount = moneyCount;
            betweenMoneyCount.setText(lastCount);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void readAndWriteBank(){
        try {
            FileInputStream fileInput1 = openFileInput("cash.txt");
            InputStreamReader reader1 = new InputStreamReader(fileInput1);
            BufferedReader buffer1 = new BufferedReader(reader1);
            StringBuffer strBuffer1 = new StringBuffer();
            String lines;
            while((lines = buffer1.readLine()) != null){
                strBuffer1.append(lines);
            }
            moneyCount = strBuffer1.toString();
            Integer monCount = Integer.parseInt(moneyCount);
            monCount -= 90;
            moneyCount = monCount.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

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

    public void readAndWriteStats(){
        try {
            FileInputStream fileInput1 = openFileInput("stats.txt");
            InputStreamReader reader1 = new InputStreamReader(fileInput1);
            BufferedReader buffer1 = new BufferedReader(reader1);
            StringBuffer strBuffer1 = new StringBuffer();
            String lines;
            while((lines = buffer1.readLine()) != null){
                strBuffer1.append(lines);
            }
            String statsStr = strBuffer1.toString();
            Integer statsInt = Integer.parseInt(statsStr);
            statsInt += 1;
            statsStr = statsInt.toString();
            statsCount = statsStr;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fileOutputCash = openFileOutput("stats.txt", MODE_PRIVATE);
            fileOutputCash.write(statsCount.getBytes());
            fileOutputCash.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}