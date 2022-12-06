package com.example.pro1121.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pro1121.R;

public class ThongTinCaNhanActivity extends AppCompatActivity {

    EditText edtTenTaiKhoan, edtSdt, edtDiaChi;
    TextView btnXacNhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_ca_nhan);

        edtTenTaiKhoan = findViewById(R.id.edtTenTaiKhoan);
        edtSdt = findViewById(R.id.edtSdt);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        btnXacNhan = findViewById(R.id.btnXacNhan);

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhapTTCN();
            }
        });
    }

    private void NhapTTCN(){
        String tenTaiKhoan = edtTenTaiKhoan.getText().toString();
        String sdt = edtSdt.getText().toString();
        String diChi = edtDiaChi.getText().toString();


        Toast.makeText(this, "xác nhận thành công", Toast.LENGTH_SHORT).show();


    }
}