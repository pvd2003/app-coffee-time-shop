package com.example.pro1121.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pro1121.R;
import com.example.pro1121.adapter.GioHangAdapter;
import com.example.pro1121.model.GioHang;
import com.example.pro1121.model.Sanpham;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FragmentQLGioHang extends Fragment {
    private TextView tvThanhTien,btnThanhToan;
    List<GioHang> gioHangList;
    GioHangAdapter adapter;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ql_gio_hang, container, false);
        recyclerView = view.findViewById(R.id.rvQLGioHang);
        tvThanhTien = view.findViewById(R.id.tvThanhTien);
        btnThanhToan = view.findViewById(R.id.btnThanhToan);

        tvThanhTien.setText((int) getTotalPrice(gioHangList));




        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickThanhToan();
            }
        });

        getlistdatafirebasestore();
        return view;
    }

    public void getlistdatafirebasestore() {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        final CollectionReference reference = firebaseFirestore.collection("giohang");
        reference
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                gioHangList = new ArrayList<>();
                if (task.isSuccessful()) {
                    QuerySnapshot querySnapshot = task.getResult();
                    for (QueryDocumentSnapshot doc : querySnapshot) {
                        GioHang gioHang = new GioHang();
                        gioHang.setTensp(doc.getString("tensanpham"));
                        gioHang.setGiasp(doc.getString("gia"));
                        gioHang.setSoluong(doc.getString("soluong"));
                        gioHangList.add(gioHang);

                    }
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(linearLayoutManager);;
                    adapter = new GioHangAdapter(gioHangList);
                    recyclerView.setAdapter(adapter);
                }

            }
        });
    }

    private void onClickThanhToan(){

    }

    public static long getTotalPrice(List<GioHang> list){
        int tongTien = 0;
        for(int i =0;i<list.size();i++){
            GioHang gioHang = list.get(i);
            int sl = Integer.parseInt(gioHang.getSoluong());
            int gia = Integer.parseInt(gioHang.getSoluong());
            tongTien += sl * gia;
        }
        return tongTien;
    }
}
