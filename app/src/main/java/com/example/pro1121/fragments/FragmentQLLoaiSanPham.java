package com.example.pro1121.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pro1121.R;
import com.example.pro1121.adapter.ThemphanloaiADAPTER;
import com.example.pro1121.model.Loaisanpham;
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
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    ThemphanloaiADAPTER phanloaiadapter;
    private FirebaseFirestore firebaseFirestore;
    List<Loaisanpham> mlistlsp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ql_loai_san_pham, container, false);
        recyclerView = view.findViewById(R.id.rvQLLoaiSanPham);
        opendialog();
        getlistdatafirebasestore();

        floatingActionButton = view.findViewById(R.id.floatAdd);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             opendialog();
            }
        });
        return view;


    }

    private void opendialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater= this.getLayoutInflater();
        firebaseFirestore = FirebaseFirestore.getInstance();
        final CollectionReference reference = firebaseFirestore.collection("loaisanpham");
        View view =inflater.inflate(R.layout.dialog_addloaisp,null);
        EditText edtmaloai = view.findViewById(R.id.edtmaloai);
        EditText edttenloai = view.findViewById(R.id.edttenloai);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext()
                , DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);




        Button btnadd = view.findViewById(R.id.btnthemmoi);
        Button btnhuy = view.findViewById(R.id.btnhuy);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> loaisanpham = new HashMap<>();
                loaisanpham.put("maloai",edtmaloai.getText().toString());
                loaisanpham.put("tenloai",edttenloai.getText().toString());
                reference.add(loaisanpham)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getContext(), "add thành công!", Toast.LENGTH_SHORT).show();
                        edttenloai.setText("");
                        edtmaloai.setText("");

                    }
                })       .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(), "add thất bại!", Toast.LENGTH_SHORT).show();
                                
                            }
                        });

            }
        });
        getlistdatafirebasestore();

    }

    private void getlistdatafirebasestore() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        final CollectionReference reference = firebaseFirestore.collection("loaisanpham");
        reference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                mlistlsp = new ArrayList<>();
              if (task.isSuccessful()){
                  QuerySnapshot querySnapshot = task.getResult();
                for (QueryDocumentSnapshot doc:querySnapshot){
                    Loaisanpham loaisanpham = new Loaisanpham();
                    loaisanpham.setMaloai(Integer.parseInt(doc.get("maloai").toString()));
                    loaisanpham.setTenloai(doc.get("tenloai").toString());
                    mlistlsp.add(loaisanpham);
                }
                  phanloaiadapter = new ThemphanloaiADAPTER(mlistlsp);
                  recyclerView.setAdapter(phanloaiadapter);

              }


            }
        });



    }


}
