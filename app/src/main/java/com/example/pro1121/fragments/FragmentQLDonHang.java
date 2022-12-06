package com.example.pro1121.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pro1121.R;
import com.example.pro1121.adapter.DonHangAdapter;
import com.example.pro1121.model.Donhang;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FragmentQLDonHang extends Fragment {
    private DonHangAdapter mDonhangadater;
    private List<Donhang> mlist;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ql_don_hang, container, false);
        recyclerView = view.findViewById(R.id.rvQLDonHang);
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference reference = firebaseFirestore.collection("phieumua");
        reference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                mlist = new ArrayList<>();
                if(task.isSuccessful()){
                    QuerySnapshot querySnapshot = task.getResult();
                 for (QueryDocumentSnapshot doc: querySnapshot){
                     Donhang donhang = new Donhang();
                     donhang.setEmail(doc.get("email").toString());
                    donhang.setMadonhang(Integer.parseInt(doc.get("mã đơn hàng").toString()));
                     donhang.setSoluong(Integer.parseInt(doc.get("số lượng").toString()));
                     donhang.setTentk(doc.get("tên").toString());
                     donhang.setTensp(doc.get("tên sản phẩm").toString());

                     mlist.add(donhang);
                 }
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(linearLayoutManager);
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext()
                            , DividerItemDecoration.VERTICAL);
                    recyclerView.addItemDecoration(dividerItemDecoration);
                     mDonhangadater = new DonHangAdapter(mlist);
                    recyclerView.setAdapter(mDonhangadater);
                }

            }
        });

        return view;
    }
}
