package com.example.pro1121.fragments;

import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.util.Log;
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
    TextView tvThanhTien,btnThanhToan;
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

        getlistdatafirebasestore();
        tongTien();

        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickThanhToan();
            }
        });


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
                        gioHang.setIdgiohang(doc.getId());
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

    private void tongTien(){
        int tong = 0;
        gioHangList = new ArrayList<>();
        for(int i = 0; i < gioHangList.size(); i++){
            GioHang gioHang = (GioHang) gioHangList.get(i);
            int soluong = Integer.parseInt(gioHang.getSoluong());
            int gia = Integer.parseInt(gioHang.getGiasp());
            tong = tong + (soluong * gia);
        }
        tvThanhTien.setText(tong +" VND");
    }

//    private BroadcastReceiver thanhtoanreceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Long tongThanhToan = intent.getLongExtra("result", 0);
//            ArrayIdSach = (ArrayList<String>) intent.getSerializableExtra("ArrayIdSach");
//            if (ArrayIdSach != null) {
//                txtXoa.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        for (int i = 0; i < ArrayIdSach.size(); i++) {
//                            db.collection("giohang")
//                                    .document(id + ArrayIdSach.get(i))
//                                    .delete()
//                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                        @Override
//                                        public void onSuccess(Void unused) {
//                                            list.clear();
//                                            getIdSach();
//                                            txtTongThanhToan.setText("0");
//                                        }
//                                    })
//                                    .addOnFailureListener(new OnFailureListener() {
//                                        @Override
//                                        public void onFailure(@NonNull Exception e) {
//
//                                        }
//                                    });
//                        }
//                        Toast.makeText(GioHangActivity.this, "Xóa thành công ", Toast.LENGTH_LONG).show();
//
//                    }
//                });
//            }
//            if (tongThanhToan != 0) {
//                txtTongThanhToan.setText(String.valueOf(tongThanhToan));
//            } else {
//                txtTongThanhToan.setText(String.valueOf(tongThanhToan));
//            }
//
//        }
//    };


}
