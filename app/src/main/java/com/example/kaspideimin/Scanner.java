package com.example.kaspideimin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

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

public class Scanner extends AppCompatActivity {
    String moneyCount;
    CodeScanner codeScanner;
    CodeScannerView scannerView;
    String code, date, time, dateToQr, timeToQr, dateToPayments, setM, copyList;
    Button scannerBtn;
    RelativeLayout progressBar, qrCopyBack;
    Timer timer;
    Integer finded;
    TextView qrToCopy;

    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    SimpleDateFormat simpleDateFormat2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        progressBar = findViewById(R.id.progress_bar);

        scannerBtn = findViewById(R.id.scannerBtn);
        scannerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scannerToMain();
            }
        });

        scannerView = findViewById(R.id.scannerView);
        codeScanner = new CodeScanner(this, scannerView);

        qrToCopy = findViewById(R.id.qr_to_copy_set);

        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        //ALGORITM FROM BOQTAMA
                        System.out.println(result.getText().getClass());
                        String qrCode = result.getText();
                        qrCopyBack = findViewById(R.id.qr_copy_back);
                        progressBar.setVisibility(View.VISIBLE);

                        int n = qrCode.length();
                        int g = 0;
                        char[] qrList =  qrCode.toCharArray();
                        char[] qrList2 = new char[5];
                        char[] qrList3 = new char[5];
                        finded = 0;
                        for(int i = 0; i < n; i++) {
                            if(qrList[i] == 42) {
                                g = i - 9;
                                finded = 1;
                                break;
                            } else{
                                //COPY
                                copyList = "";
                                for(int j = 0; j < n; j++){
                                    copyList += qrList[j];
                                }
                                Log.d("myTag", copyList);
                                qrToCopy.setText(copyList);
                            }
                        }
                        int j = 5;
                        for(int i = 0; i < j; i++){
                            qrList2[i] = qrList[g];
                            g++;
                        }
                        setM = "";
                        for (int i = 0; i < 5; i++){
                            setM += qrList2[i];
                            qrList3[i] = qrList2[i];
                        }
                        code = setM;
                        timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                if(finded == 1) {
                                    openBetween();
                                    progressBar.setVisibility(View.INVISIBLE);
                                }
                                else {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    qrCopyBack.setVisibility(View.VISIBLE);
                                }
                            }
                        }, 2000);

                    }
                });
            }
        });
        //
    }
    public void openBetween(){
        //Creating everything
        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        simpleDateFormat2 = new SimpleDateFormat("HH:mm:ss");
        date = simpleDateFormat.format(calendar.getTime());
        time = simpleDateFormat2.format(calendar.getTime());

        simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");
        simpleDateFormat2 = new SimpleDateFormat("HH:mm");

        dateToQr = simpleDateFormat.format(calendar.getTime());
        timeToQr = simpleDateFormat2.format(calendar.getTime());

        simpleDateFormat = new SimpleDateFormat("dd MMMM");
        dateToPayments = simpleDateFormat.format(calendar.getTime());

        Intent intent = new Intent(this, Between.class);
        intent.putExtra("codeKey", code);
        intent.putExtra("dateKey", date);
        intent.putExtra("timeKey", time);
        startActivity(intent);
        finish();

        //Upload to file
        write();
    }
    public void write() {
        try {
            FileOutputStream fileOutput1 = openFileOutput("example1.txt", MODE_PRIVATE);
            fileOutput1.write(code.getBytes());
            fileOutput1.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fileOutput2 = openFileOutput("example2.txt", MODE_PRIVATE);
            fileOutput2.write(date.getBytes());
            fileOutput2.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fileOutput3 = openFileOutput("example3.txt", MODE_PRIVATE);
            fileOutput3.write(time.getBytes());
            fileOutput3.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fileOutput4 = openFileOutput("example4.txt", MODE_PRIVATE);
            fileOutput4.write(dateToQr.getBytes());
            fileOutput4.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fileOutput5 = openFileOutput("example5.txt", MODE_PRIVATE);
            fileOutput5.write(timeToQr.getBytes());
            fileOutput5.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fileOutput5 = openFileOutput("example6.txt", MODE_PRIVATE);
            fileOutput5.write(dateToPayments.getBytes());
            fileOutput5.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void scannerToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    codeScanner.startPreview();
    }
}