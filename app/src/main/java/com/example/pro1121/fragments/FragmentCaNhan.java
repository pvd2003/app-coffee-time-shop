package com.example.pro1121.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.pro1121.R;
import com.example.pro1121.activities.MainActivity;
import com.example.pro1121.activities.ThongTinCaNhanActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class FragmentCaNhan extends Fragment {

    private TextView tvTenNguoiDung, tvEmailNguoiDung, btnupdateNguoiDung, btnDoiMatKhau;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ca_nhan, container, false);

        btnupdateNguoiDung = view.findViewById(R.id.btnupdateNguoiDung);
        tvTenNguoiDung = view.findViewById(R.id.tvTenNguoiDung);
        tvEmailNguoiDung = view.findViewById(R.id.tvEmailNguoiDung);
        btnDoiMatKhau = view.findViewById(R.id.btnDoiMatKhau);

        showUserInformation();

        btnupdateNguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogUpdateUser();
            }
        });

        btnDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogDoiMatKhau();
            }
        });


    return view;
    }

    public void showUserInformation(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            return;
        }

        String name = user.getDisplayName();
        String email = user.getEmail();

        if(name == null){
            tvTenNguoiDung.setVisibility(View.GONE);
        }else{
            tvTenNguoiDung.setVisibility(View.VISIBLE);
            tvTenNguoiDung.setText(name);
        }
        tvEmailNguoiDung.setText(email);
    }

    private void showDialogUpdateUser(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setNegativeButton("OK", null)
                .setPositiveButton("Close",null);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_update_ca_nhan,null);
        EditText edtTenUser = view.findViewById(R.id.edtTenUser);
        EditText edtEmailUser = view.findViewById(R.id.edtEmailUser);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            return;
        }
        String name = user.getDisplayName();
        String email = user.getEmail();

        if(name == null){
            edtTenUser.setVisibility(View.GONE);
        }else{
            edtTenUser.setVisibility(View.VISIBLE);
            edtTenUser.setText(name);
        }

        edtEmailUser.setText(email);

        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tennguoidung = edtTenUser.getText().toString().trim();

                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(tennguoidung)
                        .build();

                user.updateProfile(profileUpdates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getActivity(), "update thành công", Toast.LENGTH_SHORT).show();
                                    showUserInformation();
                                    alertDialog.dismiss();

                                }
                            }
                        });
            }
        });
    }

    public void showDialogDoiMatKhau(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setNegativeButton("OK", null)
                .setPositiveButton("Close",null);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_doi_mat_khau,null);
        EditText etOldPass = view.findViewById(R.id.etOldPass);
        EditText etNewPass = view.findViewById(R.id.etNewPass);
        EditText etRePass = view.findViewById(R.id.etRePass);

        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();
            }
        });

    }
}