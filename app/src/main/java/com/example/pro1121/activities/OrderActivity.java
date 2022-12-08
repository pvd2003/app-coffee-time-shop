package com.example.pro1121.activities;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import com.example.pro1121.R;
import com.example.pro1121.model.GioHang;
import com.example.pro1121.model.Sanpham;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    TextView tvTilteSP,tvPriceSP,tvThemSL,tvGiamSl,tvNumber, btnBuyNow;
    ImageView imgPrev;
    List<GioHang> listGioHang;
    Sanpham sanpham;

    int number=1;
//   double tongtien;
//    int soluong;
//    int madonhang = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        tvTilteSP= findViewById(R.id.tvTitleSP);
        tvPriceSP= findViewById(R.id.tvPriceSP);
        tvThemSL= findViewById(R.id.tvThemSL);
        tvGiamSl = findViewById(R.id.tvGiamSL);
        tvNumber= findViewById(R.id.tvNumber);
        imgPrev= findViewById(R.id.imgPrev);
        btnBuyNow = findViewById(R.id.btnBuyNow);

        Sanpham sanpham = (Sanpham) getIntent().getSerializableExtra("chitietsanppham");
        tvTilteSP.setText(sanpham.getTenSP());
        tvPriceSP.setText("Giá tiền: " + sanpham.getGiatien());

        tvThemSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluong = Integer.parseInt(tvNumber.getText().toString());
                number=number+1;
                tvNumber.setText(String.valueOf(number));
            }
        });

        tvGiamSl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(number>1){
                    number=number-1;
                }
                tvNumber.setText(toString().valueOf(number));
            }
        });

        imgPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAddGioHang();
            }
        });
    }

    private void onClickAddGioHang(){
//        int soluong = Integer.parseInt(tvNumber.getText().toString());
//        long gia = Long.parseLong(sanpham.getGiatien()) * soluong;
//        HashMap<String,Object> data = new HashMap<>();
//        data.put("tentaikhoan",tv)
        Toast.makeText(this, "Coming soon", Toast.LENGTH_SHORT).show();
    }

}



//    onClickluong = Integer.parseInt(txtnumber.getText().toString());
//         tongtien=(soluong * 3.50);
//         String ten = txtten.getText().toString();
//
//
//
//
//
//        txtbuynow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplication(), TTDonhangActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("ten",ten);
//                bundle.putInt("soluong",soluong);
//                bundle.putDouble("tien",tongtien);
//                bundle.putInt("madonhang",madonhang);
//                intent.putExtra("dulieu",bundle);
//                startActivity(intent);
//
//            }
//        });


