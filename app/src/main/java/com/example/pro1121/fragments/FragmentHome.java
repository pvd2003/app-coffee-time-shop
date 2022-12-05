package com.example.pro1121.fragments;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.pro1121.R;
import com.example.pro1121.activities.CTSPCappuccinoActivity;
import com.example.pro1121.activities.CTSPEspressoActivity;
import com.example.pro1121.activities.CTSPLatteActivity;
import com.example.pro1121.activities.OrderActivity;
import com.example.pro1121.adapter.PhotoAdapter;
import com.example.pro1121.adapter.SanphamADAPTER;
import com.example.pro1121.adapter.ThemphanloaiADAPTER;
import com.example.pro1121.model.ItemClick;
import com.example.pro1121.model.Photo;
import com.example.pro1121.model.Sanpham;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class FragmentHome extends Fragment {
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private PhotoAdapter adapter;
//    private ImageView sp_1,sp_2, sp_3, sp_4,imageView2;
    List<Sanpham> mlistsp;
   private SanphamADAPTER msanphamaapter;
    RecyclerView recyclerView;


    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.rcysp);

//        sp_1 = view.findViewById(R.id.sp_1);
//        sp_2 = view.findViewById(R.id.sp_2);
//        sp_3 = view.findViewById(R.id.sp_3);
//        sp_4 = view.findViewById(R.id.sp_4);
//        imageView2= view.findViewById(R.id.imageView2);


        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        final CollectionReference reference = firebaseFirestore.collection("sanpham");
        reference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                mlistsp= new ArrayList<>();
                if (task.isSuccessful()) {
                    QuerySnapshot querySnapshot = task.getResult();
                    for (QueryDocumentSnapshot doc : querySnapshot) {
                        Sanpham sanpham = new Sanpham();
                        sanpham.setTenloai(doc.get("tenloai").toString());
//                                sanpham.setGiatien(doc.get("giatien").toString());
                        mlistsp.add(sanpham);
                    }

                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext()
                            , DividerItemDecoration.VERTICAL);
                    recyclerView.addItemDecoration(dividerItemDecoration);
                    msanphamaapter = new SanphamADAPTER(mlistsp, new ItemClick() {
                        @Override
                        public void onClickSanPham(Sanpham sanpham) {
                            Intent intent = new Intent(getContext(),OrderActivity.class);
                            startActivity(intent);
                        }
                    });
                    recyclerView.setAdapter(msanphamaapter);

                }


            }
        });


//        sp_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getContext(), OrderActivity.class));
//            }
//        });
//
//
//        sp_2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), CTSPEspressoActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        sp_3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), CTSPCappuccinoActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        sp_4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getContext(), CTSPLatteActivity.class));
//
//            }
//        });
//        imageView2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getContext(), OrderActivity.class));
//            }
//        });

        viewPager = view.findViewById(R.id.viewpager);
        circleIndicator = view.findViewById(R.id.circleIndicator);

        adapter = new PhotoAdapter(getContext(),getListPhoto());
        viewPager.setAdapter(adapter);

        circleIndicator.setViewPager(viewPager);
        adapter.registerDataSetObserver(circleIndicator.getDataSetObserver());

        return view;
    }

    private List<Photo> getListPhoto(){
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.hsv_1));
        list.add(new Photo(R.drawable.hsv_2));
        list.add(new Photo(R.drawable.hsv_3));
        return list;

    }

}




