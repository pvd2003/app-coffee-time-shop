package com.example.pro1121.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pro1121.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FragmentQLLoaiSanPham extends Fragment {
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ql_loai_san_pham, container, false);

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
        View view =inflater.inflate(R.layout.dialog_addloaisp,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
    }
}
