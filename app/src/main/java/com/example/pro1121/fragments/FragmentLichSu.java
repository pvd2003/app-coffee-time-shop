package com.example.pro1121.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pro1121.R;
import com.example.pro1121.adapter.GioHangAdapter;
import com.example.pro1121.adapter.LichSuAdapter;
import com.example.pro1121.model.GioHang;
import com.example.pro1121.model.LichSu;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;


public class FragmentLichSu extends Fragment {

    private RecyclerView rvLichSu;
    private ArrayList<LichSu> lichSuList;
    private LichSuAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lich_su, container, false);
        rvLichSu = view.findViewById(R.id.rvLichSu);
        lichSuList = new ArrayList<>();

        getlistdatafirebasestore();

        return view;
    }

    //Lấy dữ liệu từ Firebase
    public void getlistdatafirebasestore() {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        final CollectionReference reference = firebaseFirestore.collection("lichsu");
        reference
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot querySnapshot = task.getResult();
                            for (QueryDocumentSnapshot doc : querySnapshot) {
                                LichSu lichSu = new LichSu();
                                lichSu.setEmail(doc.getString("em"));
                                lichSu.setTime(doc.getString("ngay"));
                                lichSu.setTong(doc.getString("tong"));
                                lichSuList.add(lichSu);
                            }
                            loadData();
                        }

                    }
                });
    }

    //Hiện tất cả sản phẩm lên màn hình
    private void loadData(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvLichSu.setLayoutManager(linearLayoutManager);;
        adapter = new LichSuAdapter(lichSuList);
        rvLichSu.setAdapter(adapter);
    }

}
