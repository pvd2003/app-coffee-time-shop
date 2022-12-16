package com.example.pro1121.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pro1121.R;
import com.example.pro1121.activities.MainActivity;
import com.example.pro1121.adapter.GioHangAdapter;
import com.example.pro1121.model.GioHang;
import com.example.pro1121.model.Sanpham;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

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
        gioHangList = new ArrayList<>();

        getlistdatafirebasestore();

        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickThanhToan();
            }
        });
        return view;
    }

    //Lấy dữ liệu từ Firebase
    public void getlistdatafirebasestore() {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        final CollectionReference reference = firebaseFirestore.collection("giohang");
        reference
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
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
                    tongTien();
                    loadData();
                }

            }
        });
    }

    //Hiện tất cả sản phẩm lên màn hình
    private void loadData(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);;
        adapter = new GioHangAdapter(gioHangList);
        recyclerView.setAdapter(adapter);
    }

    //Thực hiện thanh toán các sản phẩm
    private void onClickThanhToan(){
        putLichSU();
        for(int i = 0; i < gioHangList.size(); i++){
            GioHang gioHang = (GioHang) gioHangList.get(i);
            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
            final CollectionReference reference = firebaseFirestore.collection("giohang");
            reference.document(gioHang.getIdgiohang())
                    .delete()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            gioHangList.clear();
                            loadData();
                            tvThanhTien.setText("0 vnd");
                            Toast.makeText(getContext(), "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
        }
        diaLogThanhCong();
    }

    //Thực hiện tính tổng thành tiền của tất cả sản phẩm có trong giỏ hàng
    private void tongTien(){
        int tong = 0;
        for(int i = 0; i < gioHangList.size(); i++){
            GioHang gioHang = (GioHang) gioHangList.get(i);
            int soluong = Integer.parseInt(gioHang.getSoluong());
            int gia = Integer.parseInt(gioHang.getGiasp());
            tong = tong + (soluong * gia);
        }
        tvThanhTien.setText(tong +" vnd");

    }

    private void putLichSU(){
        String tong = tvThanhTien.getText().toString();
        String currentDate = new SimpleDateFormat("HH:mm dd-MM-yyyy", Locale.getDefault()).format(new Date());
        HashMap<String, Object> map = new HashMap<>();
        map.put("ngay",currentDate);
        map.put("tong",tong);
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        final CollectionReference referencels = firebaseFirestore.collection("lichsu");
        referencels
                .add(map)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    private void diaLogThanhCong(){
        DialogPlus dialogPlus = DialogPlus.newDialog(getContext())
                .setContentHolder(new ViewHolder(R.layout.dialog_thanh_toan))
                .setExpanded(true,1500)
                .create();

        View mView = dialogPlus.getHolderView();
        TextView btnChuyenTrang = mView.findViewById(R.id.btnChuyenTrang);

        btnChuyenTrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        dialogPlus.show();
    }

}
