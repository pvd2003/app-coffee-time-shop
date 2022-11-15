package com.example.pro1121;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private TextView tvLogin, tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        tvLogin = findViewById(R.id.tvLogin);
        tvRegister = findViewById(R.id.tvRegister);
    }

    public void onLoginClick(View view) {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        if (email.equals("abc") && password.equals("123")) {
            startActivity(new Intent(this, HomeActivity.class));
        } else {
            finish();
        }
    }
}