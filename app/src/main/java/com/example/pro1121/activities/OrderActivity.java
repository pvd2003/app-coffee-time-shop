package com.example.pro1121.activities;


import androidx.annotation.NonNull;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderActivity extends AppCompatActivity {
    TextView tvTilteSP,tvPriceSP,tvThemSL,tvGiamSl,tvNumber, btnBuyNow;
    ImageView imgPrev;
    List<GioHang> listGioHang;
    Sanpham sanpham;

    int number= 1;


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

        getChiTietSP();


        //Nút tăng số lượng
        tvThemSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number=number+1;
                tvNumber.setText(String.valueOf(number));
            }
        });

        //Nút giảm số lượng
        tvGiamSl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(number>1){
                    number=number-1;
                }
                tvNumber.setText(toString().valueOf(number));
            }
        });

        //Nút quay lại
        imgPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Nút mua sản phẩm
        btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAddGioHang();
            }
        });
    }

    //Nút thêm sản phẩm vào giỏ hàng
    private void onClickAddGioHang(){
        String tensp = tvTilteSP.getText().toString();
        String giaSP = tvPriceSP.getText().toString();
        String soluong = tvNumber.getText().toString();
        Map<String, Object> giohang = new HashMap<>();
        giohang.put("soluong",soluong);
        giohang.put("gia", giaSP);
        giohang.put("tensanpham",tensp);
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        final CollectionReference reference = firebaseFirestore.collection("giohang");
        reference.add(giohang)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(OrderActivity.this, "add thành công!", Toast.LENGTH_SHORT).show();
                        finish();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(OrderActivity.this, "add thất bại!", Toast.LENGTH_SHORT).show();

                    }
                });
    }

    //Lấy thông tin chi tiết sản phẩm từ trang home
    private void getChiTietSP(){
        Sanpham sanpham = (Sanpham) getIntent().getSerializableExtra("chitietsanppham");
        tvTilteSP.setText(sanpham.getTenSP());
        tvPriceSP.setText(sanpham.getGiatien());
    }

}



