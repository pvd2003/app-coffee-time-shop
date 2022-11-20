package com.example.pro1121.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.pro1121.R;

public class HomeActivity extends AppCompatActivity {
    private ImageView ivMenuCoffee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    // Chuyển màn hình từ Home sang Menu
    public void onMenuCoffeeClick(View view) {
        startActivity(new Intent(this, MenuActivity.class));
    }

    // Chuyển màn hình từ Home sang CTSPCappuccino
    public void onCappuccinoClick(View view) {
        startActivity(new Intent(this, CTSPCappuccinoActivity.class));
    }

    // Chuyển màn hình từ Home sang CTSPEspresso
    public void onEspressoClick(View view) {
        startActivity(new Intent(this, CTSPEspressoActivity.class));
    }

    // Chuyển màn hình từ Home sang CTSPLatte
    public void onLatteClick(View view) {
        startActivity(new Intent(this, CTSPLatteActivity.class));
    }
}