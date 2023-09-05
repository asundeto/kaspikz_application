package com.example.kaspideimin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

public class Replenish extends AppCompatActivity {
    RelativeLayout lineToBack;
    Button replenishBtn;
    EditText writeSum;
    String sum, sum3, sum6, writeSumText;
    Integer writeSumTextInt;
    Integer sum2, sum4, sum5;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replenish);



        replenishBtn = findViewById(R.id.replenish_btn_repl);
        replenishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeSum = findViewById(R.id.edit_text);
                writeSumText = writeSum.getText().toString();
                if(!writeSumText.isEmpty()) {

                    writeSumTextInt = Integer.parseInt(writeSumText);

                    if(writeSumTextInt >= 100){
                        System.out.println(writeSumText);
                        replenishAct();
                        writeSum.setText("");
                        Toast.makeText(Replenish.this, "Success !", Toast.LENGTH_SHORT).show();

                        timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                replenishToKaspiGold();
                            }
                        }, 1000);
                    }
                    else {
                        Toast.makeText(Replenish.this, "Error !!!", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(Replenish.this, "Error !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        lineToBack = findViewById(R.id.mybank_line_back);
        lineToBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replenishToKaspiGold();
            }
        });
    }
    public void replenishToKaspiGold(){
        Intent intent = new Intent(this, Kaspigold.class);
        startActivity(intent);
        finish();
    }
    public void replenishAct(){
        try {
            FileInputStream fileInput1 = openFileInput("cash.txt");
            InputStreamReader reader1 = new InputStreamReader(fileInput1);
            BufferedReader buffer1 = new BufferedReader(reader1);
            StringBuffer strBuffer1 = new StringBuffer();
            String lines;
            while((lines = buffer1.readLine()) != null){
                strBuffer1.append(lines);
            }
            sum = (strBuffer1.toString());
            sum2 = Integer.parseInt(sum);
            sum3 = writeSum.getText().toString();
            sum4 = Integer.parseInt(sum3);
            sum5 = sum2 + sum4;
            sum6 = sum5.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileOutputStream fileOutputCash = openFileOutput("cash.txt", MODE_PRIVATE);
            fileOutputCash.write(sum6.getBytes());
            fileOutputCash.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}