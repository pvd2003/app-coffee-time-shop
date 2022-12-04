package com.example.pro1121.activities;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pro1121.R;

public class OrderActivity extends AppCompatActivity {
   Button txtbuynow ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        txtbuynow = findViewById(R.id.txtbuynow);
        txtbuynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog();
            }
        });
    }

    private void opendialog() {
        AlertDialog.Builder  builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_adddonhang,null);
        builder.setView(view);
        Dialog dialog = new Dialog(this);
        dialog.show();
        }

    }
