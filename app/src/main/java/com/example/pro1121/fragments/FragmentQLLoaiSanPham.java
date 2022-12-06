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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pro1121.R;
import com.example.pro1121.adapter.ThemphanloaiADAPTER;
import com.example.pro1121.model.LoaiSanPham;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
    Button btnadd,btnhuy;
    EditText edtmaloai,edttenloai;
    RecyclerView recyclerView;
    ThemphanloaiADAPTER phanloaiadapter;
    List<LoaiSanPham> mlistlsp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ql_loai_san_pham, container, false);
        btnadd = view.findViewById(R.id.btnthemmoilsp);
        btnhuy =view.findViewById(R.id.btnhuylsp);
         edtmaloai = view.findViewById(R.id.edtmaloailsp);
         edttenloai = view.findViewById(R.id.edttenloailsp);
        recyclerView = view.findViewById(R.id.rvQLLoaiSanPham);
        onclick();
        ongetlistdata();

        return view;


    }

    private void ongetlistdata() {
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
                final CollectionReference reference = firebaseFirestore.collection("sanpham");
                reference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        mlistlsp = new ArrayList<>();
                        if (task.isSuccessful()) {
                            QuerySnapshot querySnapshot = task.getResult();
                            for (QueryDocumentSnapshot doc : querySnapshot) {
                                LoaiSanPham lsp = new LoaiSanPham();
//                                lsp.setMaloai(Integer.parseInt(doc.get("maloai").toString()));
                                lsp.setTenloai(doc.get("tenloai").toString());
                                mlistlsp.add(lsp);
                            }

                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                            recyclerView.setLayoutManager(linearLayoutManager);
                            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext()
                                    , DividerItemDecoration.VERTICAL);
                            recyclerView.addItemDecoration(dividerItemDecoration);
                            phanloaiadapter = new ThemphanloaiADAPTER(mlistlsp);
                            recyclerView.setAdapter(phanloaiadapter);

                        }


                    }
                });


            }


        });
    }

    private void onclick() {
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
                final CollectionReference reference = firebaseFirestore.collection("sanpham");
                Map<String,Object> loaisanpham = new HashMap<>();
                loaisanpham.put("maloai",edtmaloai.getText().toString());
                loaisanpham.put("tenloai",edttenloai.getText().toString());
                reference.add(loaisanpham)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(getContext(), "add thành công!", Toast.LENGTH_SHORT).show();


                            }
                        })       .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(), "add thất bại!", Toast.LENGTH_SHORT).show();

                            }
                        });


            }
        });
    }



    }









