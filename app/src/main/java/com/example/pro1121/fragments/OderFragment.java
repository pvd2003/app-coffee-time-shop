package com.example.pro1121.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.pro1121.R;
import com.example.pro1121.activities.TTDonhangActivity;


public class OderFragment extends Fragment {
    Button txtbuynow ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
           View view = inflater.inflate(R.layout.fragment_oder, container, false);
        txtbuynow =view.findViewById(R.id.txtbuynow);
        txtbuynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent(getContext(),TTDonhangActivity.class);
            }
        });
        return view;
    }

    private void opendialog() {
        AlertDialog.Builder  builder = new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_adddonhang,null);
        builder.setView(view);
        Dialog dialog = new Dialog(getContext()
        );
        dialog.show();
    }
}