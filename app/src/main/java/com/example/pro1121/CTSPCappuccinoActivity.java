package com.example.pro1121;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CTSPCappuccinoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctspcappuccino);
    }

    // Chuyển màn hình từ CTSPCappuccino sang Home
    public void onPrevCappuccinoClick(View view) {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }
}