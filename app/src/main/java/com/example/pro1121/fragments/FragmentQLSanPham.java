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
import com.example.pro1121.adapter.QuanLySanPhamAdapter;
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
import java.util.Map;

 public class FragmentQLSanPham extends Fragment {
    EditText edtTenSP, edtGiaSP;
    RecyclerView recyclerView;
    Button btnAddSP, btnList;
    private ArrayList<Sanpham> sanphamList;
    private QuanLySanPhamAdapter quanLySanPhamAdapter;
     FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
     final CollectionReference reference = firebaseFirestore.collection("sanpham");


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ql_san_pham, container, false);
        edtTenSP = view.findViewById(R.id.edtTenSP);
        edtGiaSP = view.findViewById(R.id.edtGiaSP);
        recyclerView = view.findViewById(R.id.rvQLSanPham);
        btnList = view.findViewById(R.id.btnList);
        btnAddSP = view.findViewById(R.id.btnAddSP);
        getlistdatafirebasestore();

            btnAddSP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(edtTenSP.getText().toString().equals("")){
                        Toast.makeText(getContext(), "Vui lòng nhập tên sản phẩm", Toast.LENGTH_SHORT).show();
                        return;
                    }if(edtGiaSP.getText().toString().equals("")){
                        Toast.makeText(getContext(), "Vui lòng nhập giá sản phẩm", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    onClickAdd();
                }
            });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getlistdatafirebasestore();
            }
        });

        return view;

    }
    //Thực hiện thêm sản phẩm
    private void onClickAdd() {
         Map<String, Object> sanpham = new HashMap<>();
         sanpham.put("tensanpham", edtTenSP.getText().toString());
         sanpham.put("giasanpham", edtGiaSP.getText().toString());
         reference.add(sanpham)
                 .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                     @Override
                     public void onSuccess(DocumentReference documentReference) {
                         Toast.makeText(getContext(), "add thành công!", Toast.LENGTH_SHORT).show();
                         edtTenSP.setText("");
                         edtGiaSP.setText("");
                         getlistdatafirebasestore();
                     }
                 }).addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e) {
                         Toast.makeText(getContext(), "add thất bại!", Toast.LENGTH_SHORT).show();

                     }
                 });
    }

    //Lấy dữ liệu từ Firebase
    public void getlistdatafirebasestore() {
        reference
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                sanphamList = new ArrayList<>();
                if (task.isSuccessful()) {
                    QuerySnapshot querySnapshot = task.getResult();
                    for (QueryDocumentSnapshot doc : querySnapshot) {
                        Sanpham sanpham = new Sanpham();
                        sanpham.setTenSP(doc.get("tensanpham").toString());
                        sanpham.setGiatien(doc.get("giasanpham").toString());
                        sanpham.setIdsanpham(doc.getId());
                        sanphamList.add(sanpham);
                    }
                    loadData();
                }

            }
        });

    }

     //Hiện tất cả sản phẩm lên màn hình
     private void loadData(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext()
                , DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        quanLySanPhamAdapter = new QuanLySanPhamAdapter(sanphamList);
        recyclerView.setAdapter(quanLySanPhamAdapter);
    }

}

