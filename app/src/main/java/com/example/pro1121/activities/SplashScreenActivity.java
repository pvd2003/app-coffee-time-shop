package com.example.pro1121.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.pro1121.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreenActivity extends AppCompatActivity {
    private TextView tvGetStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    public void onGetStartedClick(View view) {
        nextActivity();
    }

    //Chuyển sang trang Login
    private void nextActivity() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            //Chưa login chuyển sang màn hình Login
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);

        } else {
            //Đã login chuyển sang màn hình nhập thông tin cá nhân
            Intent intent = new Intent(this,ThongTinCaNhanActivity.class);
            startActivity(intent);
        }
    }
}