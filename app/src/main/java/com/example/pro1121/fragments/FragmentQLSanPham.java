package com.example.pro1121.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pro1121.R;
import com.example.pro1121.adapter.SanphamADAPTER;
import com.example.pro1121.model.ItemClick;
import com.example.pro1121.model.Sanpham;
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

 public class FragmentQLSanPham extends Fragment {
    EditText edttenloai, edtgiatien;
    ImageView ivtitle;
    RecyclerView recyclerView;
    Button btnthem, btnhuy;
    private List<Sanpham> sanphamList;
    private SanphamADAPTER sanphamADAPTER;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ql_san_pham, container, false);
        edttenloai = view.findViewById(R.id.edttenloaisp);
        edtgiatien = view.findViewById(R.id.edtgiatiensp);
        ivtitle = view.findViewById(R.id.titleSanPham);
        recyclerView = view.findViewById(R.id.rvQLSanPham);
        btnhuy = view.findViewById(R.id.btnhuy);
        btnthem = view.findViewById(R.id.btnthemmoi);

       onclickthem();
        getlistdatafirebasestore();
        return view;

    }

    private void onclickthem() {
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> sanpham = new HashMap<>();
                sanpham.put("tensp", edttenloai.getText().toString());
                sanpham.put("giatien", edtgiatien.getText().toString());
                FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
                final CollectionReference reference = firebaseFirestore.collection("sanpham");
                reference.add(sanpham)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(getContext(), "add thành công!", Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(), "add thất bại!", Toast.LENGTH_SHORT).show();

                            }
                        });

            }
        });


    }

    public void getlistdatafirebasestore() {
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
                final CollectionReference reference = firebaseFirestore.collection("sanpham");
                reference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        sanphamList = new ArrayList<>();
                        if (task.isSuccessful()) {
                            QuerySnapshot querySnapshot = task.getResult();
                            for (QueryDocumentSnapshot doc : querySnapshot) {
                                Sanpham sanpham = new Sanpham();
                                sanpham.setTenSP(doc.get("tenloai").toString());
//                             sanpham.setGiatien(doc.get("giatien").toString());

                                sanphamList.add(sanpham);

                            }
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                            recyclerView.setLayoutManager(gridLayoutManager);
                            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext()
                                    , DividerItemDecoration.VERTICAL);
                            recyclerView.addItemDecoration(dividerItemDecoration);
                            sanphamADAPTER = new SanphamADAPTER(sanphamList, new ItemClick() {
                                @Override
                                public void onClickSanPham(Sanpham sanpham) {
                                    edttenloai.setText(sanpham.getTenSP());
                                    edtgiatien.setText(sanpham.getGiatien());
                                }
                            });
                            recyclerView.setAdapter(sanphamADAPTER);
                        }

                    }
                });
            }
        });
    }
}

