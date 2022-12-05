package com.example.pro1121.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pro1121.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class TTDonhangActivity extends AppCompatActivity {
   Button btnhuy,btnadd;
   EditText edtmadon,edttentk,edtemai,edttensp,edtsoluong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ttdonhang);
        edtmadon = findViewById(R.id.edtMaDonHang);
        edttentk = findViewById(R.id.edtTenTaiKhoan);
        edtemai = findViewById(R.id.edtEmail);
        edttensp= findViewById(R.id.edtSanPham);
        edtsoluong = findViewById(R.id.edtSoLuong);


        btnadd = findViewById(R.id.btnthemmoi);
         btnhuy=findViewById(R.id.btnhuy);
        Intent myintent = getIntent();
        Bundle mybundle = myintent.getBundleExtra("dulieu");
        String tensp = (String) mybundle.get("ten");
        int soluong = mybundle.getInt("soluong");
        int madon = mybundle.getInt("madonhang");
        edtsoluong.setText(""+soluong);
        edttensp.setText(""+tensp);
        edtmadon.setText(""+madon);


         btnhuy.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 finish();
             }
         });
         btnadd.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
               Submit();
             }
         });


    }

    private void Submit() {
        HashMap<String,Object> donhang = new HashMap<>();
        donhang.put("mã đơn hàng",edtmadon.getText().toString());
        donhang.put("tên",edttentk.getText().toString());
        donhang.put("email",edtemai.getText().toString());
        donhang.put("tên sản phẩm",edttensp.getText().toString());
        donhang.put("số lượng",edtsoluong.getText().toString());

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference reference = firebaseFirestore.collection("phieumua");
        reference.add(donhang)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(TTDonhangActivity.this, "Thành công!", Toast.LENGTH_SHORT).show();
                        edtmadon.setText("");
                        edttentk.setText("");
                        edtemai.setText("");
                        edtsoluong.setText("");
                        edttensp.setText("");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(TTDonhangActivity.this, "Thất bại!", Toast.LENGTH_SHORT).show();

                    }
                });
    }
}