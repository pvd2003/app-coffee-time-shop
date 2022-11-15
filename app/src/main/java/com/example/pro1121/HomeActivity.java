package com.example.pro1121;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {
    private ImageView ivMenuCoffee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void onMenuCoffeeClick(View view) {
        startActivity(new Intent(this, MenuActivity.class));
    }
}