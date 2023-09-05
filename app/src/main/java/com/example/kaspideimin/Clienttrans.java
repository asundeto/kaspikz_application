package com.example.kaspideimin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Clienttrans extends AppCompatActivity {
    RelativeLayout backBtn, btn0tng, nextBtn, btnImg, nextBtn2, invis;
    TextView clientCash, changeCash, sumOfTrans1, sumOfTrans2, sumOfTrans3, clientName;
    EditText editTextClient;
    Timer timer;
    String editTime, time2, date;
    Integer clientCashInt, time3;
    private EditText yourEditText;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clienttrans);
        clientName = findViewById(R.id.client_trans_client_name);

        changeCash = findViewById(R.id.client_kaspi_btn_next_text2);
        editTextClient = findViewById(R.id.editT);

        btn0tng = findViewById(R.id.btn_0_tng);
        btnImg = findViewById(R.id.that_page_img);


        yourEditText = (EditText) findViewById(R.id.editT);

        yourEditText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                clientSetText();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });






        clientCash = findViewById(R.id.client_kaspi_cash_count);

        backBtn = findViewById(R.id.client_back_line);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clientToBack();
            }
        });


        //main FUNC
        sumOfTrans1 = findViewById(R.id.sum_of_trans1);
        sumOfTrans2 = findViewById(R.id.sum_of_trans2);
        sumOfTrans3 = findViewById(R.id.client_kaspi_btn_next_text3);
        invis = findViewById(R.id.first_invis);
        nextBtn2 = findViewById(R.id.next_btn_2);
        nextBtn = findViewById(R.id.next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time2 = editTextClient.getText().toString();
                time3 = Integer.parseInt(time2);
                if(time3 > 100) {
                    if(clientCashInt > time3) {
                        btn0tng.setVisibility(View.INVISIBLE);
                        btnImg.setVisibility(View.INVISIBLE);
                        nextBtn.setVisibility(View.INVISIBLE);
                        invis.setVisibility(View.VISIBLE);
                        nextBtn2.setVisibility(View.VISIBLE);

                        sumOfTrans1.setText(time2);
                        sumOfTrans2.setText(time2);
                        sumOfTrans3.setText(time2);
                    }
                }
            }
        });

        nextBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                write();
                writeDateToReceipt();
                clientToGreen();
            }
        });

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
            clientCash.setText(strBuffer1);
            String timeClient = strBuffer1.toString();
            clientCashInt = Integer.parseInt(timeClient);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //--------------------------------------------------------------------------------------------
        try {
            FileInputStream fileInput1 = openFileInput("edited_client_name.txt");
            InputStreamReader reader1 = new InputStreamReader(fileInput1);
            BufferedReader buffer1 = new BufferedReader(reader1);
            StringBuffer strBuffer1 = new StringBuffer();
            String lines;
            while((lines = buffer1.readLine()) != null){
                strBuffer1.append(lines);
            }
            clientName.setText(strBuffer1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void write(){
        try {
            FileOutputStream fileOutputCash = openFileOutput("last_trans_sum.txt", MODE_PRIVATE);
            fileOutputCash.write(time2.getBytes());
            fileOutputCash.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Integer remainder = clientCashInt - time3;
        String remainderStr = remainder.toString();

        try {
            FileOutputStream fileOutputCash = openFileOutput("cash.txt", MODE_PRIVATE);
            fileOutputCash.write(remainderStr.getBytes());
            fileOutputCash.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeDateToReceipt(){
        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        date = simpleDateFormat.format(calendar.getTime());
        try {
            FileOutputStream fileOutputCash = openFileOutput("trans_date.txt", MODE_PRIVATE);
            fileOutputCash.write(date.getBytes());
            fileOutputCash.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void clientToGreen(){
        Intent intent = new Intent(this, Wastrans.class);
        startActivity(intent);
        finish();
    }

    public void clientToBack(){
        Intent intent = new Intent(this, Translations.class);
        startActivity(intent);
        finish();
    }

    public void clientSetText(){
        String timeles = editTextClient.getText().toString();
        changeCash.setText(timeles);
    }
}