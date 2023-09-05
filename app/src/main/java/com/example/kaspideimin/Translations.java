package com.example.kaspideimin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Translations extends AppCompatActivity {
    RelativeLayout transToClientBtn, backBtn, toMainBtn, toQrBtn, toMessagesBtn, toServicesBtn, transChangeBtn1, transAppear, transApperBack;
    Button nextBtn;
    EditText transEdit;
    String editedName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translations);
        transEdit = findViewById(R.id.trans_edit);

        nextBtn = findViewById(R.id.trans_change_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editedName = transEdit.getText().toString();
                editedWrite();
                transAppear.setVisibility(View.INVISIBLE);
                transApperBack.setVisibility(View.INVISIBLE);
            }
        });

        transAppear = findViewById(R.id.trans_appear);
        transApperBack = findViewById(R.id.trans_appear_back);
        transChangeBtn1 = findViewById(R.id.trans_btn_to_change_name);
        transChangeBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transAppear.setVisibility(View.VISIBLE);
                transApperBack.setVisibility(View.VISIBLE);
            }
        });
        transApperBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transAppear.setVisibility(View.INVISIBLE);
                transApperBack.setVisibility(View.INVISIBLE);
            }
        });

        transToClientBtn = findViewById(R.id.trans_to_transclient);
        transToClientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transToClient();
            }
        });

        backBtn = findViewById(R.id.back_btn_1);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transToMain();
            }
        });

        toMainBtn = findViewById(R.id.main);
        toQrBtn = findViewById(R.id.qr);
        toMessagesBtn = findViewById(R.id.message);
        toServicesBtn = findViewById(R.id.services);


        toMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toMain();
            }
        });
        toQrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toQr();
            }
        });
        toMessagesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toMessages();
            }
        });
        toServicesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toServices();
            }
        });
    }
    public void editedWrite(){
        try {
            FileOutputStream fileOutputCash = openFileOutput("edited_client_name.txt", MODE_PRIVATE);
            fileOutputCash.write(editedName.getBytes());
            fileOutputCash.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void transToClient(){
        Intent intent = new Intent(this, Clienttrans.class);
        startActivity(intent);
        finish();
    }
    public void transToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void toMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void toQr(){
        Intent intent = new Intent(this, Scanner.class);
        startActivity(intent);
    }
    public void toMessages(){
        Intent intent = new Intent(this, Messages.class);
        startActivity(intent);
        finish();
    }
    public void toServices(){
        Intent intent = new Intent(this, Services.class);
        startActivity(intent);
        finish();
    }

}