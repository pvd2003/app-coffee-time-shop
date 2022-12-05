package com.example.pro1121.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.pro1121.R;

public class OrderActivity extends AppCompatActivity {

    TextView tenSanPham, giaSanPham;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        tenSanPham = findViewById(R.id.titleSP);
        giaSanPham = findViewById(R.id.priceSP);
    }

}