package com.example.kaspideimin;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Payments extends AppCompatActivity {
    TextView resultDate;
    String resultDateDate;
    DrawerLayout drawerLayout;
    ViewPager pager;
    TabLayout mTabLayout;
    TabItem firstItem, secondItem, thirdItem;
    PagerAdapter adapter;

    RelativeLayout main, toScanner, messages, thirdLayBtn, backToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);



        main = findViewById(R.id.main);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                servicesToMain();
            }
        });

        toScanner = findViewById(R.id.qr);
        toScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                servicesToScanner();
            }
        });

        messages = findViewById(R.id.message);
        messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                servicesTomessages();
            }
        });

        pager = findViewById(R.id.viewpager);
        mTabLayout = findViewById(R.id.tabLayout);
        firstItem = findViewById(R.id.firstItem);
        secondItem = findViewById(R.id.secondItem);
        thirdItem = findViewById(R.id.thirdItem);


        drawerLayout = findViewById(R.id.drawer);


        adapter = new PagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mTabLayout.getTabCount());
        pager.setAdapter(adapter);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        LayoutInflater factory = getLayoutInflater();
        View thirdLayout = factory.inflate(R.layout.fragment_third, null);

        thirdLayBtn = thirdLayout.findViewById(R.id.payments_third_receipt_btn);
        thirdLayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thirdLayToReceipt();
            }
        });

        backToMain = findViewById(R.id.payments_back_img);
        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                servicesToMain();
            }
        });
    }
    public void servicesToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void servicesToScanner(){
        Intent intent = new Intent(this, Scanner.class);
        startActivity(intent);
    }
    public void servicesTomessages(){
        Intent intent = new Intent(this, Messages.class);
        startActivity(intent);
        finish();
    }
    public void thirdLayToReceipt(){
        Intent intent = new Intent(this, Receipt.class);
        startActivity(intent);
        finish();
    }

    public void read(){
        try {
            FileInputStream fileInput6 = openFileInput("example6.txt");
            InputStreamReader reader6 = new InputStreamReader(fileInput6);
            BufferedReader buffer6 = new BufferedReader(reader6);
            StringBuffer strBuffer6 = new StringBuffer();
            String lines;
            while((lines = buffer6.readLine()) != null){
                strBuffer6.append(lines);
            }
            resultDateDate = strBuffer6.toString();
            System.out.println(resultDateDate);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}