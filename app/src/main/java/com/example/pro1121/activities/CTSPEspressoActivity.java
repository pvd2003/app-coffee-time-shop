package com.example.pro1121.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pro1121.R;
import com.example.pro1121.activities.HomeActivity;

public class CTSPEspressoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctspespresso);
    }

    // Chuyển màn hình từ CTSPEspresso sang Home
    public void onPrevEspressoClick(View view) {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }
}