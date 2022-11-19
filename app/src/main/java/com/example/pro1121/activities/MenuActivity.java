package com.example.pro1121.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.pro1121.R;

public class MenuActivity extends AppCompatActivity {
    private ImageView ivPrev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    // Chuyển màn hình từ Menu sang Home
    public void onPrevClick(View view) {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }
}