package com.example.pro1121.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pro1121.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;

public class FragmentQLTaiKhoan extends Fragment {
    private RelativeLayout rvQLTaiKhoan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ql_tai_khoan, container, false);
//        rvQLTaiKhoan = view.findViewById(R.id.rvQLTaiKhoan);


        return view;
    }

    public void showUserInformation(){
        HashMap<String, Object> data = new HashMap<>();
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        data.get("user", )

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        rvQLTaiKhoan.setLayoutManager(linearLayoutManager);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext()
//                , DividerItemDecoration.VERTICAL);
//        recyclerView.addItemDecoration(dividerItemDecoration);
    }
}
