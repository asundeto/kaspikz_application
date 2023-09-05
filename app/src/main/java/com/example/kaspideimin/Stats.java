package com.example.kaspideimin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Stats extends AppCompatActivity {
    RelativeLayout backBtn;
    TextView countTips, sumTips;
    String count;
    Button cleanStats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        countTips = findViewById(R.id.count_of_trips);
        sumTips = findViewById(R.id.sum_of_trips);
        cleanStats = findViewById(R.id.clean_stats);

        cleanStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statsCleaning();
                read();
            }
        });

        backBtn = findViewById(R.id.stats_back_line);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statsToKaspi();
            }
        });

        read();
    }
    public void read(){
        try {
            FileInputStream fileInput1 = openFileInput("stats.txt");
            InputStreamReader reader1 = new InputStreamReader(fileInput1);
            BufferedReader buffer1 = new BufferedReader(reader1);
            StringBuffer strBuffer1 = new StringBuffer();
            String lines;
            while((lines = buffer1.readLine()) != null){
                strBuffer1.append(lines);
            }
            countTips.setText(strBuffer1.toString());
            count = countTips.getText().toString();
            Integer count2 = Integer.parseInt(count);
            count2 *= 90;
            String count3 = count2.toString();
            sumTips.setText(count3);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void statsCleaning(){
        try {
            FileOutputStream fileOutputCash = openFileOutput("stats.txt", MODE_PRIVATE);
            String cleaningStr = "0";
            fileOutputCash.write(cleaningStr.getBytes());
            fileOutputCash.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void statsToKaspi(){
        Intent intent = new Intent(this, Kaspigold.class);
        startActivity(intent);
        finish();
    }
}