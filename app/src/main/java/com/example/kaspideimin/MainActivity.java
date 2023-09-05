package com.example.kaspideimin;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private View qr;
    RelativeLayout mainToQrSec, message, services, mainToPayments, mainToMybank, mainToTransBtn;
    //String timeSum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainToQrSec = findViewById(R.id.main_to_qr_sec);

        String data = "5000";
        try {
            FileOutputStream fileOutputStream = openFileOutput("cash.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



        //timeSum = "2000";
        //write();
        mainToTransBtn = findViewById(R.id.main_to_trans);
        mainToTransBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainToTrans();
            }
        });
        mainToMybank = findViewById(R.id.main_to_mybank);
        mainToMybank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainToMyBankAcc();
            }
        });

        mainToPayments = findViewById(R.id.main_to_payments);
        mainToPayments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainToPaymentsMove();
            }
        });

        services = findViewById(R.id.services);
        services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainToServices();
            }
        });
        mainToQrSec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openScanner();
            }
        });
        message = findViewById(R.id.message);
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainToMessages();
            }
        });

        qr = (View)findViewById(R.id.qr);
        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openScanner();
            }
        });
    }
    public void mainToMyBankAcc(){
        Intent intent = new Intent(this, Mybank.class);
        startActivity(intent);
    }
    public void mainToPaymentsMove(){
        Intent intent = new Intent(this, Payments.class);
        startActivity(intent);
        finish();
    }
    public void mainToServices(){
        Intent intent = new Intent(this, Services.class);
        startActivity(intent);
        finish();
    }
    public void openScanner(){
        Intent intent = new Intent(this, Scanner.class);
        startActivity(intent);
    }

    public void mainToMessages(){
        Intent intent = new Intent(this, Messages.class);
        startActivity(intent);
        finish();
    }
    public void mainToTrans(){
        Intent intent = new Intent(this, Translations.class);
        startActivity(intent);
        finish();
    }
}