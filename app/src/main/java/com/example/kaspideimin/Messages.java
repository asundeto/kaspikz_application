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

public class Messages extends AppCompatActivity {
    private View qr;
    RelativeLayout main, kaspiGoldId, services, messagesToTransReceiptBtn;
    TextView resultTime, resultTime2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        services = findViewById(R.id.services);
        services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messagesToServices();
            }
        });

        messagesToTransReceiptBtn = findViewById(R.id.messages_to_trans_receipt_btn);

        resultTime = findViewById(R.id.messages_pay_time);
        resultTime2 = findViewById(R.id.messages_pay_time2);

        kaspiGoldId = findViewById(R.id.kaspiGoldId);
        kaspiGoldId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openReceipt();
            }
        });

        qr = (View)findViewById(R.id.qr);
        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openScanner();
            }
        });
        main = findViewById(R.id.main);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messagesToMain();
            }
        });
        messagesToTransReceiptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messagesToTransReceipt();
            }
        });

        read();
    }
    public void messagesToTransReceipt(){
        Intent intent = new Intent(this, Transreceipt.class);
        startActivity(intent);
        finish();
    }
    public void messagesToServices(){
        Intent intent = new Intent(this, Services.class);
        startActivity(intent);
        finish();
    }
    public void messagesToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void openScanner(){
        Intent intent = new Intent(this, Scanner.class);
        startActivity(intent);
    }
    public void openReceipt(){
        Intent intent = new Intent(this, Receipt.class);
        startActivity(intent);
        finish();
    }
    public void read(){
        try {
            FileInputStream fileInput1 = openFileInput("example5.txt");
            InputStreamReader reader1 = new InputStreamReader(fileInput1);
            BufferedReader buffer1 = new BufferedReader(reader1);
            StringBuffer strBuffer1 = new StringBuffer();
            String lines;
            while((lines = buffer1.readLine()) != null){
                strBuffer1.append(lines);
            }
            resultTime.setText(strBuffer1.toString());
            resultTime2.setText(strBuffer1.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}