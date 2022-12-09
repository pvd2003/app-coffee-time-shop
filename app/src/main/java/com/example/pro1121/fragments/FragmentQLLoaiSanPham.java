package com.example.pro1121.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pro1121.R;
import com.example.pro1121.adapter.QuanLyLoaiSanPhamAdapter;
import com.example.pro1121.model.LoaiSanPham;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentQLLoaiSanPham extends Fragment {
    private EditText edtTenLoai, edtMaLoai;
    private Button btnList, btnUpdateLoai, btnAddLoai;
    private RecyclerView rvQLLoaiSanPham;
    private List<LoaiSanPham> mlist;
    private QuanLyLoaiSanPhamAdapter adapter;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ql_loai_san_pham, container, false);
        edtTenLoai = view.findViewById(R.id.edtTenLoai);
        edtMaLoai = view.findViewById(R.id.edtMaLoai);
        btnList = view.findViewById(R.id.btnList);
        btnUpdateLoai = view.findViewById(R.id.btnUpdateLoai);
        btnAddLoai = view.findViewById(R.id.btnAddLoai);
        rvQLLoaiSanPham = view.findViewById(R.id.rvQLLoaiSanPham);

        getlistdatafirebasestore();
        btnAddLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAddLoai();
            }
        });

        return view;
    }

    private void onClickAddLoai() {
        Map<String, Object> loaisanpham = new HashMap<>();
        loaisanpham.put("tenloai", edtTenLoai.getText().toString());
        final CollectionReference reference = firebaseFirestore.collection("loaisanpham");
        reference.add(loaisanpham)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getContext(), "add thành công!", Toast.LENGTH_SHORT).show();
                        edtTenLoai.setText("");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "add thất bại!", Toast.LENGTH_SHORT).show();

                    }
                });
    }

    public void getlistdatafirebasestore() {
        final CollectionReference reference = firebaseFirestore.collection("loaisanpham");
        reference
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                mlist = new ArrayList<>();
                if (task.isSuccessful()) {
                    QuerySnapshot querySnapshot = task.getResult();
                    for (QueryDocumentSnapshot doc : querySnapshot) {
                        LoaiSanPham loaiSanPham = new LoaiSanPham();
                        loaiSanPham.setTenloai(doc.getString("tenloai"));
                        loaiSanPham.setMaloai(doc.getId());
                        mlist.add(loaiSanPham);

                    }
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    rvQLLoaiSanPham.setLayoutManager(linearLayoutManager);
                    adapter = new QuanLyLoaiSanPhamAdapter(mlist);
                    rvQLLoaiSanPham.setAdapter(adapter);
                }

            }
        });
    }

}
