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
import com.example.pro1121.fragments.FragmentCaNhan;
import com.example.pro1121.model.NguoiDung;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class ThongTinCaNhanActivity extends AppCompatActivity {

    private EditText edtTenTaiKhoan, edtSdt, edtEmail, edtDiaChi;
    private TextView btnXacNhan;
    private ThongTinCaNhanActivity mThongTinCaNhanActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_ca_nhan);

        edtTenTaiKhoan = findViewById(R.id.edtTenTaiKhoan);
//        edtSdt = findViewById(R.id.edtSdt);
        edtEmail = findViewById(R.id.edtEmail);
//        edtDiaChi = findViewById(R.id.edtDiaChi);
        btnXacNhan = findViewById(R.id.btnXacNhan);
        mThongTinCaNhanActivity = ThongTinCaNhanActivity.this;

        showEmail();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user.getDisplayName() == null) {
            btnXacNhan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nhapThongTinCaNhan();
                }
            });
        } else {
            //Đã Nhập Thông Tin
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void showEmail(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            return;
        }
        String email = user.getEmail();
        edtEmail.setText(email);
    }

    private void nhapThongTinCaNhan() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String tennguoidung = edtTenTaiKhoan.getText().toString().trim();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(tennguoidung)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ThongTinCaNhanActivity.this, "thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ThongTinCaNhanActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                });
    }

}