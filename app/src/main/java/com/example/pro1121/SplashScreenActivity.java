package com.example.pro1121;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {
    private TextView tvGetStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    public void onGetStartedClick(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }
}