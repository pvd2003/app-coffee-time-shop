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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class ThongTinCaNhanActivity extends AppCompatActivity {

    private EditText edtTenTaiKhoan, edtEmail;
    private TextView btnXacNhan;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_ca_nhan);

        edtTenTaiKhoan = findViewById(R.id.edtTenTaiKhoan);
        edtEmail = findViewById(R.id.edtEmail);
        btnXacNhan = findViewById(R.id.btnXacNhan);
        user = FirebaseAuth.getInstance().getCurrentUser();

        showEmail();

        if (user.getDisplayName() == null) {
            btnXacNhan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nhapThongTinCaNhan();
                }
            });
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    //Hiển thị email của người dùng
    public void showEmail(){
        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            return;
        }
        String email = user.getEmail();
        edtEmail.setText(email);
    }

    //Thực hiện nhập tên người dùng
    private void nhapThongTinCaNhan() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        String tennguoidung = edtTenTaiKhoan.getText().toString().trim();

        //Thực hiện nhập tên người dùng
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(tennguoidung)
                .build();

        //Thực hiện truyền dữ liệu lên Firebare
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