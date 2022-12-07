package com.example.pro1121.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pro1121.R;
import com.example.pro1121.model.NguoiDung;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

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
        HashMap<String, Object> nguoidung = new HashMap<>();
        nguoidung.put("name",edtTenTaiKhoan.getText().toString());
        nguoidung.put("sdt",edtSdt.getText().toString());
        nguoidung.put("diachi",edtDiaChi.getText().toString());

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        final CollectionReference reference = firebaseFirestore.collection("nguoidung");
        reference.add(nguoidung)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(ThongTinCaNhanActivity.this, "add thành công!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ThongTinCaNhanActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ThongTinCaNhanActivity.this, "add thất bại!", Toast.LENGTH_SHORT).show();
                    }
                });



        Toast.makeText(this, "xác nhận thành công", Toast.LENGTH_SHORT).show();


    }
}