package com.example.pro1121.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.pro1121.R;
import com.example.pro1121.activities.CTSPCappuccinoActivity;
import com.example.pro1121.activities.CTSPEspressoActivity;
import com.example.pro1121.activities.CTSPLatteActivity;
import com.example.pro1121.adapter.PhotoAdapter;
import com.example.pro1121.model.Photo;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class FragmentHome extends Fragment {
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private PhotoAdapter adapter;
    private ImageView sp_2, sp_3, sp_4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        sp_2 = view.findViewById(R.id.sp_2);
        sp_3 = view.findViewById(R.id.sp_3);
        sp_4 = view.findViewById(R.id.sp_4);

        sp_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CTSPEspressoActivity.class);
                startActivity(intent);
            }
        });

        sp_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CTSPCappuccinoActivity.class);
                startActivity(intent);
            }
        });

        sp_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), CTSPLatteActivity.class));

            }
        });

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

